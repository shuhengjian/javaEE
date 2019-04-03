package com.creatorblue.cbitedu.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.WebUtils;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.system.domain.TsysPost;
import com.creatorblue.cbitedu.system.domain.ZtreeInfo;
import com.creatorblue.cbitedu.system.service.TsysPostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Title: Description: Copyright: Copyright (c) 2014
 * Company:hihsoft.co.,ltd
 * 
 * @author baihy
 * @version 1.0
 */

@Controller
@RequestMapping("/tsysPostController.do")
public class TsysPostController extends HihBaseController {
	@Autowired
	private TsysPostService<TsysPost> tsysPostService;

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
		ModelAndView mv = new ModelAndView("/post/tsyspostlist");
		return mv;
	}

	/**
	 * 访问列表， 查询数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=query")
	public void query(HttpServletRequest request, HttpServletResponse response) {
		Page page = this.getPage(request);
		Map<String, Object> param = WebUtils.getParametersStartingWith(request,
				"post_");
		param.put("page", page);
		List<TsysPost> list = tsysPostService.selectPageTsysPostByMap(param);
		this.convertParam(list, "postType", "postType");

		// 取出所用的父岗位的id和name
		List<TsysPost> listParent = tsysPostService
				.selectPageTsysPostByMap(new HashMap<String, Object>());
		Map<String, String> params = new HashMap<String, String>();
		for (Iterator<TsysPost> i = listParent.iterator(); i.hasNext();) {
			TsysPost post = i.next();
			params.put(post.getPostId(), post.getPostName());
		}

		for (Iterator<TsysPost> j = list.iterator(); j.hasNext();) {
			TsysPost post = j.next();
			post.setParentPostName(params.get(post.getParentPostid()) != null ? params
					.get(post.getParentPostid()) : "");
		}

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

		List<TsysPost> list = tsysPostService.selectPageTsysPostByMap(null);
		List<ZtreeInfo> treeList = new ArrayList<ZtreeInfo>();
		for (Iterator<TsysPost> i = list.iterator(); i.hasNext();) {
			TsysPost post = i.next();
			ZtreeInfo tree = new ZtreeInfo();
			tree.setId(post.getPostId());
			tree.setName(post.getPostName());
			tree.setPid(post.getParentPostid());
			treeList.add(tree);
		}
		String treeListStr = null;
		try {
			treeListStr = new ObjectMapper().writeValueAsString(treeList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		mav.addObject("zNodes", treeListStr);
		mav.setViewName("/post/tsyspostform");
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
		ModelAndView mav = new ModelAndView("/post/tsyspostform");

		String id = request.getParameter("id");
		TsysPost tsysPost = (TsysPost) tsysPostService.selectDetailById(id);

		List<TsysPost> list = tsysPostService.selectPageTsysPostByMap(null);
		list.remove(tsysPost); // 去掉数据本身
		// 移除子类
		List<TsysPost> posts = new ArrayList<TsysPost>();
		this.removeChildren(tsysPost.getPostId(), posts);
		list.removeAll(posts);
		
		List<ZtreeInfo> treeList = new ArrayList<ZtreeInfo>();
		for (Iterator<TsysPost> i = list.iterator(); i.hasNext();) {
			TsysPost post = i.next();
			ZtreeInfo tree = new ZtreeInfo();
			tree.setId(post.getPostId());
			tree.setName(post.getPostName());
			tree.setPid(post.getParentPostid());
			treeList.add(tree);
		}
		String treeListStr = null;
		try {
			treeListStr = new ObjectMapper().writeValueAsString(treeList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		mav.addObject("zNodes", treeListStr);

		// 取出所用的父岗位的id和name
		List<TsysPost> listParent = tsysPostService
				.selectPageTsysPostByMap(new HashMap<String, Object>());
		Map<String, String> params = new HashMap<String, String>();
		for (Iterator<TsysPost> i = listParent.iterator(); i.hasNext();) {
			TsysPost post = i.next();
			params.put(post.getPostId(), post.getPostName());
		}

		tsysPost.setParentPostName(params.get(tsysPost.getParentPostid()));
		mav.addObject("tsysPost", tsysPost);
		return mav;
	}

	/**
	 * @Description:根据parentPostid，找到子类，子类的子类。。。
	 * @param parentPostid
	 * @return
	 */
	private void removeChildren(String parentPostid, List<TsysPost> posts){
		Map<String, String> param = new HashMap<String, String>();
		param.put("parentPostid", parentPostid);
		List<TsysPost> rlist = tsysPostService.selectPageTsysPostByMap(param);
		posts.addAll(rlist);
		for(Iterator<TsysPost> i = rlist.iterator(); i.hasNext();){
			TsysPost post = i.next();
			removeChildren(post.getPostId(), posts);
		}
	}
	
	/**
	 * 查看记录详细
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=view")
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		TsysPost tsysPost = (TsysPost) tsysPostService.selectDetailById(id);
		this.setValue(request, tsysPost);
		return new ModelAndView("/post/tsyspostform");
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) {
		TsysPost tsysPost = new TsysPost();
		this.setValue(request, tsysPost);
		if (StringUtils.isEmpty(tsysPost.getPostId())) {
			tsysPost.setPostId(Identities.uuid());
			tsysPostService.insert(tsysPost);
		} else {
			tsysPostService.update(tsysPost);
		}
		return new ModelAndView(new RedirectView(request.getContextPath()
				+ "/tsysPostController.do?method=list"));
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
		TsysPost tsysPost = new TsysPost();
		this.setValue(request, tsysPost);
		boolean flag = true;
		String msg = null;
		try {
			if (!StringUtils.isEmpty(tsysPost.getParentPostid())
					&& !StringUtils.isEmpty(tsysPost.getPostName())) {
				List result = tsysPostService
						.checkTheNameWithParentId(tsysPost);

				if (result != null && result.size() > 0) {
					msg = "所选父节点下已经存在相同名称的岗位,请重新输入岗位名称！";
					String checkResult = "{\"flag\" : " + false
							+ " , \"msg\" : \"" + msg + "\"}";
					this.renderJson(response, checkResult);
					return;
				}

			}
			if (StringUtils.isEmpty(tsysPost.getPostId())) {
				tsysPost.setPostId(Identities.uuid());
				tsysPostService.insert(tsysPost);
				msg = "保存成功！";
			} else {
				tsysPostService.update(tsysPost);
				msg = "修改成功！";
			}

		} catch (Exception e) {
			flag = false;
			msg = StringUtils.isEmpty(tsysPost.getPostId()) ? "保存失败！" : "修改失败";
			e.printStackTrace();
		}
		String result = "{\"flag\" : " + flag + " , \"msg\" : \"" + msg + "\"}";
		this.renderJson(response, result);
	}

	/**
	 * 删除一条或多条数据
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=del")
	public void deleteById(HttpServletRequest request,
			HttpServletResponse response) {
		String[] postIds = request.getParameterValues("postIds");
		boolean flag = true;
		try {
			for (int i = 0; i < postIds.length; i++) {
				tsysPostService.delete(postIds[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		String result = "{\"flag\" : " + flag + "}";
		this.renderJson(response, result);
	}
}
