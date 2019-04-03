package com.musicbar.second.mapper;

import java.util.List;

import com.musicbar.second.domain.TParameter;

public interface TParameterMapper {

    int insert(TParameter record);

    int insertSelective(TParameter record);
   
    int updateByPrimaryKeySelective(TParameter record);

    int updateByPrimaryKey(TParameter record);
    
    /**
     * 查询所有参数
     * @return
     */
    List<TParameter> slectAllTParameter();
    /**
     * 添加参数信息
     * @param param
     * @return
     */
    int insertparameter(TParameter param);
    /**
     * 修改参数信息
     * @param param
     * @return
     */
    int updateparameter(TParameter param);
    /**
     * 删除参数信息
     * @param paraId
     * @return
     */
    int deleteByPrimaryKey(String paraId);
    /**
     * 条件查询
     * @param param
     * @return
     */
    List<TParameter> selectByParam(TParameter param);
    /**
     * 根据id查询参数信息
     * @param paraId
     * @return
     */
    TParameter selectByPrimaryKey(String paraId);
     /** 查询商品状态
     */
    List<TParameter> selectState();
    /**
     * 查询商品是否特价
     */
    List<TParameter> selectSpecial();
    /**
     * 查询商品单位
     */
    List<TParameter> selectUnits();
    /**
     * 查询商品规格
     */
    List<TParameter> selectStandard();
    /**
     * 查询分类状态
     * @return
     */
    List<TParameter> selectTypeState();
    
}