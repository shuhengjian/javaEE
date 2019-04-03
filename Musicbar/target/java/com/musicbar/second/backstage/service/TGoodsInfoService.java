package com.musicbar.second.backstage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicbar.core.utils.PinYinDemo;
import com.musicbar.core.utils.StringUtil;
import com.musicbar.second.domain.TAttach;
import com.musicbar.second.domain.TGoodsInfo;
import com.musicbar.second.domain.TType;
import com.musicbar.second.mapper.TAttachMapper;
import com.musicbar.second.mapper.TGoodsInfoMapper;

/**
 * 商品管理业务层
 * @author laj
 *
 */
@Service
public class TGoodsInfoService {
	@Autowired
	private TGoodsInfoMapper mapper;
	
	@Autowired
	private TAttachMapper attachMapper;//图片
	
	/**
	 * 后台管理查询全部商品信息
	 * @return
	 */
	public List<TGoodsInfo> selectAll(TGoodsInfo goods) {
		return mapper.selectAll(goods); 
	}
	
	/**
	 * 后台管理添加商品信息
	 * @param goods
	 * @return
	 */
	public int saveOrUpdate(TGoodsInfo goods,TAttach ac) {
		PinYinDemo pin = new PinYinDemo();
		int success = 0;
		TGoodsInfo good = new TGoodsInfo();
		good.setGoodsName(goods.getGoodsName());
		good.setGoodsId(goods.getGoodsId());
		if(mapper.selectGoodsName(good) > 0) {
			return -1;//商品名称已存在
		}
		good.setGoodsName(null);
		good.setGoodsCode(goods.getGoodsCode());
		if(mapper.selectGoodsName(good) > 0) {
			return -2;//商品编码已存在
		}
		if(goods.getGoodsId() !=null && ! goods.getGoodsId().isEmpty()) {
			String spell = goods.getGoodsName();
			String goodsSpell = pin.getPinYinFirstChar(spell);
			success = mapper.updateGoods(goods);
		}else {
			goods.setGoodsId(StringUtil.getUUIDValue());
			String spell = goods.getGoodsName();
			String goodsSpell = pin.getPinYinFirstChar(spell);
			goods.setGoodsSpell(goodsSpell);
			ac.setId(StringUtil.getUUIDValue());
			String pid = goods.getGoodsId();
			ac.setPkid(pid);
			insert(ac);
			success = mapper.insertGoods(goods);
		}
		return success;
	}
	/**
	 * 后台管理添加商品图片
	 * @param record
	 * @return
	 */
	public int insert(TAttach record) {
		record.setId(StringUtil.getUUIDValue());
		return attachMapper.insert(record);
	}
	public int insertGoods(TGoodsInfo goods) {
		return mapper.insertGoods(goods);
	}
	  /**
     *查询所有商品 
     */
    public List<TGoodsInfo> fronteSlectAll(TGoodsInfo goods){
    	List<TGoodsInfo> list =new ArrayList<TGoodsInfo>();
    	list=mapper.selecFronttAll(goods);
    	return list;
    } 
    
    /**
     *查询购物车中的商品
     */
    public TGoodsInfo selectGoodsCar(String arr){
    	TGoodsInfo list =new TGoodsInfo();
    	list=mapper.selectGoodsCar(arr); 
    	return list;
    }


    /**
	 * 根据id查询商品信息
	 * @param goodsId
	 * @return
	 */
	public TGoodsInfo selectGoodsById(String goodsId) {
		return mapper.selectByPrimaryKey(goodsId);
	}
    /**
     * 订单查询查询商品
     * @param goods
     * @return
     */
    public List<TGoodsInfo> selectArray(String[] tGoodsInfo){
    	return mapper.selectArray(tGoodsInfo);
    };
    /**
	 * 删除
	 * @param goodsId
	 * @return
	 */
	public int deleteById(String goodsId) {
		return mapper.deleteByPrimaryKey(goodsId);
	}
	/**
	 * 批量删除
	 * @param goodsId
	 * @return
	 */
	public int deleteAll(List<String> goodsId) {
		return mapper.deleteAll(goodsId);
	}
	/**
	 * 启用，禁用
	 * @param goods
	 * @return
	 */
	public int updateState(TGoodsInfo goods) {
		return mapper.updateState(goods);
	}
}
