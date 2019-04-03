package com.musicbar.second.mapper;

import java.util.List;

import com.musicbar.second.domain.TMenu;

public interface TMenuMapper {
	/**
	 * 查询所有菜单
	 * @return
	 */
    List<TMenu> selectAll();
    /**
     * 根据菜单id查询菜单名
     * @return
     */
	String selectName(String id);

}