package com.creatorblue.cbitedu.system.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.system.persistence.TsysParameterMapper;

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
@Service(value = "tsysParameterService")
public class TsysParameterService<T> extends BaseService<T> {
    @Autowired
    private TsysParameterMapper<T> mapper;

    public TsysParameterMapper<T> getMapper() {
        return mapper;
    }

    public List selectPageTsysParameterByMap(Map map) throws ServiceException {
        return getMapper().selectPageTsysParameterByMap(map);
    }
    
    public List checkTsysParameterByMap(Map map) throws ServiceException {
        return getMapper().checkTsysParameterByMap(map);
    }

    public List selectPageTsysParameter() throws ServiceException {
        return getMapper().selectPageTsysParameter();
    }

    @Cacheable(value = "parameter_cache")
    public List selectAllTsysParameter() throws ServiceException {
        List paralist = getMapper().selectPageTsysParameter();
        log.info("返回的参数列表" + paralist.size());

        return paralist;
    }

    @CacheEvict(allEntries = true, value =  {
        "parameter_cache"}
    )
    public void update(T t) throws ServiceException {
        super.update(t);
    }

    @CacheEvict(allEntries = true, value =  {
        "parameter_cache"}
    )
    public void insert(T t) throws ServiceException {
        super.insert(t);
    }

    @CacheEvict(allEntries = true, value =  {
        "parameter_cache"}
    )
    public int insertBatch(List<T> list) {
        return super.insertBatch(list);
    }

    @CacheEvict(allEntries = true, value =  {
        "parameter_cache"}
    )
    public void updateBySelective(T t) {
        super.update(t);
    }

    @CacheEvict(allEntries = true, value =  {
        "parameter_cache"}
    )
    public void delete(String... ids) throws ServiceException {
        super.delete(ids);
    }
}
