package com.creatorblue.cbitedu.system.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creatorblue.cbitedu.core.baseclass.controller.HihBaseController;
import com.creatorblue.cbitedu.core.exception.ControllerException;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.system.domain.TsysModuleoperate;
import com.creatorblue.cbitedu.system.service.TsysModuleoperateService;


/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */
@Controller
@RequestMapping("/tsysModuleoperateController.do")
public class TsysModuleoperateController extends HihBaseController {
    @Autowired
    private TsysModuleoperateService tsysModuleoperateService;

    /**
     * 模块操作列表
     * @param request
     * @param response
     * @return
     * @throws ControllerException
     */
    @RequestMapping(params = "method=list")
    public ModelAndView list(HttpServletRequest request,
        HttpServletResponse response) throws ControllerException {
        ModelAndView mv = new ModelAndView("/module/moduleoperateform");
        List list = new ArrayList();
        String id = request.getParameter("id");

        try {
            if ((id != null) && !id.equals("")) {
                list = tsysModuleoperateService.queryModuleOperate(id);
            } else {
                throw new Exception("菜单模块编号为空值");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        mv.addObject("moduleoperate", list);
        mv.addObject("module", tsysModuleoperateService.getModule(id));

        return mv;
    }

    /**
     * 保存模块操作
     * @param moduleId
     * @param request
     * @param response
     * @throws ControllerException
     * @throws IOException
     */
    @RequestMapping(params = "method=saveModuleOperate")
    public @ResponseBody
    void saveModuleOperate(@RequestParam
    String moduleId, HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        boolean flag = false;
        String[] operateNameStr = request.getParameterValues("operateName");
        String[] operateCodeStr = request.getParameterValues("operateCode");
        String[] operateIdStr = request.getParameterValues("operateId");

        try {
            if ((moduleId != null) && !moduleId.equals("")) {

                if (operateNameStr != null) {
                    for (int i = 0; i < operateNameStr.length; i++) {
                        TsysModuleoperate mo = new TsysModuleoperate();
                        mo.setOperateId(operateIdStr[i]);
                        mo.setModuleId(moduleId);
                        mo.setOperateName(operateNameStr[i]);
                        mo.setOperateCode(operateCodeStr[i]);
                        tsysModuleoperateService.insertOrUpdateModuleoperate(mo);
                    }
                } else {
                    TsysModuleoperate mo = new TsysModuleoperate();
                    mo.setOperateId(Identities.uuid());
                    mo.setModuleId(moduleId);
                    mo.setOperateId(request.getParameter("operateId"));
                    mo.setOperateName(request.getParameter("operateName"));
                    mo.setOperateCode(request.getParameter("operateCode"));
                    tsysModuleoperateService.insertOrUpdateModuleoperate(mo);
                }
            }

            flag = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            flag = false;
        }

        writeWeb(response, String.valueOf(flag));
    }

    /**
     * 删除一个菜单操作
     * @param operateId
     * @param request
     * @param response
     * @throws ControllerException
     */
    @RequestMapping(params = "method=delModuleOperate")
    public void delModuleOperate(String operateId, HttpServletRequest request,
        HttpServletResponse response) throws ControllerException {
        String r = "1";

        try {
            tsysModuleoperateService.delete(operateId);
        } catch (Exception e) {
            r = "0";
            e.printStackTrace();
        }

        renderText(response, r);
    }
}
