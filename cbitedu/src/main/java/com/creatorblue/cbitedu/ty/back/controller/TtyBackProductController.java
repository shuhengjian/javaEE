package com.creatorblue.cbitedu.ty.back.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.core.constants.Constant;
import com.creatorblue.cbitedu.core.exception.ControllerException;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.core.utils.PropertiesUtil;
import com.creatorblue.cbitedu.system.domain.TsysOrg;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;
import com.creatorblue.cbitedu.system.service.TsysOrgService;
import com.creatorblue.cbitedu.ty.back.service.TtyBackBrandService;
import com.creatorblue.cbitedu.ty.back.service.TtyBackPriceService;
import com.creatorblue.cbitedu.ty.back.service.TtyBackProductService;
import com.creatorblue.cbitedu.ty.back.service.TtyBackTypeService;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyBrand;
import com.creatorblue.cbitedu.ty.domain.TtyPrice;
import com.creatorblue.cbitedu.ty.domain.TtyProduct;
import com.creatorblue.cbitedu.ty.domain.TtyType;
import com.creatorblue.cbitedu.ty.front.service.TtyAttachService;
import com.creatorblue.cbitedu.ty.front.service.TtyPriceService;
import com.creatorblue.cbitedu.ty.front.service.TtyTypeService;

@Controller
@RequestMapping("/ttyBackProductController.do")
public class TtyBackProductController extends HihBaseController {

	@Autowired
	private TtyBackProductService ttyBackProductService;
	@Autowired
	private TtyBackBrandService ttyBackBrandService;
	@Autowired
	private TtyBackPriceService ttyBackPriceService;
	@Autowired
	private TtyBackTypeService ttyBackTypeService;
	@Autowired
	private TtyTypeService ttyTypeService;
	@Autowired
	private TtyPriceService ttyPriceService;
	@Autowired
	private TsysOrgService tsysOrgService;

	@Autowired
	private TtyAttachService ttyAttachService;

	/**
	 * 访问列表， 并且初始化列表中所需的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/product/ttyproductlist");// 页面
		List<TtyType> ttyType = ttyTypeService.select(null);
		mv.addObject("ttyType", ttyType);
		List<TtyPrice> ttyPrice = ttyPriceService.select(null);
		mv.addObject("ttyPrice", ttyPrice);
		List<TtyBrand> ttyBrand = ttyBackBrandService.selectPageTtyBrandByMap(null);
		mv.addObject("ttyBrand", ttyBrand);
		List<TsysOrg> tsysOrg = tsysOrgService.selectPageTsysOrg();
		mv.addObject("tsysOrg", tsysOrg);
		return mv;
	}

	/**
	 * 访问列表， 查询数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ControllerException
	 */
	@RequestMapping(params = "method=query")
	public void query(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		Page page = this.getPage(request);
		Map<String, Object> param = WebUtils.getParametersStartingWith(request, "product_");
		param.put("brandName", request.getParameter("brandName"));
		param.put("typeName", request.getParameter("typeName"));
		param.put("typeStart", request.getParameter("typeStart"));
		param.put("orgName", request.getParameter("orgName"));
		param.put("priceId", request.getParameter("priceId"));
		param.put("priceMin", request.getParameter("priceMin"));
		param.put("priceMax", request.getParameter("priceMax"));
		param.put("page", page);
		List<TtyProduct> list = ttyBackProductService.selectPageTtyProductByMap(param);
		renderJson(list, page, response);
	}

	/**
	 * 進入新增頁面， 并初始化新增页面所需要的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/product/ttyproductform");

		List<TtyType> TtyType = ttyBackTypeService.select(null);
		mv.addObject("TtyType", TtyType);
		List<TtyPrice> ttyPrice = ttyBackPriceService.select(null);
		mv.addObject("ttyPrice", ttyPrice);
		List<TtyBrand> ttyBrand = ttyBackBrandService.selectBrand(null);
		mv.addObject("ttyBrand", ttyBrand);
		List<TsysOrg> orgList = tsysOrgService.selectPageTsysOrg();
		mv.addObject("orgList", orgList);
		return mv;
	}

	/**
	 * 修改选中的数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=modify")
	public ModelAndView modify(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("product/ttyproductform");

		List<TtyType> TtyType = ttyBackTypeService.select(null);
		mv.addObject("TtyType", TtyType);

		List<TtyPrice> ttyPrice = ttyBackPriceService.select(null);
		mv.addObject("ttyPrice", ttyPrice);

		List<TtyBrand> ttyBrand = ttyBackBrandService.selectBrand(null);
		mv.addObject("ttyBrand", ttyBrand);

		List<TsysOrg> orgList = tsysOrgService.selectPageTsysOrg();
		mv.addObject("orgList", orgList);

		String id = request.getParameter("id");
		TtyProduct ttyProduct = (TtyProduct) ttyBackProductService.selectDetailById(id);
		mv.addObject("ttyProduct", ttyProduct);

		return mv;
	}

	/**
	 * 异步提交表单， 保存数据。
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params = "method=ajaxSave")
	public void ajaxSave(HttpServletRequest request,
			@RequestPart(required = false, value = "product_cover_picture") MultipartFile fileCover,
			@RequestPart(required = false, value = "productDetailsPicture") List<MultipartFile> fileCovers,
			HttpServletResponse response) throws ControllerException {
		/* System.out.println(); */
		boolean flag = true;
		String msg = "";
		TtyProduct ttyProduct = new TtyProduct();
		this.setValue(request, ttyProduct);
		String pdId = ttyProduct.getProductId();
		TsysUserinfo userinfo = (TsysUserinfo) getSession(request, Constant.USER_INFO);// 获取当前登录用户
		
		TtyType type = new TtyType();
		type.setTypeId(request.getParameter("typeId"));
		ttyProduct.setTtyType(type);

		TtyBrand brand = new TtyBrand();
		brand.setBrandId(request.getParameter("brandId"));
		ttyProduct.setTtyBrand(brand);

		TtyPrice price = new TtyPrice();
		price.setPriceId(request.getParameter("priceId"));
		ttyProduct.setTtyPrice(price);

		TsysOrg org = new TsysOrg();
		org.setOrgId(request.getParameter("orgId"));
		ttyProduct.setTsysOrg(org);
		/* 图片上传 */

		BufferedInputStream fis = null;
		FileOutputStream fos = null;
		SimpleDateFormat sdf=null;
		/* 封面 */
		String path = null;
		if (fileCover != null && fileCover.getSize() > 0 && flag) {
			String id=Identities.uuid();
			String url =ajaxSavePicture(fileCover, ttyProduct,request, id);
			if (url==null) {
				flag = false;
		}
			String oldName = fileCover.getOriginalFilename();// 文件原名
			String suffix = oldName.substring(oldName.lastIndexOf("."));// 扩展名
			path = PropertiesUtil.getValue("productPath");// 上传路径
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			/*
			 * int count = ttyBackProductService.selectCount(ttyProduct); if (count !=-2) {
			 */
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (flag) {
				ttyProduct.setCreateUserId(userinfo.getUserId());
				ttyProduct.setProductCoverPicture(url);
				if (StringUtils.isEmpty(ttyProduct.getProductId())) {
					ttyProduct.setProductId(Identities.uuid());
					ttyProduct.setCreateUserId(userinfo.getUserId());
					ttyBackProductService.insert(ttyProduct);
					msg = "保存成功！";
				} else {
					ttyProduct.setUpdateUserId(userinfo.getUserId());
					ttyBackProductService.updateByPrimaryKeySelective(ttyProduct);
					msg = "修改成功！";
				}
			}			
		} catch (Exception e) {
			flag = false;
			msg = StringUtils.isEmpty(ttyProduct.getProductId()) ? "保存失败！" : "修改失败";
			e.printStackTrace();
		}
}
		for (MultipartFile file : fileCovers) {
			if (file.equals(fileCovers.get(0))) {
				Map<String, Object> obj = new HashMap<String, Object>();
				String _oldName = file.getOriginalFilename();// 文件原名
				obj.put("productId", ttyProduct.getProductId());
				obj.put("fileName", _oldName);
				if (ttyAttachService.selectAttachById(obj) != null) {
					ttyBackProductService.deletDetailPicture(obj);
				}
			}
			String ids=Identities.uuid();
			ajaxSavePicture(file, ttyProduct, request,ids);
		}
		if(StringUtils.isEmpty(pdId)) {
			msg = "保存成功！";
		}else {
			ttyProduct.setUpdateUserId(userinfo.getUserId());
			ttyBackProductService.updateByPrimaryKeySelective(ttyProduct);
			msg = "修改成功！";
		}
		String result = "{\"flag\" : " + flag + " , \"msg\" : \"" + msg + "\"}";
		this.renderJson(response, result);
	}

	/**
	 * 多图上传
	 * 
	 * @param fileDetail
	 * @param tymcProduct
	 * @param response
	 * @return
	 */
	public String ajaxSavePicture(MultipartFile fileDetail, TtyProduct ttyProduct, HttpServletRequest request,String id) {
		TsysUserinfo userinfo = (TsysUserinfo) getSession(request, Constant.USER_INFO);// 获取当前登录用户
		TsysAttach attch = new TsysAttach();
		String uId = userinfo.getUserId();
		boolean flag = true;
		BufferedInputStream fis = null;
		FileOutputStream fos = null;
		String FILEURL = null;
		/*
		 * TsysAttach tsysAttach =new TsysAttach(); String
		 * attId=tsysAttach.getAttachId();
		 */
		String oldName = "";
		String suffix = "";
		String newName = "";
		String root = "";
		String path = "";
		String url =null;
		try {
			oldName = fileDetail.getOriginalFilename();// 文件原名
			suffix = oldName.substring(oldName.lastIndexOf("."));// 扩展名
			newName = id + suffix;// 新的文件名
			root = PropertiesUtil.getValue("saveProductFilePath");// 上传的根路径
			path = PropertiesUtil.getValue("productPath");// 上传路径
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			path += sdf.format(new Date()) + "/";
			File f = new File(root + path);
			if (!f.exists()) {// 如果路径不存在，则要创達此路径，每天上传的第一张图像，必须要创達当前的目录
				f.mkdirs();
			}
			if (ttyProduct.getProductId() != null) {
				TtyProduct oldProduct = (TtyProduct) ttyBackProductService.selectDetailById(ttyProduct.getProductId());
				String productCoverPicture = oldProduct.getProductCoverPicture();// 新上传的图像路径
				// 如果此图倮路径存在，则表示之前有上传图倮，执行删除
				if (productCoverPicture != null && !productCoverPicture.isEmpty()) {
					String delPath = root + productCoverPicture;
					File delF = new File(delPath);
					if (!delF.delete()) {// 如果删除失畋，有可能是此图倮正在被访问中，设g为服务停止时删除
						delF.deleteOnExit();
					}
				}
			}

			fis = new BufferedInputStream(fileDetail.getInputStream());
			fos = new FileOutputStream(root + path + newName);
			byte[] bys = new byte[1024];
			int len = 0;
			while ((len = fis.read(bys)) != -1) {
				fos.write(bys, 0, len);
			}
			fos.flush();
			FILEURL = path + newName;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
			 	try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		String msg = null;
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("productId", ttyProduct.getProductId());
			map.put("fileName", oldName);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 url = path + id + suffix;
			TsysAttach attach = ttyAttachService.selectAttachById(map);
			if (attach == null) {
				/*
				 * map.put("productId",ttyProduct.getProductId()); map.put("FILEURL",FILEURL);
				 * map.put("attachId",Identities.uuid());
				 * ttyBackProductService.insertDetailPicture(map);
				 */
				attch.setAttachId(Identities.uuid());
				attch.setCreateUserId(uId);
				attch.setFileName(oldName);
				attch.setFileUrl(url);
				attch.setPkid(ttyProduct.getProductId());
				ttyAttachService.insert(attch);
				msg = "保存成功！";
			} else {
				/*
				 * map.put("productId",ttyProduct.getProductId()); map.put("FILEURL",FILEURL);
				 * ttyBackProductService.updateDetailPicture(map);
				 */
				attch.setUpdateUserId(uId);
				attch.setFileName(oldName);
				attch.setFileUrl(url);
				ttyAttachService.update(attch);
				msg = "修改成功！";
			}

		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		if(flag) {
			return url;
		}
		return null;
	}

	/**
	 * 删除一条或多条数据
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=del")
	public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		String[] productIds = request.getParameterValues("productIds");
		boolean flag = true;
		try {
			for (int i = 0; i < productIds.length; i++) {
				ttyBackProductService.deleteByPrimaryKey(productIds[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		String result = "{\"flag\" : " + flag + "}";
		this.renderJson(response, result);
	}

	/**
	 * 产品上架,下架
	 * 
	 * @param request
	 * @param response
	 * @throws ControllerException
	 */
	@RequestMapping(params = "method=state")
	public void state(HttpServletRequest request, HttpServletResponse response) {
		TtyProduct ttyProduct = new TtyProduct();
		String productState = request.getParameter("productState");
		ttyProduct.setProductState(productState);
		String[] productIds = request.getParameterValues("productIds");
		boolean flag = true;
		try {
			for (int i = 0; i < productIds.length; i++) {
				ttyProduct.setProductId(productIds[i]);
				ttyBackProductService.updateProductStateByPrimaryKey(ttyProduct);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		String result = "{\"flag\" : " + flag + "}";
		this.renderJson(response, result);
	}

}