/**   
 * 功能描述：首页首页品牌
 * @Package: com.creatorblue.cbitedu.ty.back.service 
 * @author: shj 
 * @date: 2019年2月22日 上午10:34:42 
 */
package com.creatorblue.cbitedu.ty.front.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creatorblue.cbitedu.core.baseclass.service.BaseService;
import com.creatorblue.cbitedu.ty.domain.TsysAttach;
import com.creatorblue.cbitedu.ty.domain.TtyBrand;
import com.creatorblue.cbitedu.ty.persistence.TtyAdvertisingMapper;
import com.creatorblue.cbitedu.ty.persistence.TtyBrandMapper;

/**
 * @ClassName: TtyAdvertisingService.java
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: shj
 * @date: 2019年2月22日 上午10:34:42
 */
@Service(value = "ttyBrandService")
public class TtyBrandService<T> extends BaseService<T> {
	@Autowired
	private TtyBrandMapper<T> mapper;

	public TtyBrandMapper<T> getMapper() {
		return mapper;
	}
	
}
