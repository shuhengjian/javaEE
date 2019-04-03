package com.creatorblue.cbitedu.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.system.domain.TsysSerialno;
import com.creatorblue.cbitedu.system.persistence.TsysSerialnoMapper;
@Service("tsysSerialno")
public class TsysSerialnoService<T> extends BaseService<T>{
	@Autowired
	private TsysSerialnoMapper<T> tsysSerialno;
	
	/*
	 * 
	 */
	public List<TsysSerialno> selectPageTsysSerialnoList(Map<String, Object> result){
		return tsysSerialno.selectPageTsysSerialnoList(result);
	}
	public TsysSerialno selectTsysSerialno(Map<String, String> map){
		/*map=new HashMap<String,String>();
		map.put("serialno_id", "686b488f2a484382b0a38a85b2cf4e7e");*/
		return tsysSerialno.selectTsysSerialno(map);
	}
	
	public void deleteBatch(List<String> list) throws Exception{
		tsysSerialno.deleteBatch(list);
	}
	public void updateSerialnoCurNo(Map<String, String> map) throws Exception{
		tsysSerialno.updateSerialnoCurNo(map);
	}
	@Override
	public TsysSerialnoMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return tsysSerialno;
	}
}
