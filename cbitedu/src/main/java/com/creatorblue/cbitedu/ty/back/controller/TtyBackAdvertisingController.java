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
import com.creatorblue.cbitedu.ty.back.service.TtyBackAdvertisingService;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyAdvertising;
import comm.FileUpload;

@Controller
@RequestMapping("/ttyBackAdvertisingController.do")
public class TtyBackAdvertisingController extends HihBaseController {
	@Autowired
	private TtyBackAdvertisingService ttyBackAdvertisingService;
	/**
	 * 访问列表， 并且初始化列表中所需的数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/advertising/advertisinglist");
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
				"advertising_");
		param.put("page", page);
		List<TtyAdvertising> list = ttyBackAdvertisingService.selectPageTtyAdvertisingByMap(param);
		this.convertParam(list, "advertisingState", "advertising_state");
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
		mav.setViewName("/advertising/advertisingfrom");
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
		ModelAndView mav = new ModelAndView("/advertising/advertisingfrom");
		String id = request.getParameter("id");
		TtyAdvertising ttyAdvertising = ttyBackAdvertisingService.selectByPrimaryKey(id);
		mav.addObject("ttyAdvertising", ttyAdvertising);
		return mav;
	}
	
	/**
	 * 异步提交表单， 保存数据.
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
		TtyAdvertising ttyAdvertising = new TtyAdvertising();
		this.setValue(request, ttyAdvertising);
		try {
			TtyAdvertising result = ttyBackAdvertisingService.checkTheNameWithParentId(ttyAdvertising);
			if (result != null) {
				msg = "名称已存在！";
				flag = false;
			}else if(StringUtils.isEmpty(ttyAdvertising.getAdvertisingId()) && file.getSize() == 0) {
				msg = "未选则上传图片";
				flag = false;
			}else {
				fileUpload.setSaveFilePath(PropertiesUtil.getValue( "saveFilePath"));//根路径
				fileUpload.setPath(PropertiesUtil.getValue( "advertisingPath"));//上传文件的相对路径
				fileUpload.setAllowFiles(".jpg .jpeg .gif .png .bmp");//文件上传类型			
				//获取当前登录用户
				TsysUserinfo userinfo = (TsysUserinfo) getSession(request,Constant.USER_INFO);//获取当前登录用户
				String uId = userinfo.getUserId();
				//获取当前时间
				String time = DateUtils.fotmatDateTOyMdHms(new Date());
				
				//执行文件上传
				fileUpload.fileUpload(file, fileUpload);
				//文件上传失败直接提交
				if(!fileUpload.isIs() && fileUpload.getState() != "") {
					JSONObject jsonObject = new JSONObject(fileUpload);
					return jsonObject.toString();
				}
				//赋值
				tsysAttach.setFileName(ttyAdvertising.getAdvertisingName());
				//上传成功时赋值
				if(fileUpload.isIs()) {
					tsysAttach.setSecondName(fileUpload.getOriginalName());
					tsysAttach.setFileSuffix(fileUpload.getSuffix());
					tsysAttach.setFileUrl(fileUpload.getPath()+fileUpload.getFileName());
					tsysAttach.setFileSize(String.valueOf(fileUpload.getSize()));
				}
				ttyAdvertising.setTsysAttach(tsysAttach);
				
				if (StringUtils.isEmpty(ttyAdvertising.getAdvertisingId())) {//新增操作，保存信息
					tsysAttach.setAttachId(Identities.uuid());
					ttyAdvertising.setAdvertisingId(Identities.uuid());
					tsysAttach.setPkid(ttyAdvertising.getAdvertisingId());
					tsysAttach.setCreateUserId(uId);
					tsysAttach.setCreateTime(time);
					ttyBackAdvertisingService.insert(ttyAdvertising);
					msg = "保存成功！";
				} else {//修改操作
					//执行删除操作
					TtyAdvertising oldttyAdvertising = ttyBackAdvertisingService.selectByPrimaryKey(ttyAdvertising.getAdvertisingId());//原品牌
					//特殊情况可能存在oldttyBrand.getTsysAttach()不存在，则不需要删除图片
					if(oldttyAdvertising.getTsysAttach() != null) {
						String oldFileUrl = oldttyAdvertising.getTsysAttach().getFileUrl();//原品牌相对路径,需要删除的相对路径
						
						fileUpload.delete(fileUpload, oldFileUrl);//删除操作
					}
					
					//保存信息
					tsysAttach.setPkid(ttyAdvertising.getAdvertisingId());
					tsysAttach.setUpdateUserId(uId);
					tsysAttach.setUpdateTime(time);
					
					ttyBackAdvertisingService.updateByPrimaryKey(ttyAdvertising);
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
		String[] advertisingId = request.getParameterValues("advertisingIds");
		Boolean flag = true;

		try {
			for (int i = 0; i < advertisingId.length; i++) {
				ttyBackAdvertisingService.deleteByPrimaryKey(advertisingId[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
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
		TtyAdvertising ttyAdvertising = new TtyAdvertising();
		String advertisingState = request.getParameter("advertisingState");
		ttyAdvertising.setAdvertisingState(advertisingState);
		String[] advertisingId = request.getParameterValues("advertisingIds");
		
		boolean flag = true;
		try {
			for (int i = 0; i < advertisingId.length; i++) {
				ttyAdvertising.setAdvertisingId(advertisingId[i]);
				ttyBackAdvertisingService.updateBrandStateByPrimaryKey(ttyAdvertising);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		String result = "{\"flag\" : " +flag + "}";
		this.renderJson(response, result);
	}
}