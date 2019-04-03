package com.musicbar.second.backstage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicbar.core.utils.PinYinDemo;
import com.musicbar.core.utils.StringUtil;
import com.musicbar.second.domain.TActivityInfo;
import com.musicbar.second.domain.TAttach;
import com.musicbar.second.mapper.TActivityInfoMapper;
import com.musicbar.second.mapper.TAttachMapper;

/**
 * 活动功能业务层
 * @author xzt
 *
 */
@Service
public class TActivityInfoService { 
	@Autowired
	private TActivityInfoMapper mapper;
	
	@Autowired
	private TAttachMapper attachMapper;//图片
	/**
	 * 查询全部活动信息
	 * @return
	 */
	public List<TActivityInfo> selectAll(TActivityInfo activ) {
		return mapper.selectAll(activ); 
	}
	/**
	 * 查询活动状态
	 */
	public List<TActivityInfo> selectState(){
		return mapper.selectState();
	}
	/**
	 * 添加页面查询活动状态
	 */
	public List<TActivityInfo> selectStates(){
		return mapper.selectStates();
	}
	/**
	 * 后台管理添加活动信息
	 * @param goods
	 * @return
	 */
	public int saveOrUpdate(TActivityInfo active,String fileUrl) {
		TAttach ac = new TAttach();
		PinYinDemo pin = new PinYinDemo();
		int success = 0;
		TActivityInfo activ = new TActivityInfo();
		activ.setActivTheme(active.getActivTheme());
		activ.setActivTheme(active.getActivTheme());
		if(mapper.selectActivTheme(activ) > 0) {
			return -1;//活动主题已存在
		}
		if(active.getActivId() !=null && ! active.getActivId().isEmpty()) {
			String spell = active.getActivTheme();
			success = mapper.updateActive(active);
		}else {
			active.setActivId(StringUtil.getUUIDValue());
			String spell = active.getActivTheme();
			ac.setId(StringUtil.getUUIDValue());
			String pid = active.getActivId();
			ac.setPkid(pid);
			ac.setFileUel(fileUrl);
			insert(ac);
			success = mapper.insertActive(active);
		}
		return success;
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
    /**
	 * 根据id查询活动信息
	 * @param activId
	 * @return
	 */
	public TActivityInfo selectActiveById(String activId) {
		return mapper.selectByPrimaryKey(activId);
	}
	/**
	 * 删除
	 * @param activId
	 * @return
	 */
	public int deleteById(String activId) {
		return mapper.deleteByPrimaryKey(activId);
	}
	/**
	 * 批量删除
	 * @param activId
	 * @return
	 */
	public int deleteAll(List<String> activId) {
		return mapper.deleteAll(activId);
	}
	/**
	 * 启用，禁用
	 * @param goods
	 * @return
	 */
	public int updateState(TActivityInfo active) {
		return mapper.updateState(active);
	}
}
