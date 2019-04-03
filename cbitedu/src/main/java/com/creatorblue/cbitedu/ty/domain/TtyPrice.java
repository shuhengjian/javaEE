package com.creatorblue.cbitedu.ty.domain;

import java.math.BigDecimal;

import com.creatorblue.cbitedu.core.baseclass.domain.BaseDomain;

/**
 * 价格
 */
public class TtyPrice extends BaseDomain{
    /**
     * 价格id
     */
    private String priceId;

    /**
     * 价格最大值
     */
    private BigDecimal priceMax;

    /**
     * 价格最小值
     */
    private BigDecimal priceMin;
   
    /**
     * 价格状态
     */
    private String priceState;

    public String getPriceState() {
		return priceState;
	}

	public void setPriceState(String priceState) {
		this.priceState = priceState;
	}

	public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId == null ? null : priceId.trim();
    }

    public BigDecimal getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(BigDecimal priceMax) {
        this.priceMax = priceMax;
    }

    public BigDecimal getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(BigDecimal priceMin) {
        this.priceMin = priceMin;
    }

    
}