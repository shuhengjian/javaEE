package com.creatorblue.cbitedu.ty.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.creatorblue.cbitedu.core.baseclass.domain.BaseDomain;
import com.creatorblue.cbitedu.system.domain.TsysOrg;

/**
 * 产品管理;
 */
public class TtyProduct extends BaseDomain {
	/**
	 * 产品id
	 */
	private String productId;

	/**
	 * 品牌id
	 */
	private TtyBrand ttyBrand;

	/**
	 * 类型id
	 */
	private TtyType ttyType;

	/**
	 * 商家Id
	 */
	private TsysOrg tsysOrg;

	/**
	 * 价格区间id
	 */
	private TtyPrice ttyPrice;

	/**
	 * 产品名称
	 */
	private String productName;


	/**
	 * 市场价格
	 */
	private BigDecimal productMarketPrice;

	/**
	 * 一口价
	 */
	private BigDecimal productFlatlyPrice;

	/**
	 * 产品优势/介绍
	 */
	private String productRemark;

	/**
	 * 产品颜色
	 */
	private String productColor;

	/**
	 * 产品封面图
	 */
	private String productCoverPicture;

	/**
	 * 发布时间
	 */
	private Date productPubTime;

	/**
	 * 创建人
	 */
	private String createUserId;
	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 修改人
	 */
	private String updateUserId;

	/**
	 * 修改时间
	 */
	private String updateTime;

	/**
	 * 商品详情
	 */
	private String productDetails;
	
	/**
	 * 产品状态
	 */
	private String productState;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId == null ? null : productId.trim();
	}

	public TsysOrg getTsysOrg() {
		return tsysOrg;
	}

	public void setTsysOrg(TsysOrg tsysOrg) {
		this.tsysOrg = tsysOrg;
	}

	public TtyBrand getTtyBrand() {
		return ttyBrand;
	}

	public void setTtyBrand(TtyBrand ttyBrand) {
		this.ttyBrand = ttyBrand;
	}

	public TtyType getTtyType() {
		return ttyType;
	}

	public void setTtyType(TtyType ttyType) {
		this.ttyType = ttyType;
	}

	public TtyPrice getTtyPrice() {
		return ttyPrice;
	}

	public void setTtyPrice(TtyPrice ttyPrice) {
		this.ttyPrice = ttyPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName == null ? null : productName.trim();
	}

	public BigDecimal getProductMarketPrice() {
		return productMarketPrice;
	}

	public void setProductMarketPrice(BigDecimal productMarketPrice) {
		this.productMarketPrice = productMarketPrice;
	}

	public BigDecimal getProductFlatlyPrice() {
		return productFlatlyPrice;
	}

	public void setProductFlatlyPrice(BigDecimal productFlatlyPrice) {
		this.productFlatlyPrice = productFlatlyPrice;
	}

	public String getProductRemark() {
		return productRemark;
	}

	public void setProductRemark(String productRemark) {
		this.productRemark = productRemark == null ? null : productRemark.trim();
	}

	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColour) {
		this.productColor = productColour == null ? null : productColour.trim();
	}

	public String getProductCoverPicture() {
		return productCoverPicture;
	}

	public void setProductCoverPicture(String productCoverPicture) {
		this.productCoverPicture = productCoverPicture == null ? null : productCoverPicture.trim();
	}

	public Date getProductPubTime() {
		return productPubTime;
	}

	public void setProductPubTime(Date productPubTime) {
		this.productPubTime = productPubTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId == null ? null : createUserId.trim();
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId == null ? null : updateUserId.trim();
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime == null ? null : updateTime.trim();
	}

	public String getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails == null ? null : productDetails.trim();
	}

	public String getProductState() {
		return productState;
	}

	public void setProductState(String productState) {
		this.productState = productState;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}