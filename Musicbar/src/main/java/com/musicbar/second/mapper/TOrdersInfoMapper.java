package com.musicbar.second.mapper;

import java.util.List;

import com.musicbar.second.domain.TGoodsInfo;
import com.musicbar.second.domain.TOrders;
import com.musicbar.second.domain.TOrdersInfo;

public interface TOrdersInfoMapper {
    int deleteByPrimaryKey(String ordersInfoId);

    int insert(List<TOrdersInfo > ordersInfo);

    int insertSelective(TOrdersInfo record);

    TOrdersInfo selectByPrimaryKey(String ordersInfoId);

    int updateByPrimaryKeySelective(TOrdersInfo record);

    int updateByPrimaryKey(TOrdersInfo record);
    
    int modifyHostId(List<TOrdersInfo > ordersInfo);
    

    List<TOrdersInfo> selectByOdersId(String ordersId);

   /* List<TOrdersInfo> selectKitchen();*/
    /**
     * 查询订单所有需要烹饪的商品
     * @return
     */
    List<TOrdersInfo> selectKitchen(String kitState);
    /**
     * 改变烹饪状态
     * @param torders
     * @return
     */
    int updateKitchen(TOrdersInfo tordersInfo);
    /**
     * 改变订单状态
     * @param torders
     * @return
     */
    int updateKitchenState(TOrders torders);
    /**
     * 根据订单id查询所有烹饪的商品
     * @param tOrders
     * @return
     */
    List<TOrdersInfo> selectDetailByorderId(String ordersId);
}