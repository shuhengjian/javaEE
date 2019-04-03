package com.musicbar.second.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TOrders {
    private String ordersId;

    private Integer tableNum;

    private BigDecimal ordersMoney;

    private Integer ordersNum;

    private String ordersCode;

    private String paymentMode;

    private Date paymentTime;

    private String ordersMobile;

    private String ordersState;

    private String ordersWay;

    private String ordersDeal;

    private String createUser;

    private Date createTime;
    
    private List<TOrdersInfo> tOrdersInfo;

    

	public List<TOrdersInfo> gettOrdersInfo() {
		return tOrdersInfo;
	}

	public void settOrdersInfo(List<TOrdersInfo> tOrdersInfo) {
		this.tOrdersInfo = tOrdersInfo;
	}

	public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public Integer getTableNum() {
        return tableNum;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }

    public BigDecimal getOrdersMoney() {
        return ordersMoney;
    }

    public void setOrdersMoney(BigDecimal ordersMoney) {
        this.ordersMoney = ordersMoney;
    }

    public Integer getOrdersNum() {
        return ordersNum;
    }

    public void setOrdersNum(Integer ordersNum) {
        this.ordersNum = ordersNum;
    }

    public String getOrdersCode() {
        return ordersCode;
    }

    public void setOrdersCode(String ordersCode) {
        this.ordersCode = ordersCode;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getOrdersMobile() {
        return ordersMobile;
    }

    public void setOrdersMobile(String ordersMobile) {
        this.ordersMobile = ordersMobile;
    }

    public String getOrdersState() {
        return ordersState;
    }

    public void setOrdersState(String ordersState) {
        this.ordersState = ordersState;
    }

    public String getOrdersWay() {
        return ordersWay;
    }

    public void setOrdersWay(String ordersWay) {
        this.ordersWay = ordersWay;
    }

    public String getOrdersDeal() {
        return ordersDeal;
    }

    public void setOrdersDeal(String ordersDeal) {
        this.ordersDeal = ordersDeal;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}