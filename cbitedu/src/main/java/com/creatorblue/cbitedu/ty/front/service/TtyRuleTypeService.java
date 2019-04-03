package com.creatorblue.cbitedu.ty.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.persistence.BaseSqlMapper;
import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyPrice;
import com.creatorblue.cbitedu.ty.domain.TtyProduct;
import com.creatorblue.cbitedu.ty.domain.TtyType;
import com.creatorblue.cbitedu.ty.persistence.TtyRuleMapper;

@Service( value = "ttyRuleTypeService")
public class TtyRuleTypeService<T> extends BaseService<T>  {
	@Autowired
	private  TtyRuleMapper<T> ruleMapper;

	public TtyRuleMapper<T> getRuleMapper() {
		return ruleMapper;
	}

	/**
	 * 查询品牌
	 * 
	 * @return
	 */
	public List<TsysAttach> selectLogo() {
		return ruleMapper.selectLogo();
	}

	/**
	 * 查询价格区间
	 * 
	 * @return
	 */
	public List<TtyPrice> selectPrice() {
		return ruleMapper.selectPrice();
	}

	/**
	 * 查询全部类型
	 * 
	 * @return
	 */
	public List<TtyType> selectType() {
		return ruleMapper.selectType();
	}

	/**
	 * 查询全部产品
	 * 
	 * @return
	 */
	public List<TtyProduct> selectAll(TtyProduct pro) {
		return ruleMapper.selectAll(pro);
	}

	/**
	 * 首页产品新推荐
	 * 
	 * @Function: TtyProductMapper.java
	 */
	public List<TtyProduct> selectNewProduct() {
		return ruleMapper.selectNewProduct();
	}

	/**
	 * 首页产品新推荐
	 * 
	 * @Function: TtyProductMapper.java
	 */
	public List<TtyProduct> selectLikeProduct() {
		return ruleMapper.selectLikeProduct();
	}
	/**
	 * 查询商品详情
	 * @param productId
	 * @return
	 */
	public TtyProduct selectDetails(String productId) {
		return ruleMapper.selectDetails(productId);
	}
	public List<TsysAttach> selectPicture(String productId) {
		return ruleMapper.selectPicture(productId);
	}
	@Override
	public BaseSqlMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
