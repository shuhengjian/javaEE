/**   
 * 功能描述：
 * @Package: com.musicbar.second.front.service 
 * @author: shj 
 * @date: 2019年3月11日 上午10:50:04 
 */
package com.musicbar.second.backstage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicbar.core.utils.StringUtil;
import com.musicbar.second.domain.TAttach;
import com.musicbar.second.domain.TType;
import com.musicbar.second.mapper.TAttachMapper;
import com.musicbar.second.mapper.TTypeMapper;

/**
 * @ClassName: TTypeService.java
 * @Description: 商品分类
 * @version: v1.0.0
 * @author: shj
 * @date: 2019年3月11日 上午10:50:04
 */
@Service
public class TTypeService {
	@Autowired
	private TTypeMapper tTypeMapper;

	@Autowired
	private TAttachMapper attachMapper;//图片
	/**
	 * 查询所有分类
	 */
	public List<TType> selectAll() {
		List<TType> list = new ArrayList<TType>();
		list = tTypeMapper.selectAll();
		return list;
	} 
	/**
	 * 后台管理查询分类状态
	 */
	public List<TType> selectState(){
		return tTypeMapper.selectState();
	}
	/**
	 * 启用，禁用
	 * @param type
	 * @return
	 */
	public int updateState(TType type) {
		return tTypeMapper.updateState(type);
	}
	
	/**
	 * 根据ID查询类型
	 */
	public TType selectByPrimaryKey(String typeId) {
		TType t=new TType();
		t=tTypeMapper.selectByPrimaryKey(typeId);
		return t;
	}
	/**
	 * 后台分类管理查询全部分类
	 * @param type
	 * @return
	 */
	public List<TType> findAll(TType type) {
		return 	tTypeMapper.findAll(type);
	}
	/**后台管理添加分类信息
	 * 
	 * @param type
	 * @param fileUrl
	 * @return
	 */
	public int saveOrUpdate(TType type,TAttach ac) {
		//TAttach ac = new TAttach();
		int success = 0;
			TType t = new TType();
			t.setTypeName(type.getTypeName());
			t.setTypeId(type.getTypeId());
			if(tTypeMapper.selectTypeName(t) > 0) {
				return -1;//分类名称已存在
			}
			t.setTypeName(null);
			t.setTypeSort(type.getTypeSort());
			if(tTypeMapper.selectTypeName(t) >0) {
				return -2;//排序号已存在
			}
			if(type.getTypeId() !=null && ! type.getTypeId().isEmpty()) {
				success = tTypeMapper.updateType(type);
			}else {
				type.setTypeId(StringUtil.getUUIDValue());
				ac.setId(StringUtil.getUUIDValue());
				String pid = type.getTypeId();
				ac.setPkid(pid);
				//ac.setFileUel(fileUrl);
				insert(ac);
				success = tTypeMapper.insertType(type);
			}
		return success;
	}
	/**
	 * 根据id查询商品信息
	 * @param goodsId
	 * @return
	 */
	public TType selectTypeById(String typeId) {
		return tTypeMapper.selectByPrimaryKey(typeId);
	}
	/**
	 * 后台管理添加商品图片
	 * @param record
	 * @return
	 */
	public int insert(TAttach record) {
		record.setId(StringUtil.getUUIDValue());
		return attachMapper.insert(record);
	}
	
	public int insertType(TType type) {
		return tTypeMapper.insertType(type);
		
	}
	/**
	 * 总记录数 
	 * @param typeId
	 * @return
	 */
	public int selectCount(String typeId) {
		return tTypeMapper.selectCount(typeId);
	}
	/**
	 * 删除
	 * @param goodsId
	 * @return
	 */
	public int deleteById(String typeId) {
		return tTypeMapper.deleteByPrimaryKey(typeId);
	}
	/**
	 * 批量删除
	 * @param goodsId
	 * @return
	 */
	public int deleteAll(List<String> typeId) {
		return tTypeMapper.deleteAll(typeId);
	}
	
}
