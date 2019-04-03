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

import org.springframework.dao.DataAccessException;

import com.creatorblue.cbitedu.core.baseclass.persistence.BaseSqlMapper;
import com.creatorblue.cbitedu.system.domain.TsysDataprivilege;
import com.creatorblue.cbitedu.system.domain.TsysOrg;

public interface TsysDataprivilegeMapper<T> extends BaseSqlMapper<T> {
       
	public List selectPageTsysDataprivilegeByMap(Map para);

	public List selectPageTsysDataprivilege(TsysDataprivilege dataprivilege);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteSelective(TsysDataprivilege dataprivilege)throws DataAccessException;
	
	/**
	 * 查询数量
	 * @param org
	 * @return
	 */
	public int selectDataprivilegeNum(TsysOrg org);
}