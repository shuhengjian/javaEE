package com.creatorblue.cbitedu.ty.front.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.persistence.TsysAttachMapper;
import com.creatorblue.cbitedu.ty.persistence.TtyProductMapper;

@Service(value = "ttyAttachService")
public class TtyAttachService<T> extends BaseService<T> {
	
	@Autowired
	private TsysAttachMapper<T> mapper;
	
	public TsysAttachMapper<T> getMapper() {
		return mapper;
	}
	
	public TsysAttach selectAttachById(Map<String,Object> obj ) {
		return mapper.selectAttachById(obj);
	}
	public TsysAttach selectfindById(String obj ) {
		return mapper.selectfindById(obj);
	}
	
}
