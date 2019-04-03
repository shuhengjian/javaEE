package com.musicbar.second.mapper;

import java.util.List;
 
import com.musicbar.second.domain.TActivityInfo;

public interface TActivityInfoMapper {
    int deleteByPrimaryKey(String activId);

    int insert(TActivityInfo record);

    int insertSelective(TActivityInfo record);

    TActivityInfo selectByPrimaryKey(String activId);

    int updateByPrimaryKeySelective(TActivityInfo record);

    int updateByPrimaryKey(TActivityInfo record);
    
    /**
     * 查询活动列表
     * @param activ
     * @return
     */
    List<TActivityInfo> selectAll(TActivityInfo activ);
    /**
     * 查询活动状态
     */
    List<TActivityInfo> selectState();
    /**
     *添加页面 查询活动状态
     */
    List<TActivityInfo> selectStates();
    /**
     * 查询所有
     * @param activ
     * @return
     */
   List<TActivityInfo> selecFronttAll(TActivityInfo activ);
   /**
    * 添加活动信息
    */
   int insertActive(TActivityInfo activ); /**
     * 启用，禁用
     * @param activ
     * @return
     */
    int updateState(TActivityInfo activ);
   /**
    * 修改活动信息
    */
   int updateActive(TActivityInfo activ);
   
   /**
    * 批量删除
    */
   int deleteAll(List<String> activId);
   /**
    * 查询活动主题是否唯一
    * @param activ
    * @return
    */
   int selectActivTheme(TActivityInfo activ);
}
