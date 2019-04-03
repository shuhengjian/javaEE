package com.creatorblue.cbitedu.system.controller;

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
import com.creatorblue.cbitedu.core.exception.ControllerException;
import com.creatorblue.cbitedu.core.page.Page;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.system.domain.TsysParameter;
import com.creatorblue.cbitedu.system.service.TsysParameterService;

/**
 * @ClassName: TsysParameterController
 * @Description: 参数管理控制器
 * @author baihy
 * @date 2014年7月22日 下午3:13:28
 */
@Controller
@RequestMapping("/tsysParameterController.do")
public class TsysParameterController extends HihBaseController {
	@Autowired
	private TsysParameterService<TsysParameter> tsysParameterService;

	/**
	 * 访问列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=list")
	public ModelAndView list(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/parameter/tsysparameterlist");
		return mv;
	}

	/**
	 * 查询方法
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "method=query")
	public void query(HttpServletRequest request, HttpServletResponse response) {
		Page page = this.getPage(request);
		Map<String, Object> param = WebUtils.getParametersStartingWith(request,
				"para_");
		param.put("page", page);
		List<TsysParameter> list = tsysParameterService.selectPageTsysParameterByMap(param);
		renderJson(list, page, response);
	}

	/**
	 * 進入新增頁面
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=add")
	public ModelAndView add(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		return new ModelAndView("/parameter/tsysparameterform");
	}

	/**
	 * 修改
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=modify")
	public ModelAndView modify(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/parameter/tsysparameterform");
		String id = request.getParameter("id");
		TsysParameter tsysParameter = (TsysParameter) tsysParameterService
				.selectDetailById(id);
		mv.addObject("tsysParameter", tsysParameter);
		return mv;
	}

	/**
	 * 查看记录详细
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=view")
	public ModelAndView view(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String id = request.getParameter("id");
		TsysParameter tsysParameter = (TsysParameter) tsysParameterService
				.selectDetailById(id);
		this.setValue(request, tsysParameter);
		return new ModelAndView("/parameter/tsysparameterform");
	}

	/**
	 * 保存
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		TsysParameter tsysParameter = new TsysParameter();
		this.setValue(request, tsysParameter);
		tsysParameterService.insert(tsysParameter);
		return new ModelAndView(new RedirectView(request.getContextPath()
				+ "/tsysParameterController.do?method=list"));
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
		TsysParameter tsysParameter = new TsysParameter();
		this.setValue(request, tsysParameter);
		boolean flag = true;
		String msg = null;
		List<TsysParameter> resultCheck = new ArrayList<TsysParameter>();
		try {
			if (!StringUtils.isEmpty(tsysParameter.getParaKey())) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("paraKey", tsysParameter.getParaKey());
				resultCheck = tsysParameterService.checkTsysParameterByMap(map);
				resultCheck.remove(tsysParameter);
			}
			if (resultCheck.size() <= 0) {

				if (StringUtils.isEmpty(tsysParameter.getParaid())) {
					tsysParameter.setParaid(Identities.uuid());
					tsysParameterService.insert(tsysParameter);
					msg = "保存成功！";
				} else {
					tsysParameterService.update(tsysParameter);
					msg = "修改成功！";
				}
			} else {
				flag = false;
				msg = "字典值已经存在，请重新输入字典值！";
			}
		} catch (Exception e) {
			flag = false;
			msg = StringUtils.isEmpty(tsysParameter.getParaid()) ? "保存失败！"
					: "修改失败";
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
		String[] paraIds = request.getParameterValues("paraIds");
		boolean flag = true;
		try {
			for (int i = 0; i < paraIds.length; i++) {
				tsysParameterService.delete(paraIds[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		String result = "{\"flag\" : " + flag + "}";
		this.renderJson(response, result);
	}
}
