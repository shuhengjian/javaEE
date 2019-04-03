package com.creatorblue.cbitedu.ty.back.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.core.constants.Constant;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.core.utils.DateUtils;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.core.utils.PropertiesUtil;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;
import com.creatorblue.cbitedu.ty.back.service.TtyBackBrandService;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyBrand;

import comm.FileUpload;
@Controller
@RequestMapping("/ttyBrandController.do")
public class TtyBackBrandController extends HihBaseController {
	@Autowired
	private TtyBackBrandService ttyBackBrandService;
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
		ModelAndView mv = new ModelAndView("/brand/ttybrandlist");
		return mv;
	}
	@RequestMapping(params = "method=editor") 
	public ModelAndView editor(HttpServletRequest request,
			HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("/brand/ueitor");
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
				"brand_");
		param.put("brandState", request.getParameter("brand_state"));
		param.put("page", page);
		List<TtyBrand> list = ttyBackBrandService.selectPageTtyBrandByMap(param);
		this.convertParam(list, "brandState", "brand_state");
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
		mav.setViewName("/brand/ttybrandform");
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
		ModelAndView mav = new ModelAndView("/brand/ttybrandform");
		String id = request.getParameter("id");
		TtyBrand ttyBrand = ttyBackBrandService.selectByPrimaryKey(id);
		mav.addObject("ttyBrand", ttyBrand);
		return mav;
	}

	/**
	 * 异步提交表单， 保存数据。
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(params = "method=ajaxSave")
	@ResponseBody
	public String ajaxSave(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("file") MultipartFile file){
		TsysAttach tsysAttach = new TsysAttach();//创建资源类
		//创建文件上传
		FileUpload fileUpload = new FileUpload();
		
		boolean flag = true;
		String msg = null;
		TtyBrand ttyBrand = new TtyBrand();
		this.setValue(request, ttyBrand);
		try {
			TtyBrand result = ttyBackBrandService.checkTheNameWithParentId(ttyBrand);
			if (result != null) {
				msg = "已经存在相同名称的品牌名称,请重新输入品牌名称！";
				flag = false;
			}else if(StringUtils.isEmpty(ttyBrand.getBrandId()) && file.getSize() == 0) {
				msg = "未选则上传图标";
				flag = false;
			}else {
				fileUpload.setSaveFilePath(PropertiesUtil.getValue( "saveFilePath"));//根路径
				fileUpload.setPath(PropertiesUtil.getValue( "path"));//上传文件的相对路径
				fileUpload.setAllowFiles(".jpg .jpeg .gif .png .bmp");//文件上传类型
				
				//获取当前登录用户
				TsysUserinfo userinfo = (TsysUserinfo) getSession(request,Constant.USER_INFO);//获取当前登录用户
				String uId = userinfo.getUserId();
				//获取当前时间
				String time = DateUtils.fotmatDateTOyMdHms(new Date());
				
				//执行文件上传出参
				fileUpload.fileUpload(file, fileUpload);
				//文件上传失败直接提交
				if(!fileUpload.isIs() && fileUpload.getState() != "") {
					JSONObject jsonObject = new JSONObject(fileUpload);
					return jsonObject.toString();
				}
				//赋值
				tsysAttach.setFileName(ttyBrand.getBrandName());
				//上传成功时赋值
				if(fileUpload.isIs()) {
					tsysAttach.setSecondName(fileUpload.getOriginalName());
					tsysAttach.setFileSuffix(fileUpload.getSuffix());
					tsysAttach.setFileUrl(fileUpload.getPath()+fileUpload.getFileName());
					tsysAttach.setFileSize(String.valueOf(fileUpload.getSize()));
				}
				ttyBrand.setTsysAttach(tsysAttach);
				
				if (StringUtils.isEmpty(ttyBrand.getBrandId())) {//新增操作，保存信息
					tsysAttach.setAttachId(Identities.uuid());
					ttyBrand.setBrandId(Identities.uuid());
					tsysAttach.setPkid(ttyBrand.getBrandId());
					tsysAttach.setCreateUserId(uId);
					tsysAttach.setCreateTime(time);
					ttyBackBrandService.insert(ttyBrand);
					msg = "保存成功！";
				} else {//修改操作
					//执行删除操作
					TtyBrand oldttyBrand = ttyBackBrandService.selectByPrimaryKey(ttyBrand.getBrandId());//原品牌
					//特殊情况可能存在oldttyBrand.getTsysAttach()不存在，则不需要删除图片
					if(oldttyBrand.getTsysAttach() != null && fileUpload.getState() != "") {
						String oldFileUrl = oldttyBrand.getTsysAttach().getFileUrl();//原品牌相对路径,需要删除的相对路径
						
						fileUpload.delete(fileUpload, oldFileUrl);//删除操作
					}
					
					//保存信息
					tsysAttach.setPkid(ttyBrand.getBrandId());
					tsysAttach.setUpdateUserId(uId);
					tsysAttach.setUpdateTime(time);
					
					ttyBackBrandService.updateByPrimaryKey(ttyBrand);
					msg = "修改成功！";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "操作失败";
			flag = false;
		}
		JSONObject jsonObject = new JSONObject(fileUpload);
		jsonObject.put("msg", msg);
		jsonObject.put("flag", flag);
		return jsonObject.toString();
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
		String[] brandIds = request.getParameterValues("brandIds");
		String flag = "1";
		int j = 0;
		//循环判断品牌下面是否含有商品
		for (int i = 0; i < brandIds.length; i++) {
			j = ttyBackBrandService.selectCountProduct(brandIds[i]);
			if(j > 0) {
				break;
			}
		}
		//如果有商品则跳过删除
		if(j > 0) {
			flag = "0";
		}else {
			try {
				for (int i = 0; i < brandIds.length; i++) {
					ttyBackBrandService.deleteByPrimaryKey(brandIds[i]);
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
		TtyBrand ttyBrand = new TtyBrand();
		String brandState = request.getParameter("brandState");
		ttyBrand.setBrandState(brandState);
		String[] brandIds = request.getParameterValues("brandIds");
		boolean flag = true;
		try {
			for (int i = 0; i < brandIds.length; i++) {
				ttyBrand.setBrandId(brandIds[i]);
				ttyBackBrandService.updateBrandStateByPrimaryKey(ttyBrand);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		String result = "{\"flag\" : " +flag + "}";
		this.renderJson(response, result);
	}
	/**
	 * 查询新闻内容
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=queryNewsContent")
	public String queryNewsContent(HttpServletRequest request,
			HttpServletResponse response) {
		
		return null;
	}
}
