package com.creatorblue.cbitedu.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.creatorblue.cbitedu.system.domain.TsysDict;
import com.creatorblue.cbitedu.system.service.TsysDictService;

/**
 * @ClassName: TsysDictController
 * @Description: 字典管理模块控制器
 * @author baihy
 * @date 2014年7月23日 下午2:04:30
 */
@Controller
@RequestMapping("/tsysDictController.do")
public class TsysDictController extends HihBaseController {
	@Autowired
	private TsysDictService<TsysDict> tsysDictService;

	/**
	 * 访问列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/dict/tsysdictlist");
		return mv;
	}

	/**
	 * 字典树专用
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=initTree")
	public void queryTree(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> param = WebUtils.getParametersStartingWith(request,
				"dict_");
		String treeResult = tsysDictService.selectTreeSortByMap(param);
		try {
			this.writeWeb(response, treeResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 访问列表类型
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=dictlist")
	public ModelAndView dictList(HttpServletRequest request,
			HttpServletResponse response) {
		String tree_id = request.getParameter("tree_id");
		String displaySort = request.getParameter("type");
		ModelAndView mv = new ModelAndView("/dict/dictlist");
		mv.addObject("tree_id", tree_id);
		mv.addObject("displaySort", displaySort);
		return mv;
	}

	/**
	 * 访问树形类型
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=dicttree")
	public ModelAndView dictTree(HttpServletRequest request,
			HttpServletResponse response) {
		String tree_id = request.getParameter("tree_id");
		String displaySort = request.getParameter("type");
		ModelAndView mv = new ModelAndView("/dict/dicttree");
		mv.addObject("tree_id", tree_id);
		mv.addObject("displaySort", displaySort);
		return mv;

	}

	/**
	 * 获取树数据
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=dicttreedata")
	public void queryTreeData(HttpServletRequest request,
			HttpServletResponse response) {
		String tree_id = request.getParameter("tree_id");
		String treeResult = tsysDictService.selectTreeTsysDictById(tree_id);
		try {
			this.writeWeb(response, treeResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取异步树的数据
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=asyncDictTreeData")
	public void AsyncQueryTreeData(HttpServletRequest request,
			HttpServletResponse response) {
		String tree_id = request.getParameter("id");
		String type = request.getParameter("type");
		String id = (tree_id == null) ? type : tree_id;
		String treeResult = tsysDictService.asyncSelectTreeTsysDictById(id);
		try {
			this.writeWeb(response, treeResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 访问列表类型
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=dictlistdata")
	public void dictListData(HttpServletRequest request,
			HttpServletResponse response) {
		Page page = this.getPage(request);
		Map<String, Object> param = WebUtils.getParametersStartingWith(request,
				"dict_");
		param.put("page", page);
		List<TsysDict> list = tsysDictService.selectPageTsysDictByMap(param);
		renderJson(list, page, response);
	}

	/**
	 * 访问列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=query")
	public void query(HttpServletRequest request, HttpServletResponse response) {
		Page page = this.getPage(request);
		Map<String, Object> param = WebUtils.getParametersStartingWith(request,
				"dict_");
		List<TsysDict> list = tsysDictService.selectPageTsysDictByMap(param);
		renderJson(list, page, response);
	}

	/**
	 * 進入新增頁面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=add")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String pid = request.getParameter("tree_id");
		TsysDict tsysDict = new TsysDict();
		tsysDict.setParentType(pid);
		mav.addObject("tsysDict", tsysDict);
		mav.setViewName("/dict/tsysdictform");
		return mav;
	}

	@RequestMapping(params = "method=addtree")
	public ModelAndView addTree(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String pid = request.getParameter("tree_id");
		String type = request.getParameter("type");
		
		TsysDict dict = tsysDictService.selectDetailById(pid);
		
		TsysDict tsysDict = new TsysDict();
		tsysDict.setParentType(pid);
		tsysDict.setDisplaySort(type);
		tsysDict.setDictName(dict.getDictName());
		tsysDict.setDictType(dict.getDictType());
		
		mav.addObject("tsysDict", tsysDict);
		mav.setViewName("/dict/dicttreeform");
		return mav;
	}

	/**
	 * 添加列表类型字典数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=addlist")
	public ModelAndView addList(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String pid = request.getParameter("tree_id");
		
		TsysDict dict = tsysDictService.selectDetailById(pid);
		
		String displaySort = request.getParameter("displaySort");
		TsysDict tsysDict = new TsysDict();
		tsysDict.setParentType(pid);// 把新增传过来的id赋给父类id
		tsysDict.setDisplaySort(displaySort);
		tsysDict.setDictName(dict.getDictName());
		tsysDict.setDictType(dict.getDictType());
		mav.addObject("tsysDict", tsysDict);
		mav.setViewName("/dict/dictlistform");
		return mav;
	}

	@RequestMapping(params = "method=modifylist")
	public ModelAndView modifyList(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/dict/dictlistform");
		String id = request.getParameter("id");
		TsysDict tsysDict = (TsysDict) tsysDictService.selectDetailById(id);
		mav.addObject("tsysDict", tsysDict);
		mav.addObject("option", "update");
		return mav;
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "method=modify")
	public ModelAndView modify(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/dict/tsysdictform");
		String id = request.getParameter("id");
		TsysDict tsysDict = (TsysDict) tsysDictService.selectDetailById(id);
		mav.addObject("tsysDict", tsysDict);
		mav.addObject("option", "update");
		return mav;
	}

	@RequestMapping(params = "method=modifytree")
	public ModelAndView modifyTree(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/dict/dicttreeform");
		String id = request.getParameter("id");
		TsysDict tsysDict = (TsysDict) tsysDictService.selectDetailById(id);
		mav.addObject("tsysDict", tsysDict);
		return mav;
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
		TsysDict tsysDict = (TsysDict) tsysDictService.selectDetailById(id);
		this.setValue(request, tsysDict);
		return new ModelAndView("/dict/tsysdictform");
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
		TsysDict tsysDict = new TsysDict();
		this.setValue(request, tsysDict);
		tsysDictService.insertTsysDict(tsysDict);
		return new ModelAndView(new RedirectView(request.getContextPath()
				+ "/tsysDictController.do?method=list"));
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
		TsysDict tsysDict = new TsysDict();
		this.setValue(request, tsysDict);
		//tsysDict.setDictValue(tsysDict.getDictName());
		boolean flag = true;
		String msg = null;
		List<TsysDict> resultCheck = new ArrayList<TsysDict>();
		try {
			if (!StringUtils.isEmpty(tsysDict.getDictType())) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("dictType", tsysDict.getDictType());
				map.put("parentType", tsysDict.getParentType());
				resultCheck = tsysDictService.checkTsysDictByMap(map);
				resultCheck.remove(tsysDict);
			}
			if (resultCheck.size() <= 0) {
				if (StringUtils.isEmpty(tsysDict.getDictId())) {
					tsysDict.setDictId(Identities.uuid());
					tsysDictService.insertTsysDict(tsysDict);
					msg = "保存成功！";
				} else {
					tsysDictService.updateTsysDict(tsysDict);
					msg = "修改成功！";
				}
			} else {
				flag = false;
				msg = "字典类型已经存在，请重新输入字典类型！";
			}
		} catch (Exception e) {
			flag = false;
			msg = StringUtils.isEmpty(tsysDict.getDictId()) ? "保存失败！" : "修改失败";
			e.printStackTrace();
		}
		String result = "{\"flag\" : " + flag + " , \"msg\" : \"" + msg + "\"}";
		this.renderJson(response, result);
	}

	/**
	 * 异步提交表单， 保存数据。
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=ajaxSaveData")
	public void dataSave(HttpServletRequest request,
			HttpServletResponse response) {
		TsysDict tsysDict = new TsysDict();
		this.setValue(request, tsysDict);
		boolean flag = true;
		String msg = null;
		List<TsysDict> resultCheck = new ArrayList<TsysDict>();
		try {
			if (!StringUtils.isEmpty(tsysDict.getDictValue())) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("dictValue", tsysDict.getDictValue());
				resultCheck = tsysDictService.checkTsysDictByMap(map);
				resultCheck.remove(tsysDict);
			}
			if (resultCheck.size() <= 0) {
				if (StringUtils.isEmpty(tsysDict.getDictId())) {
					tsysDict.setDictId(Identities.uuid());
					tsysDictService.insertTsysDict(tsysDict);
					msg = "保存成功！";
				} else {
					tsysDictService.updateTsysDict(tsysDict);
					msg = "修改成功！";
				}
			} else {
				flag = false;
				msg = "字典值已经存在，请重新输入字典值！";
			}
		} catch (Exception e) {
			flag = false;
			msg = StringUtils.isEmpty(tsysDict.getDictId()) ? "保存失败！" : "修改失败";
			e.printStackTrace();
		}
		String result = "{\"flag\" : " + flag + " , \"msg\" : \"" + msg + "\"}";
		this.renderJson(response, result);
	}

	
	@RequestMapping(params = "method=ajaxSaveDataTree")
	public void dataTreeSave(HttpServletRequest request,
			HttpServletResponse response) {
		TsysDict tsysDict = new TsysDict();
		this.setValue(request, tsysDict);
		boolean flag = true;
		String msg = null;
		List<TsysDict> resultCheck = new ArrayList<TsysDict>();
		try {
			if (!StringUtils.isEmpty(tsysDict.getDictValue())) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("dictValue", tsysDict.getDictValue());
				map.put("parentType", tsysDict.getParentType());
				resultCheck = tsysDictService.checkTsysDictByMap(map);
				resultCheck.remove(tsysDict);
			}
			if (resultCheck.size() <= 0) {
				if (StringUtils.isEmpty(tsysDict.getDictId())) {
					tsysDict.setDictId(Identities.uuid());
					tsysDictService.insertTsysDict(tsysDict);
					msg = "保存成功！";
				} else {
					tsysDictService.updateTsysDict(tsysDict);
					msg = "修改成功！";
				}
			} else {
				flag = false;
				msg = "字典值已经存在，请重新输入字典值！";
			}
		} catch (Exception e) {
			flag = false;
			msg = StringUtils.isEmpty(tsysDict.getDictId()) ? "保存失败！" : "修改失败";
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
		String[] dictIds = request.getParameterValues("dictIds");
		boolean flag = true;
		try {
			for (int i = 0; i < dictIds.length; i++) {
				tsysDictService.deleteTsysDict(dictIds[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		String result = "{\"flag\" : " + flag + "}";
		this.renderJson(response, result);
	}

	@RequestMapping(params = "method=hasChildren")
	public void selectChildrenById(HttpServletRequest request,
			HttpServletResponse response) {

		boolean flag = true;
		Map<String, Object> param = WebUtils.getParametersStartingWith(request,
				"dict_");
		try {
			List<TsysDict> dicts = tsysDictService
					.selectPageTsysDictByMap(param);
			if (dicts.size() >= 1) {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String result = "{\"flag\" : " + flag + "}";
		this.renderJson(response, result);
	}
}
