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
import com.creatorblue.cbitedu.system.domain.TsysModuleoperate;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;

public interface TsysModuleoperateMapper<T> extends BaseSqlMapper<T> {
       
	public List selectPageTsysModuleoperateByMap(Map para);

	public List selectPageTsysModuleoperate(TsysUserinfo userinfo);

	public List selectModuleoperateByMId(String moduleId);
    
	public void deleteByModuleID(String moduleId);
	
	public List<Map> selectModuleoperateByUser(TsysUserinfo userinfo);
}