package com.musicbar.second.mapper;

import java.util.List;

import com.musicbar.second.domain.TOperation;

public interface TOperationMapper {
	/**
	 * 根据菜单id查询操作
	 * @param menuId
	 * @return
	 */
   List<TOperation> selectByMenuId(String menuId);
   /**
    * 根据操作id查询
    * @return
    */
	TOperation selectOperationById(String id);

}