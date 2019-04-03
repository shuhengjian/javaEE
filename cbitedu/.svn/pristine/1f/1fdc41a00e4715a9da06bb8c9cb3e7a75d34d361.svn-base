package com.creatorblue.cbitedu.system.persistence;

import java.util.List;
import java.util.Map;

import com.creatorblue.cbitedu.core.baseclass.persistence.BaseSqlMapper;
import com.creatorblue.cbitedu.system.domain.TsysSerialno;

public interface TsysSerialnoMapper<T> extends BaseSqlMapper<T>  {
	public List<TsysSerialno> selectPageTsysSerialnoList(Map<String, Object> result);
	public TsysSerialno selectTsysSerialno(Map<String, String> result);
	public void deleteBatch(List<String> list);
	public String getSerialNumber(Map<String,String> map);
	public void updateSerialnoCurNo(Map<String,String> map);
}
