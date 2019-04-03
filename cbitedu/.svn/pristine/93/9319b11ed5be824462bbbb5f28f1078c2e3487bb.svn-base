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
import com.creatorblue.cbitedu.ty.domain.TtyPrice;
import com.creatorblue.cbitedu.ty.domain.TtyProduct;
import com.creatorblue.cbitedu.ty.domain.TtyType;
import com.creatorblue.cbitedu.ty.persistence.TtyPriceMapper;

/**
 * 价格
 * 
 * @ClassName: TtyAdvertisingService.java
 */
@Service(value = "ttyPriceService")
public class TtyPriceService<T> extends BaseService<T> {
	@Autowired
	private TtyPriceMapper<T> mapper;

	public TtyPriceMapper<T> getMapper() {
		return mapper;
	}

	public TtyPrice selectByPrimaryKey(String priceId) {
		return mapper.selectByPrimaryKey(priceId);
	}

}
