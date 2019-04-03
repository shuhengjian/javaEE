package com.musicbar.second.backstage.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.proxy.jdbc.StatementExecuteType;
import com.musicbar.core.annotation.LoggerAnnotation;
import com.musicbar.core.base.Result;
import com.musicbar.core.redis.RedisUtils;
import com.musicbar.core.utils.StringUtil;
import com.musicbar.second.domain.TGoodsInfo;
import com.musicbar.second.domain.TOrders;
import com.musicbar.second.domain.TOrdersInfo;
import com.musicbar.second.mapper.TOrdersMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class TOrdersService {
	@Autowired
	private TOrdersMapper tOrdersmapper;
	@Autowired
	private TGoodsInfoService tGoodsInfoService; 
	@Autowired
	private TOrderMealServices OrderMeal;
	
	@Autowired
	private RedisUtils redisUtils;
	/**
	 * 下单未付款
	 */
	private final String ORDERSNO = "ordersno";
	/**
	 * 支付未收货
	 */
	private final String ORDERSYES = "ordersyes";
	
	
	
	 /**
     *  新增订单
     * @param ordersId
     * @return
     */
    public String confirmOrderAdd(HttpServletRequest req,String goods) {
        JSONObject jb=    new JSONObject();
        Result rs = Result.error();
        boolean isNum = true;
        HttpSession session = req.getSession();
        JSONArray goodsArry = JSONArray.fromObject(goods);
        String [] goodsIdArry=new String[goodsArry.size()];
        String mobile = req.getParameter("mobile");//手机号
        String tableNum = req.getParameter("tableNum");//台号
        String orderID = StringUtil.getUUIDValue();//订单id
        String code =null; //StringUtil.getSerialNumber(null);//订单号
        String dersWay = req.getParameter("dersWay");//下单类型，前台为1，后台为2
        //同步锁订单号
        //synchronized (this) {
            //订单号累加
            TOrders payCode = tOrdersmapper.selectPayCode();//查询当天时间最大的订单号
            if(payCode == null) {
                code = StringUtil.getSerialNumber(null);//当天第一个订单号
            }else {
                String str = payCode.getOrdersCode();
                Long pCo = Long.parseLong(str);
                pCo = pCo+1;                          //订单号加1
                code = String.valueOf(pCo);
            }
        //}
        int tbNum = 0;
        if(tableNum != null) {
            tbNum =Integer.valueOf(tableNum);
        }
        /*tbNum = Integer.valueOf(tableNum);*/
        BigDecimal sum =BigDecimal.ZERO;//总金额
        Integer numSum=0;//总数量
        List<TOrdersInfo > tgi =new ArrayList<>();
        TOrdersInfo ordersInfo=null;
        if(goodsArry.size() > 0) {
            for (int i = 0; i < goodsArry.size(); i++) {
                JSONObject job = goodsArry.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                String goodsId = job.getString("goodsId"); // 每个商品的Id
                Integer num = job.getInt("num"); // 该商品的数量
                numSum += num;     //商品数量累加        
                goodsIdArry[i]=goodsId;
                TGoodsInfo gList = null;
                
                synchronized (this) {//同步锁
                    gList = tGoodsInfoService.selectGoodsCar(goodsId); // 根据ID查询商品
                    //下单时减掉下单的商品
                    Long stock= gList.getGoodsStock();//查询库存数
                    if(stock < num) { //如果商品数大于库存是，提示：库存不足 ，返回Code=0
                        rs.setMsg(gList.getGoodsName() + "库存不足!剩余：" + stock);
                        rs.setCode(0);
                        isNum = false;
                        break;
                    }
                    if (stock >= num) { //库存数大于等于商品数，
                        rs.setCode(1);  //返回Code=1
                        Long snum = num.longValue();
                        Long gds_stock = stock-snum;//库存数减去商品数
                        gList.setGoodsStock(gds_stock);
                        tGoodsInfoService.updataStock(gList);//库存数修改
                    }
                }
                
                BigDecimal price = gList.getGoodsPrice().multiply(BigDecimal.valueOf(num));// 小计=单价*数量
                //循环商品，将商品信息存入点单详情表
                ordersInfo =new TOrdersInfo();
                ordersInfo.setGoodsId(goodsId);                        //商品id
                ordersInfo.setGoodsName(gList.getGoodsName());         //商品名称
                ordersInfo.setGoodsNum(num);                           //商品数量
                ordersInfo.setGoodsPrice(gList.getGoodsPrice());       //商品价格
                ordersInfo.setGoodsQuantity(gList.getGoodsQuantity()); //商品净含量
                ordersInfo.setGoodsStandard(gList.getGoodsStandard()); //商品规格
                ordersInfo.setGoodsUnits(gList.getGoodsUnits());       //商品单位
                ordersInfo.setOrdersId(orderID);                       //订单id
                ordersInfo.setOrdersInfoId(StringUtil.getUUIDValue()); //订单详情id
                sum = sum.add(price); // 总金额累加
                tgi.add(ordersInfo);
            }
            TOrders tOrders = new TOrders();
            if(isNum) {
                //将订单信息存入订单表
                tOrders.setOrdersId(orderID);          //订单id
                tOrders.setOrdersCode(code);           //订单号
                tOrders.setOrdersMobile(mobile);       //手机号
                if(tbNum != 0) {                       //台号
                    tOrders.setTableNum(tbNum);
                }
                tOrders.setOrdersMoney(sum);           //总金额
                tOrders.setOrdersNum(numSum);          //总数量
                tOrders.setOrdersWay(dersWay);         //下单类型
                tOrders.setOrdersState("0");           //支付状态 
                tOrders.setCreateTime(new Date());     //下单时间
                session.setAttribute("mobile", mobile);//将手机号存入session               
                tOrdersmapper.insert(tOrders); //新增订单信息
                
                OrderMeal.modifyHostId(tgi);  //新增订单详情
            } 
            redisAdd(tOrders,State.unpaid);
            
        }
        jb.put("orderID",orderID);
        rs.setData(jb);
        return rs.toString();
    }
	
	/**
	 * 订单支付
	 * @param req
	 * @param session
	 * @return
	 */
	public String update(HttpServletRequest req,HttpSession session){
		String mobile = (String) session.getAttribute("mobile");
		String id = (String) session.getAttribute("id");
		String radio = req.getParameter("radio");//支付方式
		TOrders tOrders = null;
		if(mobile != null && id == null) {
			tOrders = selectseMobileSe(mobile);
		}
		if(id != null) {
			tOrders = selectByPrimaryKey(id);
		}
		tOrders.setPaymentTime(new Date());
		tOrders.setOrdersState("1");
		tOrders.setPaymentMode(radio);
		tOrdersmapper.updateCode(tOrders);
		
		redisAdd(tOrders,State.unconfirmed);
		
		Result ok = Result.OK();
		//ok.addData("", );
		return ok.toString();
	}
	
	public enum State {
		unpaid("ordersno"),      //未支付
		unconfirmed("ordersyes");  //未确认
		State(String val) {
			this.val = val;
		}
		private String val;
		public String getVal() {
			return val;
		}
		
	}
	public void redisAdd(TOrders tOrders,State state) {
		
			@SuppressWarnings("unchecked")
			List<String> tOrdersList = redisUtils.getCacheObject(state.getVal(),List.class);
			if(tOrdersList == null) {
				tOrdersList = new ArrayList<>();
			}
			String array = JSONArray.fromObject(tOrders).toString();
			tOrdersList.add(array);
			//将TOrders存入Redis
			redisUtils.setCacheObject(state.getVal(), tOrdersList.toString());
		
	}
	
	public void redisDel(String tOrdersId,State state){
		List<String> tOrdersList = redisUtils.getCacheObject(state.getVal(),List.class);
		
		if(tOrdersList != null) {
			for (String str : tOrdersList) {
				JSONObject tOarray = JSONObject.fromObject(str);
				TOrders  tt = (TOrders) JSONObject.toBean(tOarray, TOrders.class);
				if(tt.getOrdersId().equals(tOrdersId)) {
					tOrdersList.remove(str);
					redisUtils.setCacheObject(state.getVal(), tOrdersList);
					break;
				}
			}
		}
	}
	
	public TOrders getRedis(String tOrdersId,State state) {
		List<String> tOrdersList = redisUtils.getCacheObject(state.getVal(),List.class);
		
		if(tOrdersList != null) {
			for (String str : tOrdersList) {
				JSONObject tOarray = JSONObject.fromObject(str);
				TOrders  tt = (TOrders) JSONObject.toBean(tOarray, TOrders.class);
				if(tt.getOrdersId().equals(tOrdersId)) {
					return tt;
				}
			}
		}
		return null;
	}
	
	@Scheduled(cron = "0/600 * * * * *")
    public void reportCurrentTime() {
		for (State ss : State.values()) {
			 List<String> tOList = redisUtils.getCacheObject(ss.val,List.class);
		     JSONArray jsonOr= JSONArray.fromObject(tOList);
		     TOrders array = (TOrders) JSONArray.toArray(jsonOr);
		     String Ostate = array.getOrdersState();
		     if(Ostate == "0") {
		    	 Long date1 = System.currentTimeMillis();
	        	 Long date2 = array.getCreateTime().getTime();
	        	 int date = (int)((date1-date2)/(1000*60));
		    	 if(date >= 15) {
		    		 array.setOrdersState("3");
        			 tOrdersmapper.updateTime(array);
        			 redisDel(array.getOrdersId(),ss);
		    	 }
		     }
		     if(Ostate == "1") {
		    	 Long date1 = System.currentTimeMillis();
	        	 Long date2 = array.getPaymentTime().getTime();
	        	 int date = (int)((date1-date2)/(1000*60));
		    	 if(date >= 120) {
		    		 array.setOrdersState("2");
        			 tOrdersmapper.updateTime(array);
        			 redisDel(array.getOrdersId(),ss); 
		    	 }
		     }
		}
		 
		 
    }
	
	/**
	 * 根据id查询
	 * @param ordersId
	 * @return
	 */
	public TOrders selectByPrimaryKey(String ordersId) {
		return tOrdersmapper.selectByPrimaryKey(ordersId);
	};
	
	
	/**
	 * 根据条件查询所有订单基本信息
	 * @return
	 */
	public List<TOrders> selectAll(TOrders tOrders) {
		return tOrdersmapper.selectAll(tOrders);

	}  
   /**
	 * 查询订单详细信息
	 * @param tOrders
	 * @return
	 */
	public List<TOrders> selectorder(String ordersId){
		return tOrdersmapper.selectorder(ordersId);
	}
   
   public List<TOrders> selectOrdCode(TOrders tOrders){
	   return tOrdersmapper.selectOrdCode(tOrders);
   };
	/**
     * 根据订单号查询
    * @param ordersId
    * @return
    */
   public List<TOrders> selectCode(String ordersId) {
	   return tOrdersmapper.selectCode(ordersId);
   };
   
   /**
    * 根据状态号查询
   * @param ordersId
   * @return
   */
   public int selectState(String ordersId) {
	   return selectState(ordersId);
   };
   
   /**
    * 根据id查询商品信息
    * @return
    */
   public List<TOrders> selectOrdInfo(TOrders tOrders) {
	   return tOrdersmapper.selectOrdInfo(tOrders);
   }; 
   
   /**
    * 前端订单更新状态
    * @param tOrders
    * @return
    */
   public int updateCode(TOrders tOrders){
	   return tOrdersmapper.updateCode(tOrders);
   };
   
   
   /**
    * 根据session手机号查询
   * @param ordersId
   * @return
   */
  public TOrders selectseMobileSe(String ordersId){
	  return tOrdersmapper.selectseMobileSe(ordersId);
  };
  
  /**

   * 关联参数表根据手机号查询
   * @param ordersId
   * @return
   */
   public TOrders selectParameter(String tOrders) {
	  return tOrdersmapper.selectParameter(tOrders);
  };
  /**
   * 关联参数表根据id查询
   * @param ordersId
   * @return
   */
   public TOrders selectParameterId(String tOrders) {
	  return tOrdersmapper.selectParameterId(tOrders);
  }

	public List<TOrders> selectOrderAll() {
		return tOrdersmapper.selectOrderAll();
	}
	
}
   

