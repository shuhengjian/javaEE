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
import com.creatorblue.cbitedu.system.domain.TsysOrg;
import com.creatorblue.cbitedu.system.domain.ZtreeInfo;

public interface TsysOrgMapper<T> extends BaseSqlMapper<T> {
       
	public List selectPageTsysOrgByMap(Map para);
  
	public List selectPageTsysOrg();
	
	public List<ZtreeInfo> selectSysOrgInfo(TsysOrg tsysOrg);
	
	public String selectSysSequInfo();
	
	public List<T> selectSysSeqMaxId();
	
	public void updateSeq(String seqId);
	
	public void updateSeqByDel();	
	
	public String selectSysOrgAncesty(String orgId);
	
	public TsysOrg selectOrgIdByOrgObject(Map para);
	
	public String selectChildCount(String orgId);
	
	public List selectChildById(String orgIdString);
	
	public Integer selectOrgCount();
	
	public void updateBySort(TsysOrg tsysOrg);
	
	public void updateSortId(Integer sortId);
	
	public void updateStatus(String orgId);
}