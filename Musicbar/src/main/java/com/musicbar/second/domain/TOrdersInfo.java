package com.musicbar.second.domain;

import java.math.BigDecimal;

public class TOrdersInfo {
    private String ordersInfoId;

    private String ordersId;

    private String goodsId;

    private String goodsName;

    private String goodsQuantity;

    private String goodsUnits;

    private BigDecimal goodsPrice;

    private String goodsStandard;

    private Integer goodsNum;
    
    private String fileUel;
    
    private String kitState;

    public String getOrdersInfoId() {
        return ordersInfoId;
    }

    public void setOrdersInfoId(String ordersInfoId) {
        this.ordersInfoId = ordersInfoId;
    }

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsQuantity() {
        return goodsQuantity;
    }

    public void setGoodsQuantity(String goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    public String getGoodsUnits() {
        return goodsUnits;
    }

    public void setGoodsUnits(String goodsUnits) {
        this.goodsUnits = goodsUnits;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsStandard() {
        return goodsStandard;
    }

    public void setGoodsStandard(String goodsStandard) {
        this.goodsStandard = goodsStandard;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

	/**
	 * @return the fileUel
	 */
	public String getFileUel() {
		return fileUel;
	}

	/**
	 * @param fileUel the fileUel to set
	 */
	public void setFileUel(String fileUel) {
		this.fileUel = fileUel;
	}

	/**
	 * @return the kitState
	 */
	public String getKitState() {
		return kitState;
	}

	/**
	 * @param kitState the kitState to set
	 */
	public void setKitState(String kitState) {
		this.kitState = kitState;
	}
    
}