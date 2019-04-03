package com.musicbar.second.backstage.controller;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.musicbar.core.annotation.LoggerAnnotation;
import com.musicbar.core.redis.RedisUtils;
import com.musicbar.core.uploads.FileUpload;
import com.musicbar.core.uploads.ImgCompress;
import com.musicbar.core.utils.StringUtil;
import com.musicbar.second.backstage.service.TAttachService;
import com.musicbar.second.backstage.service.TGoodsInfoService;
import com.musicbar.second.backstage.service.TParameterService;
import com.musicbar.second.backstage.service.TTypeService;
import com.musicbar.second.comm.config.ConfigProperties;
import com.musicbar.second.comm.param.ParamTransformation;
import com.musicbar.second.comm.param.ParamUtil;
import com.musicbar.second.domain.TAttach;
import com.musicbar.second.domain.TGoodsInfo;
import com.musicbar.second.domain.TParameter;
import com.musicbar.second.domain.TType;

/**
 * 商品管理变现层
 * @author laj
 *
 */
@Controller
@RequestMapping("/backstage")
public class TGoodsInfoController {
	@Autowired
	private TGoodsInfoService tGoodsInfoService;
	
	@Autowired
	private ParamTransformation paramTransformation;
	
	@Autowired
	private TTypeService tTypeService;
	
	@Autowired
	private ConfigProperties configProperties;
	
	@Autowired
	private TAttachService tAttachService;
	
	@Autowired
	private RedisUtils redisUtils;//缓存
	
	@Autowired
	private TParameterService tParameterService;//参数
	
	@Autowired
	private ParamUtil param;
	/**
	 * 进入商品列表页面
	 * @return
	 */
	@RequestMapping("/goods_querylist")
	@LoggerAnnotation(begin="进入查询商品列表页面",end="查询商品列表页面结束")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("backstage/pages/goods/list");
		return mv;
	}
	/**
	 * 进入商品缩略图页面
	 * @return
	 */
	@RequestMapping("/goods_querypiclist")
	@LoggerAnnotation(begin="进入查询商品列表页面",end="查询商品列表页面结束")
	public ModelAndView piclist() {
		ModelAndView mv = new ModelAndView("backstage/pages/goods/piclist");
		return mv;
	}
	/**
	 * 查询商品列表
	 * @return
	 */
	@RequestMapping("/goods_queryAll")
	@LoggerAnnotation(begin="进入查询商品列表方法",end="查询商品列表方法结束") 
	@ResponseBody
	public String queryAll(TGoodsInfo goods,int pageNum) {
		int pageSize = configProperties.getPageSize();
		PageHelper.startPage(pageNum,pageSize);
		List<TGoodsInfo> list = tGoodsInfoService.selectAll(goods);
		PageInfo<TGoodsInfo> page = new PageInfo<>(list);
		return net.sf.json.JSONArray.fromObject(page).toString();
	}
	/**
	 * 查询商品分类
	 */
	@RequestMapping("/goods_type")
	@ResponseBody
	public String selectType() {
		List<TType> list = tTypeService.selectAll();
		return net.sf.json.JSONArray.fromObject(list).toString(); 
	}
	
	/**
	 * 查询商品状态
	 * @return
	 */
	@RequestMapping("goods_state")
	@ResponseBody
	public String selectState() { 
		List<TParameter> list =  param.getListByKey("goods_state");
		return net.sf.json.JSONArray.fromObject(list).toString(); 
	}
	
	/**
	 * 进入编辑页面
	 * @return
	 */
	@RequestMapping("/goods_open")
	@LoggerAnnotation(begin="进入商品添加页面方法",end="查询商品添加页面方法结束")
	public ModelAndView open(String goodsId,String code) {
		ModelAndView mv = new ModelAndView("backstage/pages/goods/edit");
		if(goodsId != null && !goodsId.isEmpty()) {
			TGoodsInfo goods = tGoodsInfoService.selectGoodsById(goodsId);
			mv.addObject("goods",goods);
		}
		//查询商品分类
		List<TType> typeList = tTypeService.selectAll();
		mv.addObject("typeList", typeList);
		//查询商品状态
		List<TParameter> stateList = param.getListByKey("goods_state");
		mv.addObject("stateList", stateList);
		//查询商品是否特价
		List<TParameter> special = param.getListByKey("goods_special");
		mv.addObject("special", special);
		//查询商品单位
		List<TParameter> units = param.getListByKey("goods_units");
		mv.addObject("units", units);
		//查询商品规格
		List<TParameter> standard = param.getListByKey("goods_standard");
		mv.addObject("standard", standard); 
		//查询商是否烹饪
		List<TParameter> cook = param.getListByKey("cook");
		mv.addObject("cook", cook);
		if(code !=null && !goodsId.isEmpty()) {
			mv.addObject("code", code);
		}
		return mv; 
	}
	
	/**
	 * 添加or修改
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/goods_save") 
	@ResponseBody
	@LoggerAnnotation(begin="进入商品添加or修改方法",end="查询商品添加or修改方法结束")
	public String save(HttpServletRequest request,TGoodsInfo goods,@RequestParam("file") MultipartFile file,String code){
		 TAttach attach = new TAttach();//创建资源类 
		 //创建文件上传
		 FileUpload fileUpload = new FileUpload();
		 JSONObject json=new JSONObject();
		 String msg = goods.getGoodsId() !=null && ! goods.getGoodsId().isEmpty()? "修改":"添加";
		 if(file !=null && file.getSize() > 0) {
			 fileUpload.setSaveFilePath(configProperties.getPath());//根路径
			 fileUpload.setPath(configProperties.getGoodsPath());//上传文件的相对路径
			 fileUpload.setAllowFiles(".jpg .jpeg .gif .png .bmp");//文件上传类型
			 fileUpload.fileUpload(file, fileUpload);
		 }
		 attach.setFileName(goods.getGoodsName());
		 if(fileUpload.isIs()) {
			 attach.setFileSuffix(fileUpload.getSuffix());
			 attach.setFileUel(fileUpload.getPath()+fileUpload.getFileName());
		 }
		 goods.setAttch(attach);
		 int success = tGoodsInfoService.saveOrUpdate(goods,attach);
		
		 if(success > 0 && code !=null && !code.isEmpty()) {
			  json.put("code", 200);//预警修改成功
        	  json.put("msg", msg+"成功");
        	  json.put("flag", true);
		 }else {
	         if(success == 0) {
	        	 json.put("msg", msg+"失败");
	        	 json.put("flag", false);
	         }else if(success == -1) {
	        	 json.put("msg", "商品名称已存在");
	        	 json.put("flag", false);
	         }else if(success == -2) {
	        	 json.put("msg", "商品编号已存在");
	        	 json.put("flag", false);
	         }
		 }
		 if(success >0 && (code==null || code.isEmpty())) {
			 json.put("code", 300);//商品添加修改成功
			 json.put("msg", msg+"成功");
		 }
		return json.toString();

	}
	/**
	 * 删除
	 * @param goodsId
	 * @return
	 */
	@RequestMapping("/goods_delete")
	@ResponseBody
	public String delete(String goodsId) {
		JSONObject json=new JSONObject();
		int count = tGoodsInfoService.deleteById(goodsId);
		int num = tAttachService.deleteById(goodsId);
		if(count > 0 && num > 0) {
			json.put("msg", "删除成功");
		}else {
			json.put("msg", "删除失败");
		}
		return json.toString();
	}
	/**
	 * 批量删除
	 */
	@RequestMapping("/goods_deleteAll")
	@ResponseBody
	public String deleteAll(String id) {
		JSONObject json=new JSONObject();
		int count = 0,num = 0;
		if(!id.equals("")) {
			String[] split = id.split(",");
			List<String> list = new ArrayList<String>();
			for(String string : split) {
				list.add(string);
			}
			count = tGoodsInfoService.deleteAll(list);
			num = tAttachService.deleteAll(list);
		}else {
			count = -1;
			num = -1;
		}
		if(count > 0 ) {
			json.put("msg", "操作成功");
		}else if(count == -1) {
			json.put("msg", "请勾选要删除的记录");
		}else {
			json.put("msg", "操作失败");
		}
		return json.toString();
	}
	/**
	 * 启用，禁用
	 * @param goods
	 * @return
	 */
	@RequestMapping("/goods_updateState")
	@ResponseBody
	public String updateState(TGoodsInfo goods) {
		JSONObject json=new JSONObject();
		int count = tGoodsInfoService.updateState(goods);
		if(count == 1) {
			json.put("msg", "操作成功");
		}else {
			json.put("msg", "操作失败");
		}
		return json.toString();
	}
	/**
	 * 上移
	 * @param request
	 * @return
	 */
	@RequestMapping("/goods_moveUp")
	@ResponseBody
	public String moveUp(String goodsId) {
		JSONObject json=new JSONObject();
		//通过ID查询出当前行的商品信息
		TGoodsInfo goodsInfo = tGoodsInfoService.selectGoodsById(goodsId);
		//获取当前行的排序号
		int sort = goodsInfo.getGoodsSort();
		//通过当前行的排序号查询出上一条记录
		TGoodsInfo tGoodsInfo = tGoodsInfoService.infoBySortbefer(sort);
		//交换goods_sort的值
        int temp = goodsInfo.getGoodsSort();
        goodsInfo.setGoodsSort(tGoodsInfo.getGoodsSort());
        tGoodsInfo.setGoodsSort(temp);
        //更新到数据库中
        tGoodsInfoService.updateBySort(goodsInfo);
        tGoodsInfoService.updateBySort(tGoodsInfo);
        json.put("code", 200);
		return json.toString();
	}
	/**
	 * 下移
	 * @param request
	 * @return
	 */
	@RequestMapping("/goods_moveDown")
	@ResponseBody
	public String moveDown(String goodsId) {
		JSONObject json=new JSONObject();
		//通过ID查询出当前行的商品信息
		TGoodsInfo goodsInfo = tGoodsInfoService.selectGoodsById(goodsId);
		//获取当前行的排序号
		int sort = goodsInfo.getGoodsSort();
		//通过当前行的排序号查询出上一条记录
		TGoodsInfo tGoodsInfo = tGoodsInfoService.infoBySortafter(sort);
		//交换goods_sort的值
        int temp = goodsInfo.getGoodsSort();
        goodsInfo.setGoodsSort(tGoodsInfo.getGoodsSort());
        tGoodsInfo.setGoodsSort(temp);
        //更新到数据库中
        tGoodsInfoService.updateBySort(goodsInfo);
        tGoodsInfoService.updateBySort(tGoodsInfo);
        json.put("code", 200);
		return json.toString();
	}
	/**
	 * 进入预警界面
	 * @return
	 */
	@RequestMapping("goods_stockList")
	public ModelAndView stockList() {
		ModelAndView mv = new ModelAndView("backstage/pages/goods/stockList");
		List<TGoodsInfo> list = tGoodsInfoService.selectByWarning();
		mv.addObject("list", list);
		return mv;
	}
	/**
	 * 进入预警界面
	 * @return
	 */
	@RequestMapping("wecomel_index")
	public ModelAndView wecomelIndex() {
		ModelAndView mv = new ModelAndView("backstage/index");
		List<TGoodsInfo> list = tGoodsInfoService.selectByWarning();
		mv.addObject("list", list);
		return mv;
	}
}
