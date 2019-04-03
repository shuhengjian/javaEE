package com.musicbar.second.mapper;

import java.util.List;

import com.musicbar.second.domain.TGoodsInfo;
/**
 * 商品管理接口
 * @author laj
 *
 */
import com.musicbar.second.domain.TType;
public interface TGoodsInfoMapper {
    int deleteByPrimaryKey(String goodsId);

    int insert(TGoodsInfo record);

    int insertSelective(TGoodsInfo record);
    /**
     * 根据id查询商品
     * @param goodsId
     * @return
     */
    TGoodsInfo selectByPrimaryKey(String goodsId);
 
    int updateByPrimaryKeySelective(TGoodsInfo record);

    int updateByPrimaryKey(TGoodsInfo record);
    
    /**
     * 查询商品列表
     */
    List<TGoodsInfo> selectAll(TGoodsInfo goods);
    /**
  
    /**
     *查询购物车中的商品
     */
   TGoodsInfo selectGoodsCar(String arr); 
     /**
      * 查询所有
      * @param goods
      * @return
      */
    List<TGoodsInfo> selecFronttAll(TGoodsInfo goods);
    /**
     * 添加商品信息
     */
    int insertGoods(TGoodsInfo goods);

    /**
     * 修改商品信息
     */

    int updateGoods(TGoodsInfo goods);

    
    /**
     * 订单结算查询商品
     */
    List<TGoodsInfo> selectArray(String[] tGoodsInfo);
    /**
     * 批量删除
     */
    int deleteAll(List<String> goodsId);
    /**
     * 启用，禁用
     * @param goods
     * @return
     */
    int updateState(TGoodsInfo goods);
    /**
     * 查询商品名称编号是否唯一
     * @param goods
     * @return
     */
    int selectGoodsName(TGoodsInfo goods);

}
