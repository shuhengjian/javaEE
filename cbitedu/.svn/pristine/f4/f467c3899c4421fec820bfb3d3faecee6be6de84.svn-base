package com.creatorblue.cbitedu.ty.persistence;

import java.util.List;
import java.util.Map;

import com.creatorblue.cbitedu.core.baseclass.persistence.BaseSqlMapper;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyPrice;
import com.creatorblue.cbitedu.ty.domain.TtyProduct;
import com.creatorblue.cbitedu.ty.domain.TtyType;

public interface TtyProductMapper<T> extends BaseSqlMapper<T> {
	
	/**
	 * 多图上传
	 * @param map
	 * @return
	 */
	public int insertDetailPicture(Map<String,String> map);
	public int updateDetailPicture(Map<String,String> map);
	
	public int deletDetailPicture(Map<String, Object> map);
	
	/**
	 * 根据品牌和价格查询
	 * @return
	 */
	List<TtyProduct> selectByBrandAndTypeAndPrice();
	/**
	 * 查询
	 * @param map
	 * @return
	 */
	public List<TtyProduct> selectPageTtyProductByMap(Map<String, Object> map);
	
	public TtyProduct selectByPrimaryKey(String productId);
	
	/**
	 * 新增
	 * @param record
	 * @return
	 */
	public int insert(TtyProduct record);

    public int insertSelective(TtyProduct record);

    /**
     * 修改
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(TtyProduct record);

    public int updateByPrimaryKey(TtyProduct record);
	
	/**
	 * 删除
	 * @param productId
	 * @return
	 */
    public int deleteByPrimaryKey(String productId);
    
    /**
     * 改变产品状态
     * @param record
     * @return
     */
	public int updateProductStateByPrimaryKey(TtyProduct record);
    
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
	public List<TtyType> selectType(String code);
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
	/**
	 * 看过的人还看
	 */
	public List<TtyProduct> selectNew();
	/**
	 * 热门推荐
	 */
	public List<TtyProduct> selectLike();
	
}
