package com.creatorblue.cbitedu.ty.persistence;

import java.util.List;
import java.util.Map;

import com.creatorblue.cbitedu.core.baseclass.persistence.BaseSqlMapper;
/**
 * 
* @ClassName: TtyAdvertisingMapper.java
* @Description: 广告
 */
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyAdvertising;
import com.creatorblue.cbitedu.ty.domain.TtyBrand;
public interface TtyAdvertisingMapper<T> extends BaseSqlMapper<T> {
	List<TsysAttach>  selectDeficiency(Map<String, Object> map);
	int deleteByPrimaryKey(String advertisingId);

    int insert(TtyAdvertising record);

    int insertSelective(TtyAdvertising record);

    TtyAdvertising selectByPrimaryKey(String advertisingId);

    int updateByPrimaryKeySelective(TtyAdvertising record);

    int updateByPrimaryKey(TtyAdvertising record);
    /**
     * 查询所有，分页
     * @param map
     * @return
     */
    public List<TtyAdvertising> selectPageTtyAdvertisingByMap(Map<String, Object> map);
    /**
     * 查询类是否存在
     * @param ttyBrand
     * @return
     */
    public TtyAdvertising checkTheNameWithParentId(TtyAdvertising ttyAdvertisingId);
    /**
     * 更新状态
     * @param record
     * @return
     */
    public int updateBrandStateByPrimaryKey(TtyAdvertising record);
}