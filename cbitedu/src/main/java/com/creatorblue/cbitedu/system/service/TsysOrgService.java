package com.creatorblue.cbitedu.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.core.interceptor.AConfig;
import com.creatorblue.cbitedu.system.domain.TsysOrg;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;
import com.creatorblue.cbitedu.system.persistence.TsysOrgMapper;


/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */
@Service(value = "tsysOrgService")
public class TsysOrgService<T> extends BaseService<T> {
    @Autowired
    private TsysOrgMapper<T> mapper;

    //@Autowired允许业务逻辑层调用其他服务
    //private TsysUserService<TsysUser> tsysUserService;
    public TsysOrgMapper<T> getMapper() {
        return mapper;
    }

    public List selectPageTsysOrgByMap(Map map) throws ServiceException {
        return getMapper().selectPageTsysOrgByMap(map);
    }

    public List selectTsysOrgByUser(TsysUserinfo userinfo) throws ServiceException {
        Map map = new HashMap();
        map.put("userId", userinfo.getUserId());

        return getMapper().selectPageTsysOrgByMap(map);
    }

    public List selectPageTsysOrg() throws ServiceException {
        return getMapper().selectPageTsysOrg();
    }

    /**
     * 查询机构树
     * @return
     * @throws ServiceException
     */
    //@Cacheable(value={"orginfo_cache"})
    public List selectSysOrgInfo(TsysOrg tsysOrg) throws ServiceException {
    	log.info("缓存开始");
    	AConfig aConfig = new AConfig("CREATOR", "ORG_ID");
    	System.out.println("**********"+tsysOrg.getState());
    	tsysOrg.addAConfigs(aConfig);
        return getMapper().selectSysOrgInfo(tsysOrg);
    }

    /**
     * 查询机构树
     * @return
     * @throws ServiceException
     */
   
    public String selectSysSequInfo() throws ServiceException {
        return getMapper().selectSysSequInfo();
    }

    public List<T> selectSysSeqMaxId() throws ServiceException {
        return getMapper().selectSysSeqMaxId();
    }
    //@CacheEvict(value = {"orginfo_cache"}, allEntries = true,beforeInvocation=true)
    public void insert(T tsysOrg, String maxId) throws ServiceException {
    	log.info("清除缓存");
        getMapper().insert(tsysOrg);
        //getMapper().updateSeq(maxId);
    }

    public String selectSysOrgAncesty(String orgId) throws ServiceException {
        return getMapper().selectSysOrgAncesty(orgId);
    }
    
    public TsysOrg selectOrgIdByOrgObject(Map para) throws ServiceException {
    	return getMapper().selectOrgIdByOrgObject(para);
    }
    
    public String selectChildCount(String orgId) throws ServiceException {
    	return getMapper().selectChildCount(orgId);
    }
    //@CacheEvict(value = {"orginfo_cache"}, allEntries = true,beforeInvocation=true)
    public void updateSeqByDel() throws ServiceException {
   	 getMapper().updateSeqByDel();
   }
    
    public List selectChildById(String orgIdString) throws ServiceException{
    	return getMapper().selectChildById(orgIdString);
    	
    }
    
    public Integer selectOrgCount() throws ServiceException{
    	
    	return getMapper().selectOrgCount();
    }
   // @CacheEvict(value = {"orginfo_cache"}, allEntries = true,beforeInvocation=true)
    public void updateBySort(TsysOrg tsysOrg) throws ServiceException{
    	 getMapper().updateBySort(tsysOrg);
    }
    //@CacheEvict(value = {"orginfo_cache"}, allEntries = true,beforeInvocation=true)
    public void updateSortId(Integer sortId)throws ServiceException{
    	getMapper().updateSortId(sortId);
    	
    }
   // @CacheEvict(value = {"orginfo_cache"}, allEntries = true,beforeInvocation=true)
    public void updateStatus(String orgId)throws ServiceException{
    	getMapper().updateStatus(orgId);
    	
    }
}
