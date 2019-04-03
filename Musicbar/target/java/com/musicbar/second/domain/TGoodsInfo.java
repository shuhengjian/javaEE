package com.musicbar.second.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TGoodsInfo {
	private String goodsId;

	private TType type;

	private String goodsName;

	private String goodsCode;

	private String goodsUnits;

	private String goodsQuantity;

	private BigDecimal goodsPrice;

	private String goodsStandard;

	private String goodsInventoryWarning;

	private Long goodsStock;

	private Long goodsSales;

	private String goodsState;

	private String goodsSpecial;

	private String goodsSpell;

	private Date createUser;

	private Date createTime;

	private String updateUser;

	private Date updateTime;

	private String cook;

	private TType tType;

	private TAttach attch;
	
	private TParameter parameter;

	public TParameter getParameter() {
		return parameter;
	}

	public void setParameter(TParameter parameter) {
		this.parameter = parameter;
	}

	public TType gettType() {
		return tType;
	}

	public void settType(TType tType) {
		this.tType = tType;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public TType getType() {
		return type;
	}

	public void setType(TType type) {
		this.type = type;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getGoodsUnits() {
		return goodsUnits;
	}

	public void setGoodsUnits(String goodsUnits) {
		this.goodsUnits = goodsUnits;
	}

	public String getGoodsQuantity() {
		return goodsQuantity;
	}

	public void setGoodsQuantity(String goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
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

	public String getGoodsInventoryWarning() {
		return goodsInventoryWarning;
	}

	public void setGoodsInventoryWarning(String goodsInventoryWarning) {
		this.goodsInventoryWarning = goodsInventoryWarning;
	}

	public Long getGoodsStock() {
		return goodsStock;
	}

	public void setGoodsStock(Long goodsStock) {
		this.goodsStock = goodsStock;
	}

	public Long getGoodsSales() {
		return goodsSales;
	}

	public void setGoodsSales(Long goodsSales) {
		this.goodsSales = goodsSales;
	}

	public String getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(String goodsState) {
		this.goodsState = goodsState;
	}

	public String getGoodsSpecial() {
		return goodsSpecial;
	}

	public void setGoodsSpecial(String goodsSpecial) {
		this.goodsSpecial = goodsSpecial;
	}

	public String getGoodsSpell() {
		return goodsSpell;
	}

	public void setGoodsSpell(String goodsSpell) {
		this.goodsSpell = goodsSpell;
	}

	public Date getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Date createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCook() {
		return cook;
	}

	public void setCook(String cook) {
		this.cook = cook;
	}

	public TAttach getAttch() {
		return attch;
	}

	public void setAttch(TAttach attch) {
		this.attch = attch;
	}

}