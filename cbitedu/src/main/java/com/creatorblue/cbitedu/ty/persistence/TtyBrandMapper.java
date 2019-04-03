package com.creatorblue.cbitedu.ty.persistence;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.creatorblue.cbitedu.core.baseclass.persistence.BaseSqlMapper;
import com.creatorblue.cbitedu.ty.domain.TtyBrand;

public interface TtyBrandMapper<T> extends BaseSqlMapper<T> {
	public int deleteByPrimaryKey(String brandId);

    public int insert(TtyBrand record);

    public int insertSelective(TtyBrand record);

    public TtyBrand selectByPrimaryKey(String brandId);

    public int updateBrandStateByPrimaryKey(TtyBrand record);

    public int updateByPrimaryKey(TtyBrand record);
    
    public List<TtyBrand> selectPageTtyBrandByMap(Map<String, Object> map);
    
    public TtyBrand checkTheNameWithParentId(TtyBrand ttyBrand);

    public int selectCountProduct(String brandId);
    
    public List<TtyBrand> selectBrand(Map<String, Object> map);
    
}