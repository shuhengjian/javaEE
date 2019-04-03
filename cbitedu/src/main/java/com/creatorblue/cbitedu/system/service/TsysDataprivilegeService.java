package com.creatorblue.cbitedu.system.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.system.domain.TsysDataprivilege;
import com.creatorblue.cbitedu.system.domain.TsysOrg;
import com.creatorblue.cbitedu.system.persistence.TsysDataprivilegeMapper;

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
@Service(value = "tsysDataprivilegeService")
public class TsysDataprivilegeService<T> extends BaseService<T> {
    @Autowired
    private TsysDataprivilegeMapper<T> mapper;

    //@Autowired允许业务逻辑层调用其他服务
    //private TsysUserService<TsysUser> tsysUserService;
    public TsysDataprivilegeMapper<T> getMapper() {
        return mapper;
    }

    public List selectPageTsysDataprivilegeByMap(Map map)
        throws ServiceException {
        return getMapper().selectPageTsysDataprivilegeByMap(map);
    }

    public List selectPageTsysDataprivilege(TsysDataprivilege dataprivilege)
        throws ServiceException {
        return getMapper().selectPageTsysDataprivilege(dataprivilege);
    }

    /**
     * 查询数量
     * @param org
     * @return
     */
    public int selectDataprivilegeNum(TsysOrg org) {
        return getMapper().selectDataprivilegeNum(org);
    }
}
