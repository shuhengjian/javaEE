package com.musicbar.second.mapper;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.musicbar.second.domain.TAttach;

import io.lettuce.core.dynamic.annotation.Param;

public interface TAttachMapper {
    int deleteByPrimaryKey(String id);

    int insert(TAttach record);

    int insertSelective(TAttach record);

    TAttach selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TAttach record);

    int updateByPrimaryKey(TAttach record);
    
    /**
     *根据关联的Pkid查询资源 
     */
    TAttach selectByPkID(String id);
   
    /**
     *查询所有上架的商品图片
     */
   List<TAttach> selectAllGoodsAttach(Map<String, Object> obj); 
   /**
    * 批量删除
    */
   int deleteAll(List<String> id);
   /**
    * 批量插入
    * @param attachs
    * @return
    */
   int insertAttach(List<TAttach> attachs);
   /**
    *根据关联的Pkid查询图片集合
    */
   List<TAttach> selectListByPkID(String id);
   /**
    * 根据备注关联id删除图片
    * @param pkid
    * @param fileRemark
    * @return
    */
   int deleteByRemarkAndPkid(@Param(value="pkid")String pkid,@Param(value="fileRemark")String fileRemark);
   /**
    * 根据备注关联id查询图片路径
    * @param pkid
    * @param fileRemark
    * @return
    */
   String selectFileUelByRemarkAndPkid(@Param(value="pkid")String pkid,@Param(value="fileRemark")String fileRemark);
}