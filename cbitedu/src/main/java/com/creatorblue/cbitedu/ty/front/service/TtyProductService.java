/**   
 * 功能描述：首页首页品牌
 * @Package: com.creatorblue.cbitedu.ty.back.service 
 * @author: shj 
 * @date: 2019年2月22日 上午10:34:42 
 */
package com.creatorblue.cbitedu.ty.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyPrice;
import com.creatorblue.cbitedu.ty.domain.TtyProduct;
import com.creatorblue.cbitedu.ty.domain.TtyType;
import com.creatorblue.cbitedu.ty.persistence.TtyProductMapper;
import com.creatorblue.cbitedu.ty.persistence.TtyTypeMapper;

/**
 * 产品 汽车
 * 
 * @ClassName: TtyAdvertisingService.java
 */
@Service(value = "ttyProductService")
public class TtyProductService<T> extends BaseService<T> {
	@Autowired
	private TtyProductMapper<T> mapper;

	public TtyProductMapper<T> getMapper() {
		return mapper;
	}

	/**
	 * 查询品牌
	 * 
	 * @return
	 */
	public List<TsysAttach> selectLogo() {
		return mapper.selectLogo();
	}

	/**
	 * 查询价格区间
	 * 
	 * @return
	 */
	public List<TtyPrice> selectPrice() {
		return mapper.selectPrice();
	}

	/**
	 * 查询全部类型
	 * 
	 * @return
	 */
	public List<TtyType> selectType(String code) {
		return mapper.selectType(code);
	}

	/**
	 * 查询全部产品
	 * 
	 * @return
	 */
	public List<TtyProduct> selectAll(TtyProduct pro) {
		return mapper.selectAll(pro);
	}

	/**
	 * 首页产品新推荐
	 * 
	 * @Function: TtyProductMapper.java
	 */
	public List<TtyProduct> selectNewProduct() {
		return mapper.selectNewProduct();
	}

	/**
	 * 首页产品新推荐
	 * 
	 * @Function: TtyProductMapper.java
	 */
	public List<TtyProduct> selectLikeProduct() {
		return mapper.selectLikeProduct();
	}
	/**
	 * 查询商品详情
	 * @param productId
	 * @return
	 */
	public TtyProduct selectDetails(String productId) {
		return mapper.selectDetails(productId);
	}
	public List<TsysAttach> selectPicture(String productId) {
		return mapper.selectPicture(productId);
	}
	/**
	 * 看过的还看
	 */
	public List<TtyProduct> selectNew(){
		return mapper.selectNew();
	}
	/**
	 * 详情新品推荐
	 */
	public List<TtyProduct> selectLike(){
		return mapper.selectLike();
	}
}
