package com.musicbar.second.mapper;

import java.util.List;


import java.util.Map;


import com.musicbar.second.domain.TOrders;

public interface TOrdersMapper {
	
    int deleteByPrimaryKey(String ordersId);
    
    /**
     * 查询所有
     * @param tOrders
     * @return
     */
    List<TOrders> selectAll(TOrders tOrders);
    /**
     *  
     * @param ordersId
     * @return
     */
    List<TOrders> selectorder(String ordersId);
    /**
      * 根据手机号查询
     * @param ordersId
     * @return
     */
    List<TOrders> selectCode(String ordersId);
    
    /**
     * 根据状态号查询
    * @param ordersId
    * @return
    */
    int selectState(String ordersId);
    
    /**
     * 根据id查询商品信息
     * @return
     */
    List<TOrders> selectOrdInfo(TOrders tOrders);
    /**
     * 根据订单号查询商品信息
     * @return
     */
    List<TOrders> selectOrdCode(TOrders tOrders);
    /**
     * 查询所有订单信息
     * @return
     */
    List<TOrders> selectOrderAll();
    /**
     * 根据订单号查询订单信息
     * @param ordersId
     * @return
     */
    List<TOrders> selectOrderCode();
    
    /**
     * 按时间降序查询当天最大时间的订单号
     * @param record
     * @return
     */
    TOrders selectPayCode();
    
    /**
     * 根据session手机号查询
    * @param ordersId
    * @return
    */
   TOrders selectseMobileSe(String ordersId);
    
   /**
    * 关联参数表根据手机号查询
    * @param ordersId
    * @return
    */
   TOrders selectParameter(String tOrders);
   /**
    * 关联参数表根据id查询
    * @param ordersId
    * @return
    */
   TOrders selectParameterId(String tOrders);
    
    int insert(TOrders record);
    
    int insertCode(List<TOrders > tOrders);
    
    int insertSelective(TOrders record);

    TOrders selectByPrimaryKey(String ordersId);

    int updateByPrimaryKeySelective(TOrders record);

    int updateByPrimaryKey(TOrders record);
    
    int updateCode(TOrders tOrders);
    
    /**
      * 统计订单总数量，总销售额，总销量
     * @param map 开始时间，结束时间
     * @return
     */
    Map<String, String> selectTotal(Map<String, String> map);
    /**
     * 根据时间按商品分类名称与商品名称统计销售量
     * @param map 开始时间，结束时间
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> selectNumTotalByTypeNameAndGoodsName(Map<String, String> map);
    /**
     * 查询订单的最大时间与最小
     * @return
     */
    Map<String,String> selectMaxAndMinTime();
    
    /**
     * 往年对比查询一年的总和
     * @param time
     * @return
     */
    Map<String, String> selectFormerYears(String time);
    
    /**
     * 订单计时修改状态
     * @param tOrders
     * @return
     */
    int updateTime(TOrders tOrders);


}