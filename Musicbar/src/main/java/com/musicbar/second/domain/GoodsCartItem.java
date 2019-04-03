/**   
 * 功能描述：
 * @Package: com.musicbar.second.domain 
 * @author: shj 
 * @date: 2019年3月14日 上午10:33:20 
 */
package com.musicbar.second.domain;

import java.math.BigDecimal;

/**
 * @ClassName: GoodCartItem.java
 * @Description: 购物车每一项商品
 */
public class GoodsCartItem {
	private TGoodsInfo goodsInfo;

	private Integer num;

	private BigDecimal sriceSubtotal;

	public TGoodsInfo getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(TGoodsInfo goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public BigDecimal getSriceSubtotal() {
		return sriceSubtotal;
	}

	public void setSriceSubtotal(BigDecimal sriceSubtotal) {
		this.sriceSubtotal = sriceSubtotal;
	}

}
