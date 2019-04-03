package com.musicbar.second.backstage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicbar.core.utils.StringUtil;
import com.musicbar.second.domain.TAttach;
import com.musicbar.second.domain.TParameter;
import com.musicbar.second.domain.TRole;
import com.musicbar.second.mapper.TParameterMapper;
@Service
public class TParameterService {
	@Autowired
	private TParameterMapper parameterMapper;
	/**
	 * 查询所有参数
	 * @return
	 */
	public List<TParameter> slectAllTParameter(){
		return parameterMapper.slectAllTParameter();
	}
	/**
	 * 
	 * @return
	 */
	public List<TParameter> selectAll(){
		return parameterMapper.selectAll();
	}
	/**
	 * 添加参数信息
	 * @param param
	 * @return
	 */
	public int insertparameter(TParameter param) {
		int success = 0;
		if(param.getParaId() != null && !param.getParaId().isEmpty()) {
			success = parameterMapper.updateparameter(param);
		}else {
			param.setParaId(StringUtil.getUUIDValue());
			success = parameterMapper.insertparameter(param);
		}
		return success;
	}
	/**
	 * 根据ID查询参数信息
	 * @param paraId
	 * @return
	 */
	public TParameter selectById(String paraId) {
		return parameterMapper.selectByPrimaryKey(paraId);
	}
	/**
	 * 修改参数信息
	 * @param param
	 * @return
	 */
	public int updateparameter (TParameter param) {
		return parameterMapper.updateparameter(param);
	}
	/**
	 * 删除参数信息
	 * @param paraId
	 * @return
	 */
	public int deleteByPrimaryKey(String paraId) {
		return parameterMapper.deleteByPrimaryKey(paraId);
	}
	/**
	 * 条件查询
	 * @param param
	 * @return
	 */
	public List<TParameter> selectByParam(TParameter param){
		return parameterMapper.selectByParam(param);
	}
	/**
	 * 后台管理查询商品状态
	 */
	public List<TParameter> selectState(){
		return parameterMapper.selectState();
	}
	/**
	 * 后台管理查询分类状态 
	 */
	public List<TParameter> selectTypeState(){
		return parameterMapper.selectTypeState();  
	}
	/**
	 * 查询商品是否特价
	 */
	public List<TParameter> selectSpecial(){
		return parameterMapper.selectSpecial();
	}
	/**
	 * 查询商品单位
	 */
	public List<TParameter> selectUnits(){
		return parameterMapper.selectUnits();
	}
	/**
	 * 查询商品规格
	 */
	public List<TParameter> selectStandard(){
		return parameterMapper.selectStandard();
	}
}
