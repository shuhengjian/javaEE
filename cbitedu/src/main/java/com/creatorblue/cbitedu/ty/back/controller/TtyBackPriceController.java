package com.creatorblue.cbitedu.ty.back.controller;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.core.constants.Constant;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.system.domain.TsysPost;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;
import com.creatorblue.cbitedu.ty.back.service.TtyBackPriceService;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyBrand;
import com.creatorblue.cbitedu.ty.domain.TtyPrice;
import com.creatorblue.cbitedu.ty.domain.TtyType;

@Controller
@RequestMapping("/ttyBackPriceController.do")
public class TtyBackPriceController extends HihBaseController {
	@Autowired
	private TtyBackPriceService ttyBackPriceService;
	/**
	 * 访问列表， 并且初始化列表中所需的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/price/ttypricelist");
		return mv;
	}
	
	/**
	 * 访问列表， 查询数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=query")
	public void query(HttpServletRequest request, HttpServletResponse response) {
		Page page = this.getPage(request);
		Map<String, Object> param = WebUtils.getParametersStartingWith(request,
				"price_");
		param.put("priceState", request.getParameter("brand_state"));
		if(request.getParameter("price_priceMax") != "" && request.getParameter("price_priceMax") != null) {
			Double priceMax = Double.parseDouble(request.getParameter("price_priceMax"));
			param.put("priceMax", priceMax);
		}
		if(request.getParameter("price_priceMin") != "" && request.getParameter("price_priceMin") != null) {
			Double priceMin = Double.parseDouble(request.getParameter("price_priceMin"));
			param.put("priceMin", priceMin);
		}
		param.put("page", page);
		List<TtyPrice> list = ttyBackPriceService.selectPageTtyPriceByMap(param);
		this.convertParam(list, "priceState", "brand_state");
		renderJson(list, page, response);
	}
	/**
	 * 進入新增頁面， 并初始化新增页面所需要的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=add")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/price/ttypriceform");
		return mav;
	}
	/**
	 * 修改选中的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=modify")
	public ModelAndView modify(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/price/ttypriceform");
		String id = request.getParameter("id");
		TtyPrice ttyPrice = ttyBackPriceService.selectByPrimaryKey(id);
		mav.addObject("ttyPrice", ttyPrice);
		return mav;
	}
	/**
	 * 异步提交表单， 保存数据。
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=ajaxSave")
	public void ajaxSave(HttpServletRequest request,
			HttpServletResponse response) {
		TtyPrice price = new TtyPrice();
		this.setValue(request, price);
		boolean flag = true;
		String msg = null;
		try {
			TtyPrice result=new TtyPrice();
			//查询出最小值集合，值是从小到大排序的
			List minList = ttyBackPriceService.selectAllMin();
			//查询出最大值集合，值是从小到大排序的
			List maxList = ttyBackPriceService.selectAllMax();
			//记录min小于最小值集合中的一个数后，记录这个最小值的下标
	  		int num1 = -1;
	  		Double min = 0.00;
	  		if(price.getPriceMin()!=null) {
	  			min = Double.parseDouble(String.valueOf(price.getPriceMin()));
	  		}
	  		if(min < 0 ) {
	  			msg = "价格区间最小值不能为负数或零，如果您输入了0，可以选择不输入";
				String checkResult = "{\"flag\" : " + false
						+ " , \"msg\" : \"" + msg + "\"}";
				this.renderJson(response, checkResult);
				return;
	  		}
			if (price.getPriceMin()!=null) {
				//判断最小值是否存在
				result = ttyBackPriceService.checkTheMinWithParentId(price);
				
				//把前端传过来的min转为int型方便比较
		  		min = Double.parseDouble(String.valueOf(price.getPriceMin()));
				//取出最大值集合中的最大值
		  		String tempMax = maxList.get(maxList.size()-1).toString();
		  		int index = tempMax.indexOf("=") + 1;
		  		tempMax = tempMax.substring(index ,tempMax.indexOf("}"));
		  		Double maxMax = Double.parseDouble(tempMax);
		  		String tempMin1 = minList.get(minList.size()-1).toString();
		  		int index1 = tempMin1.indexOf("=") + 1;
		  		tempMax = tempMin1.substring(index1 ,tempMin1.indexOf("}"));
		  		Double minMax = Double.parseDouble(tempMax);
		  		Double max = 0.00;
		  		if(price.getPriceMax() != null) {
		  			max = Double.parseDouble(String.valueOf(price.getPriceMax()));
		  		}
				//如果输入的最小值大于大于所有最大值的最大值，则不需要验证交交集，反之进入if进行验证
				if(min < maxMax) {
					//如果最大值集合的第一项为空，最大值集合与最小值集合将不一一对应，会最小值的第一项对应最大值的第二项，最大值的第一项对应最小值的最后一项
					if(maxList.get(0) == null) {
						//将最小值的集合与min比较
						for(int i = 0;i<minList.size();i++) {
							if(minList.get(i) != null) {
								//把值转为
								String tempMin = minList.get(i).toString();
						  		int _index = tempMin.indexOf("=") + 1;
						  		tempMin = tempMin.substring(_index ,tempMin.indexOf("}"));
						  		Double mins = Double.parseDouble(tempMin);
								//比较，min小于最小值集合中的一个数后，记录这个最小值，后台传过来的最大值要小于这个最小值，并且min要大于等于这个最小值前一个最小值对应的最大值
								if(min < mins) {
									num1 = i;
									if(i == 0 && max <= mins) {
										break;
									}
									String _tempMax = maxList.get(i).toString();
							  		int __index = _tempMax.indexOf("=") + 1;
							  		_tempMax = _tempMax.substring(__index ,_tempMax.indexOf("}"));
							  		Double max1 = Double.parseDouble(_tempMax);
							  		if(price.getPriceId() != null) {
							  			break;
							  		}else if(min < max1) {
										msg = "价格区间不能有交集";
										String checkResult = "{\"flag\" : " + false
												+ " , \"msg\" : \"" + msg + "\"}";
										this.renderJson(response, checkResult);
										return;
									}else {
										break;
									}
								}
							}
						}
						if(num1 == 0) {
							msg = "价格区间不能有交集";
							String checkResult = "{\"flag\" : " + false
									+ " , \"msg\" : \"" + msg + "\"}";
							this.renderJson(response, checkResult);
							return;
						}
					}
					//反之一一对应
					else {
						for(int i = 0;i<minList.size();i++) {
							if(minList.get(i) !=null) {
								String tempMin = minList.get(i).toString();
						  		int _index = tempMin.indexOf("=") + 1;
						  		tempMin = tempMin.substring(_index ,tempMin.indexOf("}"));
						  		Double mins = Double.parseDouble(tempMin);
								//比较，min小于最小值集合中的一个数后，记录这个最小值，后台传过来的最大值要小于这个最小值，并且min要大于等于这个最小值前一个最小值对应的最大值
								if(min < mins) {
									num1 = i;
									if(i == 0) {
										break;
									}
									String _tempMax = maxList.get(i-1).toString();
							  		int __index = _tempMax.indexOf("=") + 1;
							  		_tempMax = _tempMax.substring(__index ,_tempMax.indexOf("}"));
							  		Double max1 = Double.parseDouble(_tempMax);
							  		if(price.getPriceId() != null) {
							  			break;
							  		}else if(min < max1) {
										msg = "价格区间不能有交集";
										String checkResult = "{\"flag\" : " + false
												+ " , \"msg\" : \"" + msg + "\"}";
										this.renderJson(response, checkResult);
										return;
									}else {
										break;
									}
								}
							}
						}
						String _tempMax = maxList.get(maxList.size()-2).toString();
				  		int __index = _tempMax.indexOf("=") + 1;
				  		_tempMax = _tempMax.substring(__index ,_tempMax.indexOf("}"));
				  		Double max2 = Double.parseDouble(_tempMax);
						if(min > max2 && max < minMax) {
							num1 = -1;
						}else if(max > maxMax){
							msg = "价格区间不能有交集";
							String checkResult = "{\"flag\" : " + false
									+ " , \"msg\" : \"" + msg + "\"}";
							this.renderJson(response, checkResult);
							return;
						}
						if(num1 == 0) {
							msg = "价格区间不能有交集";
							String checkResult = "{\"flag\" : " + false
									+ " , \"msg\" : \"" + msg + "\"}";
							this.renderJson(response, checkResult);
							return;
						}
						
					}
				}
				if(max > minMax && price.getPriceId() != null) {
					msg = "价格区间不能有交集";
					String checkResult = "{\"flag\" : " + false
							+ " , \"msg\" : \"" + msg + "\"}";
					this.renderJson(response, checkResult);
					return;
				}
				if(maxList.get(0) == null && price.getPriceId() == null && price.getPriceMax() == null) {
					msg = "价格区间不能有交集";
					String checkResult = "{\"flag\" : " + false
							+ " , \"msg\" : \"" + msg + "\"}";
					this.renderJson(response, checkResult);
					return;
				}
				if (result != null) {
					msg = "已经存在相同价格区间最小值,请重新输入！";
					String checkResult = "{\"flag\" : " + false
							+ " , \"msg\" : \"" + msg + "\"}";
					this.renderJson(response, checkResult);
					return;
				}
			}
			if (price.getPriceMax()!=null) {
				result = ttyBackPriceService.checkTheMaxWithParentId(price);
				//把前端传过来的max转为int型方便比较
		  		Double max = Double.parseDouble(String.valueOf(price.getPriceMax()));
				//num1等于0，说明输入的最小值大于最大值集合的最大值，可以直接通过认证，反之进入if
		  		if(num1 > 0) {
		  			String tempMin = minList.get(num1).toString();
			  		int _index = tempMin.indexOf("=") + 1;
			  		tempMin = tempMin.substring(_index ,tempMin.indexOf("}"));
			  		Double mins = Double.parseDouble(tempMin);
					if(max > mins) {
						msg = "价格区间不能有交集";
						String checkResult = "{\"flag\" : " + false
								+ " , \"msg\" : \"" + msg + "\"}";
						this.renderJson(response, checkResult);
						return;
					}
				}
		  		//只输入最大值时，只有在小于最小值集合中的最小值才能通过认证，否则不行
		  		if(price.getPriceMin() == null) {
		  			//取出最小值集合中的最小值
		  			int _min = 0;
		  			int minIndex = 0;
		  			if(maxList.get(0) == null) {
		  				minIndex++;
		  			}
		  			String _tempMax = minList.get(minIndex).toString();
			  		int __index = _tempMax.indexOf("=") + 1;
			  		_tempMax = _tempMax.substring(__index ,_tempMax.indexOf("}"));
			  		Double minMin = Double.parseDouble(_tempMax);
		  			if(price.getPriceId() != null && max > minMin) {
		  				msg = "价格区间不能有交集！";
						String checkResult = "{\"flag\" : " + false
								+ " , \"msg\" : \"" + msg + "\"}";
						this.renderJson(response, checkResult);
						return;
		  			}else if(price.getPriceId() == null && minList.get(_min) == null) {
		  				msg = "价格区间不能有交集！";
						String checkResult = "{\"flag\" : " + false
								+ " , \"msg\" : \"" + msg + "\"}";
						this.renderJson(response, checkResult);
						return;
		  			}else {
		  				_min++;
		  			}
			  		String tempMin = minList.get(_min).toString();
			  		int index = tempMin.indexOf("=") + 1;
			  		tempMin = tempMin.substring(index ,tempMin.indexOf("}"));
			  		minMin = Double.parseDouble(tempMin);
			  		if(max > minMin) {
			  			msg = "价格区间不能有交集！";
						String checkResult = "{\"flag\" : " + false
								+ " , \"msg\" : \"" + msg + "\"}";
						this.renderJson(response, checkResult);
						return;
			  		}
		  		}
		  		//取出最小值集合中的最大值
		  		String tempMin = minList.get(minList.size()-1).toString();
		  		int index = tempMin.indexOf("=") + 1;
		  		tempMin = tempMin.substring(index ,tempMin.indexOf("}"));
		  		Double minMax = Double.parseDouble(tempMin);
		  		String min1 = String.valueOf(price.getPriceMin());
		  		if(max > minMax && min1.equals(minMax)) {
		  			msg = "价格区间不能有交集！";
					String checkResult = "{\"flag\" : " + false
							+ " , \"msg\" : \"" + msg + "\"}";
					this.renderJson(response, checkResult);
					return;
		  		}
				if (result != null) {
					msg = "已经存在相同价格区间最大值,请重新输入！";
					String checkResult = "{\"flag\" : " + false
							+ " , \"msg\" : \"" + msg + "\"}";
					this.renderJson(response, checkResult);
					return;
				}
			}
			if (StringUtils.isEmpty(price.getPriceId())) {
				price.setPriceId(Identities.uuid());
				ttyBackPriceService.insert(price);
				msg = "保存成功！";
			} else {
				ttyBackPriceService.update(price);
				msg = "修改成功！";
			}

		} catch (Exception e) {
			flag = false;
			msg = StringUtils.isEmpty(price.getPriceId()) ? "保存失败！" : "修改失败";
			e.printStackTrace();
		}
		String result = "{\"flag\" : " + flag + " , \"msg\" : \"" + msg + "\"}";
		this.renderJson(response, result);
	}
	/**
	 * 删除一条或多条数据
	 * -1表示异常 删除失败
	 * 1  删除成功
	 * 0 所选品牌下面包含商品不可删除
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=del")
	public void deleteById(HttpServletRequest request,
			HttpServletResponse response) {
		String[] priceIds = request.getParameterValues("priceIds");
		String flag = "1";
		int j = 0;
		
		TtyPrice pri = new TtyPrice();
		//循环判断品牌下面是否含有商品
		for (int i = 0; i < priceIds.length; i++) {
			List proPrices = ttyBackPriceService.selectAllProductFlatlyPrice();
			pri = ttyBackPriceService.selectMaxAndMin(priceIds[i]);
			for(int k=0;k < proPrices.size();k++) {
				String temp = proPrices.get(k).toString();
			  	int index = temp.indexOf("=") + 1;
			  	temp = temp.substring(index ,temp.indexOf("}"));
			  	Double proPrice = Double.parseDouble(temp);
			  	if(pri.getPriceMax() == null) {
			  		Double priMin = Double.parseDouble(String.valueOf(pri.getPriceMin()));
			  		if(proPrice >= priMin) {
				  		j = 1;
				  	}
			  	}else if(pri.getPriceMin() == null) {
			  		Double priMax = Double.parseDouble(String.valueOf(pri.getPriceMax()));
			  		if(proPrice <= priMax ) {
				  		j = 1;
				  	}
			  	}else {
			  		Double priMax = Double.parseDouble(String.valueOf(pri.getPriceMax()));
				  	Double priMin = Double.parseDouble(String.valueOf(pri.getPriceMin()));
				  	if(proPrice <= priMax && proPrice >= priMin) {
				  		j = 1;
				  	}
			  	}
			}
				
			if(j > 0) {
				break;
			}
		}
		//如果有商品则跳过删除
		if(j > 0) {
			flag = "0";
		}else {
			try {
				for (int i = 0; i < priceIds.length; i++) {
					ttyBackPriceService.deleteByPrimaryKey(priceIds[i]);
				}
			} catch (Exception e) {
				e.printStackTrace();
				flag = "-1";
			}
		}
		String result = "{\"flag\" : " + flag+ "}";
		this.renderJson(response, result);
	}
	/**
	 * 启用，禁用
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=state")
	public void state(HttpServletRequest request,
			HttpServletResponse response) {
		TtyPrice ttyPrice = new TtyPrice();
		String brandState = request.getParameter("priceState");
		ttyPrice.setPriceState(brandState);
		String[] brandIds = request.getParameterValues("priceIds");
		boolean flag = true;
		try {
			for (int i = 0; i < brandIds.length; i++) {
				ttyPrice.setPriceId(brandIds[i]);
				ttyBackPriceService.updatePriceStateByPrimaryKey(ttyPrice);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		String result = "{\"flag\" : " +flag + "}";
		this.renderJson(response, result);
	}
}
