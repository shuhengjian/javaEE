package com.creatorblue.cbitedu.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.core.exception.ServiceException;
import com.creatorblue.cbitedu.system.domain.TsysDict;
import com.creatorblue.cbitedu.system.domain.ZtreeInfo;
import com.creatorblue.cbitedu.system.persistence.TsysDictMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Title: Description: Copyright: Copyright (c) 2014
 * Company:hihsoft.co.,ltd
 * 
 * @author zhujw
 * @version 1.0
 */

@Service(value = "tsysDictService")
public class TsysDictService<T> extends BaseService<T> {
	@Autowired
	private TsysDictMapper<T> mapper;

	// @Autowired允许业务逻辑层调用其他服务
	// private TsysUserService<TsysUser> tsysUserService;

	public TsysDictMapper<T> getMapper() {
		return mapper;
	}

	@CacheEvict(value = { "dict_cache" }, allEntries = true, beforeInvocation = true)
	public void insertTsysDict(T tsysDict) throws ServiceException {
		getMapper().insert(tsysDict);
	}

	@CacheEvict(value = { "dict_cache" }, allEntries = true, beforeInvocation = true)
	public void updateTsysDict(T tsysDict) throws ServiceException {
		getMapper().update(tsysDict);
	}

	@CacheEvict(value = { "dict_cache" }, allEntries = true, beforeInvocation = true)
	public void deleteTsysDict(String id) throws ServiceException {
		getMapper().delete(id);
	}

	public List selectPageTsysDictByMap(Map map) throws ServiceException {
		return getMapper().selectPageTsysDictByMap(map);

	}

	public List checkTsysDictByMap(Map map) throws ServiceException {
		return getMapper().checkTsysDictByMap(map);

	}
	 @Cacheable(value = "dict_cache")
	public List selectPageTsysDict() throws ServiceException {
		return getMapper().selectPageTsysDict();
	}

	public String selectTreeTsysDictByMap(Map map) throws ServiceException {
		List<ZtreeInfo> list = new ArrayList<ZtreeInfo>();

		List<TsysDict> dicts = getMapper().selectPageTsysDictByMap(map);

		for (Iterator<TsysDict> i = dicts.iterator(); i.hasNext();) {
			TsysDict dict = i.next();
			ZtreeInfo treeNode = new ZtreeInfo();
			treeNode.setId(dict.getDictId());
			treeNode.setName(dict.getDictValue());
			treeNode.setPid(dict.getParentType());
			treeNode.setType(dict.getDisplaySort());
			list.add(treeNode);
		}
		String result = null;
		try {
			result = new ObjectMapper().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public String selectTreeSortByMap(Map map) throws ServiceException {
		List<ZtreeInfo> list = new ArrayList<ZtreeInfo>();

		List<TsysDict> dicts = getMapper().selectPageTsysDictByMap(map);

		for (Iterator<TsysDict> i = dicts.iterator(); i.hasNext();) {
			TsysDict dict = i.next();
			ZtreeInfo treeNode = new ZtreeInfo();
			treeNode.setId(dict.getDictId());
			treeNode.setName(dict.getDictName());
			treeNode.setPid(dict.getParentType());
			treeNode.setType(dict.getDisplaySort());
			list.add(treeNode);
		}
		String result = null;
		try {
			result = new ObjectMapper().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Cacheable(value = { "dict_cache" })
	public String selectTreeTsysDictById(String id) throws ServiceException {
		List<ZtreeInfo> list = new ArrayList<ZtreeInfo>();
		Map<String, String> param = new HashMap<String, String>();
		param.put("parentType", id);
		param.put("displaySort", "2");

		List<TsysDict> dicts = selectPageTsysDictByMap(param);

		for (Iterator<TsysDict> i = dicts.iterator(); i.hasNext();) {
			TsysDict dict = i.next();
			ZtreeInfo treeNode = new ZtreeInfo();
			treeNode.setId(dict.getDictId());
			treeNode.setName(dict.getDictValue());
			treeNode.setPid(dict.getParentType());
			treeNode.setType(dict.getDisplaySort());
			list.add(treeNode);
			getNode(list, dict.getDictId());
		}
		String result = null;
		try {
			result = new ObjectMapper().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 递增获取树子结点
	 * 
	 * @param list
	 * @param pid
	 */
	private void getNode(List<ZtreeInfo> list, String pid) {
		Map param = new HashMap();
		param.put("parentType", pid);
		param.put("displaySort", "2");
		List dicts = selectPageTsysDictByMap(param);

		if ((dicts != null) && (dicts.size() != 0)) {
			for (int k = 0; k < dicts.size(); k++) {
				TsysDict dict = (TsysDict) dicts.get(k);
				ZtreeInfo treeNode = new ZtreeInfo();
				treeNode.setId(dict.getDictId());
				treeNode.setName(dict.getDictValue());
				treeNode.setPid(dict.getParentType());
				treeNode.setType(dict.getDisplaySort());
				treeNode.setDict_code(dict.getDictCode());
				list.add(treeNode);
				getNode(list, dict.getDictId());
			}
		}

	}

	/**
	 * @Description:异步树获取数据
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public String asyncSelectTreeTsysDictById(String id)
			throws ServiceException {

		List<ZtreeInfo> list = new ArrayList<ZtreeInfo>();
		Map<String, String> param = new HashMap<String, String>();
		param.put("parentType", id);
		param.put("displaySort", "2");
		List<TsysDict> dicts = selectPageTsysDictByMap(param);
		for (Iterator<TsysDict> i = dicts.iterator(); i.hasNext();) {
			TsysDict dict = i.next();
			ZtreeInfo treeNode = new ZtreeInfo();
			treeNode.setId(dict.getDictId());
			treeNode.setName(dict.getDictValue());
			treeNode.setDict_code(dict.getDictCode());
			if (this.hasChildren(dict)) {
				treeNode.setIsParent(true);
			} else {
				treeNode.setIsParent(false);
			}
			list.add(treeNode);
		}
		String result = null;
		try {
			result = new ObjectMapper().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @Description:判断一个节点有没有子节点
	 * @param treeNode
	 * @return
	 */
	private boolean hasChildren(TsysDict dict) {
		boolean flag = false;
		Map<String, String> param = new HashMap<String, String>();
		param.put("displaySort", "2");
		param.put("parentType", dict.getDictId());
		List<TsysDict> result = this.checkTsysDictByMap(param);
		if(result.size()>0){
			flag = true;
		}
		return flag;
	}
}
