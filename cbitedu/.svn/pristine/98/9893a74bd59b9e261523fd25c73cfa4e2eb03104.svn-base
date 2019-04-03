package com.creatorblue.cbitedu.ty.persistence;

import java.util.List;
import java.util.Map;

import com.creatorblue.cbitedu.core.baseclass.persistence.BaseSqlMapper;
import com.creatorblue.cbitedu.ty.domain.TtyPrice;

public interface TtyPriceMapper<T> extends BaseSqlMapper<T> {
	public int deleteByPrimaryKey(String priceId);

    public int insert(TtyPrice record);

    public int insertSelective(TtyPrice record);

    public TtyPrice selectByPrimaryKey(String priceId);

    public int updateByPrimaryKeySelective(TtyPrice record);

    public int updateByPrimaryKey(TtyPrice record);
    
    public List<TtyPrice> selectPageTtyPriceByMap(Map<String, Object> map);
    
    public TtyPrice checkTheMaxWithParentId(TtyPrice TtyPrice);
    
    public TtyPrice checkTheMinWithParentId(TtyPrice TtyPrice);
    
    public int updatePriceStateByPrimaryKey(TtyPrice record);
    
    public List selectAllProductFlatlyPrice();
    
    
    
    
    public List<TtyPrice> selectPrice(Map<String, Object> map);
    
    public List selectAllMin();
    
    public List selectAllMax();
    
    public TtyPrice selectMaxAndMin(String priceId);
}