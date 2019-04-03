package com.creatorblue.cbitedu.system.service;

import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.util.CollectionUtils;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.constants.Constant;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.core.utils.Identities;
import com.creatorblue.cbitedu.system.domain.TsysModule;
import com.creatorblue.cbitedu.system.domain.TsysModuleoperate;
import com.creatorblue.cbitedu.system.domain.TsysRoleprivilege;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;
import com.creatorblue.cbitedu.system.persistence.TsysModuleMapper;
import com.creatorblue.cbitedu.system.persistence.TsysModuleoperateMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2014
 * </p>
 * <p>
 * Company:hihsoft.co.,ltd
 * </p>
 *
 * @author zhujw
 * @version 1.0
 */
@Service(value = "tsysModuleoperateService")
public class TsysModuleoperateService extends BaseService<TsysModuleoperate> {
    @Autowired
    private TsysModuleoperateMapper<TsysModuleoperate> mapper;
    @Autowired
    private TsysModuleMapper<TsysModule> moduleMapper; // 注入模块数据访问层

    public TsysModuleMapper<TsysModule> getModuleMapper() {
        return moduleMapper;
    }

    public TsysModuleoperateMapper<TsysModuleoperate> getMapper() {
        return mapper;
    }

    public List selectPageTsysModuleoperateByMap(Map map)
        throws ServiceException {
        return getMapper().selectPageTsysModuleoperateByMap(map);
    }

    public List selectPageTsysModuleoperate(TsysUserinfo userinfo)
        throws ServiceException {
        if (Constant.ADMIN_USER_LOGINID.equals(userinfo.getLoginName())) {
            userinfo = null;
        }

        return getMapper().selectPageTsysModuleoperate(userinfo);
    }

    /**
     * 根据模块id删除操作信息
     *
     * @param moduleId
     */
    public void deleteByModuleId(String moduleId) {
        getMapper().deleteByModuleID(moduleId);
    }

    /**
     * 根据模块获取模块操作
     *
     * @param moduleId
     * @return
     * @throws ServiceException
     */
    public List queryModuleOperate(String moduleId) throws ServiceException {
        return getMapper().selectModuleoperateByMId(moduleId);
    }

    /**
     * 根据模块ID获取模块信息
     *
     * @param moduleID
     * @return
     */
    public TsysModule getModule(String moduleID) {
        return getModuleMapper().selectDetailById(moduleID);
    }

    /**
     * 按用户查询模块信息
     * @param userId
     * @return
     */
    public List<Map> selectModuleoperateByUser(TsysUserinfo userinfo) {
        if (Constant.ADMIN_USER_LOGINID.equals(userinfo.getLoginName())) {
            return getMapper().selectModuleoperateByUser(null);
        }

        return getMapper().selectModuleoperateByUser(userinfo);
    }

    /**
     * 更新或新增菜单操作
     * @param moduleoperates
     */
    public void insertOrUpdateModuleoperate(TsysModuleoperate... moduleoperates) {
        if ((moduleoperates != null) && (moduleoperates.length > 0)) {
            for (TsysModuleoperate mo : moduleoperates) {
                if (StringUtils.isEmpty(mo.getOperateId())) {
                    mo.setOperateId(Identities.uuid());
                    insert(mo);
                } else {
                    update(mo);
                }
            }
        }
    }

    /**
     * 生成菜单和操作组成的选择树
     * @param list 菜单列表
     * @param opList 操作列表
     * @param rpList 角色-权限关联列表
     * @return
     */
    public List<Map> genModuleOpTree(List<Map> list,
        List<TsysModuleoperate> opList, List<TsysRoleprivilege> rpList) {
        List<Map> opMapList = new ArrayList<Map>(32);

        for (Map module : list) {
            final String moduleId = module.get("id").toString();

            if (!CollectionUtils.isEmpty(rpList)) {
                //设置菜单选择状态
                for (TsysRoleprivilege tsysRoleprivilege : rpList) {
                    if (moduleId.equals(tsysRoleprivilege.getModuleId())) {
                        module.put("checked", "true");
                    }
                }
            }

            //添加操作到菜单下面
            for (TsysModuleoperate tsysModuleoperate : opList) {
                String opModuleId = tsysModuleoperate.getModuleId();

                if (opModuleId.equals(moduleId)) {
                    Map opModuleMap = new HashMap();
                    opModuleMap.put("id", tsysModuleoperate.getOperateId());
                    opModuleMap.put("name", tsysModuleoperate.getOperateName());
                    opModuleMap.put("pId", tsysModuleoperate.getModuleId());
                    opModuleMap.put("isop", "1");

                    final String operateId = tsysModuleoperate.getOperateId();

                    if (!CollectionUtils.isEmpty(rpList)) {
                        for (TsysRoleprivilege tsysRoleprivilege : rpList) { //设置操作的选中状态

                            if (operateId.equals(
                                        tsysRoleprivilege.getOperateId())) {
                                opModuleMap.put("checked", "true");
                            }
                        }
                    }

                    opMapList.add(opModuleMap);
                }
            }
        }

        list.addAll(opMapList);

        return list;
    }
}
