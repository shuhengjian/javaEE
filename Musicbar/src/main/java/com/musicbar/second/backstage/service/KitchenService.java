package com.musicbar.second.backstage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicbar.second.domain.TOrders;
import com.musicbar.second.domain.TOrdersInfo;
import com.musicbar.second.mapper.TOrdersInfoMapper;

@Service(value = "kitService")
public class KitchenService {
	@Autowired
	private TOrdersInfoMapper mapper;

	public List<TOrdersInfo> selectCitchen(String kitState){
		return mapper.selectKitchen(kitState);
	}
	/**
	 * 修改订单详细表商品烹饪状态
	 * @param tordersInfo
	 * @return
	 */
	public int updateKitchen(TOrdersInfo tordersInfo) {
		return mapper.updateKitchen(tordersInfo);
	}
	/**
	 * 修改订单表烹饪状态
	 * @param tOrders
	 * @return
	 */
	public int updateState(TOrders tOrders) {
		return mapper.updateKitchenState(tOrders);
	}
	
	public List<TOrdersInfo> selectDetailByorderId(String ordersId){
		return mapper.selectDetailByorderId(ordersId);
	}
}