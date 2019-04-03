/**   
 * 功能描述：
 * @Package: com.musicbar.second.domain 
 * @author: shj 
 * @date: 2019年3月13日 下午8:43:12 
 */
package com.musicbar.second.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: GoodsCar.java
 * @Description:购物车
 */
public class GoodsCart {
	/**
	 * 商品
	 */
	private List<GoodsCartItem> goodsList;

	/**
	 * 总金额
	 */
	private BigDecimal sum;
	/**
	 * 总数量
	 */
	private Integer num;

	/**
	 * 种类
	 */
	private Integer typeNum;

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public List<GoodsCartItem> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<GoodsCartItem> goodsList) {
		this.goodsList = goodsList;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getTypeNum() {
		return typeNum;
	}

	public void setTypeNum(Integer typeNum) {
		this.typeNum = typeNum;

	}

}
