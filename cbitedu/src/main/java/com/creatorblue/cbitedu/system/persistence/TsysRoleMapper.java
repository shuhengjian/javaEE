/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

package com.creatorblue.cbitedu.system.persistence;
import java.util.List;
import java.util.Map;

import com.creatorblue.cbitedu.core.baseclass.persistence.BaseSqlMapper;
import com.creatorblue.cbitedu.core.interceptor.AConfig;
import com.creatorblue.cbitedu.system.domain.TsysRole;

public interface TsysRoleMapper<T> extends BaseSqlMapper<T> {
       
	public List selectPageTsysRoleByMap(Map para);
	
	public List selectRoleInfoByMap(Map para);

	public List selectPageTsysRole(TsysRole sysRole);
}