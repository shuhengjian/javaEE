package com.creatorblue.cbitedu.ty.persistence;

import java.util.List;
import java.util.Map;

import com.creatorblue.cbitedu.core.baseclass.persistence.BaseSqlMapper;
import com.creatorblue.cbitedu.ty.domain.TtyBrand;
import com.creatorblue.cbitedu.ty.domain.TtyType;
/**
 * 类型
* @ClassName: TtyTypeMapper.java
 */
public interface TtyTypeMapper<T> extends BaseSqlMapper<T> {
	public int deleteByPrimaryKey(String typeId);

    public int insert(TtyType record);

    public int insertSelective(TtyType record);

    public TtyType selectByPrimaryKey(String typeId);

    public int updateByPrimaryKeySelective(TtyType record);

    public int updateByPrimaryKey(TtyType record);
    
    public List<TtyType> selectPageTtyTypeByMap(Map<String, Object> map);
    
    public TtyType checkTheNameWithParentId(TtyType ttyType);
    
    public int updateTypeStateByPrimaryKey(TtyType record);
    
    public List<TtyType> selectType(Map<String, Object> map);
    
    public int selectCountProduct(String typeId);
}