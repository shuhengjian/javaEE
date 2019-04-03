package com.creatorblue.cbitedu.ty.persistence;

import java.util.List;

import com.creatorblue.cbitedu.core.baseclass.persistence.BaseSqlMapper;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyPrice;
import com.creatorblue.cbitedu.ty.domain.TtyProduct;
import com.creatorblue.cbitedu.ty.domain.TtyType;

public interface TtyRuleMapper<T> extends BaseSqlMapper<T> {

	/**
	 * 根据品牌和价格查询
	 * @return
	 */
	List<TtyProduct> selectByBrandAndTypeAndPrice();
	
	 /**
		 * 查询品牌LOGO
		 * @return
		 */
		public List<TsysAttach> selectLogo();
		/**
		 * 查询价格区间
		 * @return
		 */
		public List<TtyPrice> selectPrice();
		/**
		 * 查询全部分类
		 * @return
		 */
		public List<TtyType> selectType();
		/**
		 * 查询全部产品
		 * @return
		 */
		public List<TtyProduct> selectAll(TtyProduct pro);
		
		/**
		 * 首页产品新推荐
		* @Function: TtyProductMapper.java
		 */
		public List<TtyProduct> selectNewProduct();
		/**
		 * 首页产品热销
		* @Function: TtyProductMapper.java
		 */
		public List<TtyProduct> selectLikeProduct();
		
		public TtyProduct checkTheNameWithParentId(TtyProduct ttyProduct);
		
		/**
		 * 商品详情
		 * @param productId
		 * @return
		 */
		public TtyProduct selectDetails(String productId);
		public List<TsysAttach> selectPicture(String productId);
		
}
