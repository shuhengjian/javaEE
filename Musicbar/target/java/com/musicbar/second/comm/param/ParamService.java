package com.musicbar.second.comm.param;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicbar.second.domain.TParameter;
import com.musicbar.second.mapper.TParameterMapper;

@Service("paramService")
public class ParamService {
	@Autowired
	private TParameterMapper tParameterMapper;
	
	public List<TParameter> slectAllTParameter(){
		List<TParameter> tParam = tParameterMapper.slectAllTParameter();
		return  tParam;
	}
}
