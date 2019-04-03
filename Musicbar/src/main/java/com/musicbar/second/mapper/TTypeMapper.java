package com.musicbar.second.mapper;

import java.util.List;

import com.musicbar.second.domain.TGoodsInfo;
import com.musicbar.second.domain.TType;


public interface TTypeMapper {
    int deleteByPrimaryKey(String typeId);

    int insert(TType record);

    int insertSelective(TType record);

    /**
     * 根据id查询商品
     * @param goodsId
     * @return
     */
    TType selectByPrimaryKey(String typeId);

    int updateByPrimaryKeySelective(TType record);

    int updateByPrimaryKey(TType record);
    
    List<TType> findAll(TType type);//查询列表所有
    
    int selectCount(String typeId);//查询总记录数
    
    /**
     * 批量删除
     */
    int deleteAll(List<String> typeId);
    /**
     * 修改商品分类信息
     */
    int updateType(TType type);
    /**
     * 添加商品分类信息
     */
    int insertType(TType type);
    /**
     * 查询商品分类状态
     */
    List<TType> selectState();
    /**
     * 启用，禁用
     * @param goods
     * @return
     */
    int updateState(TType type);
    /**
     *查询所有分类 
     */
    List<TType> selectAll(); 
    /**
     * 查询分类名称编号是否唯一
     * @param goods
     * @return
     */
    int selectTypeName(TType type);
}