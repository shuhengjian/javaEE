package com.creatorblue.cbitedu.system.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.constants.Constant;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.system.domain.TsysApp;
import com.creatorblue.cbitedu.system.domain.TsysModule;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;
import com.creatorblue.cbitedu.system.persistence.TsysAppMapper;
import com.creatorblue.cbitedu.system.persistence.TsysModuleMapper;
import com.creatorblue.cbitedu.system.persistence.TsysModuleoperateMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */
@Service(value = "tsysModuleService")
public class TsysModuleService<T> extends BaseService<T> {
    @Autowired
    private TsysModuleMapper<T> mapper; //注入模块数据访问层
    @Autowired
    private TsysAppMapper<TsysApp> appMapper; //注入子应用系统数据访问层
    @Autowired
    private TsysModuleoperateMapper<T> moduleOperaterMapper; //注入模块操作数据访问层

    public TsysModuleoperateMapper<T> getModuleOperaterMapper() {
        return moduleOperaterMapper;
    }

    public TsysModuleMapper<T> getMapper() {
        return mapper;
    }

    public TsysAppMapper<TsysApp> getAppMapper() {
        return appMapper;
    }

    public List selectPageTsysModuleByMap(Map map, TsysUserinfo userinfo)
        throws ServiceException {
        if ((userinfo != null) &&
                !Constant.ADMIN_USER_LOGINID.equals(userinfo.getLoginName())) {
            map.put("userId", userinfo.getUserId());
        }

        return getMapper().selectPageTsysModuleByMap(map);
    }

    public List selectPageTsysModule() throws ServiceException {
        return getMapper().selectPageTsysModule();
    }

    /**
     * 设置默认的菜单节点
     * @return
     * @throws ServiceException
     */
    public TsysModule setdefaultNote() throws ServiceException {
        TsysModule tsysModule = new TsysModule();
        String appID = "";
        List appList = getAppMapper().selectTsysApp(); //应用系统节点

        if (appList.size() > 0) {
            for (int i = 0; i < appList.size(); i++) {
                TsysApp tsyApp = (TsysApp) appList.get(0);
                appID = tsyApp.getAppId();

                break;
            }

            Map paramMap = new HashMap();
            paramMap.put("parentModuleid", 0);
            paramMap.put("display", 0);
            paramMap.put("appId", appID);

            List moduleList = getMapper().selectPageTsysModuleByMap(paramMap);

            if (moduleList.size() > 0) {
                for (int j = 0; j < moduleList.size(); j++) {
                    tsysModule = (TsysModule) moduleList.get(0);

                    break;
                }
            }
        }

        return tsysModule;
    }

    /**
     * 菜单树
     * @return
     * @throws ServiceException
     */
    public List queryModuleTree(TsysUserinfo userinfo)
        throws ServiceException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List appList = getAppMapper().selectPageTsysApp(); //应用系统节点

        for (int i = 0; i < appList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            TsysApp tsyApp = (TsysApp) appList.get(i);
            map.put("id", tsyApp.getAppId());
            map.put("name", tsyApp.getAppName());
            map.put("ismenu", "0");

            List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();

            Map paramMap = new HashMap();
            paramMap.put("parentModuleid", 0);
            paramMap.put("display", 0);
            paramMap.put("appId", tsyApp.getAppId());

            List moduleList = selectPageTsysModuleByMap(paramMap, userinfo); //根据应用子应用id找到菜单模块节点

            for (int j = 0; j < moduleList.size(); j++) {
                TsysModule tsysModule = (TsysModule) moduleList.get(j);

                String ismenu = "1";
                tsysModule.setParentModuleid(tsyApp.getAppId());

                Map<String, Object> map2 = module2Map(tsysModule, ismenu);
                list.add(map2);
                getNode(list, tsysModule.getModuleId(), userinfo);
            }

            list.add(map);
        }

        return list;
    }

    /**
     * 把菜单对象转换成Map
     * @param tsysModule
     * @param ismenu
     * @return
     */
    public Map<String, Object> module2Map(TsysModule tsysModule, String ismenu) {
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("id", tsysModule.getModuleId());
        map2.put("pId", tsysModule.getParentModuleid());
        map2.put("name", tsysModule.getModuleName());
        map2.put("linkAddr", tsysModule.getLinkAddr());
        map2.put("ismenu", ismenu);

        return map2;
    }

    /**
     * 递归菜单子节点
     * @param list
     * @param pid
     */
    private void getNode(List<Map<String, Object>> list, String pid,
        TsysUserinfo userinfo) {
        Map paramMap = new HashMap();
        paramMap.put("parentModuleid", pid);
        paramMap.put("display", 0);

        if ((userinfo != null) &&
                !Constant.ADMIN_USER_LOGINID.equals(userinfo.getLoginName())) {
            paramMap.put("userId", userinfo.getUserId());
        }

        List childModlueList = selectPageTsysModuleByMap(paramMap, userinfo);

        if ((childModlueList != null) && (childModlueList.size() != 0)) {
            for (int k = 0; k < childModlueList.size(); k++) {
                TsysModule childModlue = (TsysModule) childModlueList.get(k);
                Map<String, Object> map3 = module2Map(childModlue, "1");
                list.add(map3);
                getNode(list, childModlue.getModuleId(), userinfo);
            }
        }
    }

    /**
     * 获取模块主菜单  parentId:0
     * @param pid
     * @return
     */
    private TsysModule getParentModule(String pid) {
        TsysModule module = (TsysModule) getMapper().selectDetailById(pid);

        if ((module != null) && !module.getParentModuleid().equals("0")) {
            module = getParentModule(module.getParentModuleid());
        }

        return module;
    }

    /**
     * 根据父节点ID获取上级目录数据
     * @param pid
     * @return
     */
    public TsysModule getModule(String pid) {
        return (TsysModule) getMapper().selectDetailById(pid);
    }

    /**
     * 根据菜单模块parentID获取应用系统的主菜单信息
     * @param tsysModule.parentId
     * @return
     */
    public TsysApp getApp(TsysModule tsysModule) {
        if (!tsysModule.getParentModuleid().equals("0")) {
            tsysModule = getParentModule(tsysModule.getParentModuleid());
        }

        return getAppMapper().selectDetailById(tsysModule.getAppId());
    }

    /**
     * 根据模块编号查询模块信息
     * @param moduleCode
     * @return
     */
    public TsysModule getModuleByCode(String moduleCode) {
        TsysModule t = getMapper().selectModuleByCode(moduleCode);

        return t;
    }

    public List getModuleBycondition(Map map) {
        List list = getMapper().selectModuleBycondition(map);

        return list;
    }
}
