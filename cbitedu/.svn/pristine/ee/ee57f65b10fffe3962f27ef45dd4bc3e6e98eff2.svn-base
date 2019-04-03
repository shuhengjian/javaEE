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
import com.creatorblue.cbitedu.system.domain.TsysPostTreeinfo;
import com.creatorblue.cbitedu.system.domain.TsysUserinfo;
import com.creatorblue.cbitedu.system.domain.TuserPost;
import com.creatorblue.cbitedu.system.domain.ZtreeInfo;

public interface TsysUserinfoMapper<T> extends BaseSqlMapper<T> {
       
	public List selectPageTsysUserinfoByMap(Map para);
	
	public List selectExitsUserByUsername(Map para);

	public List selectPageTsysUserinfo(TsysUserinfo userinfo);
	
	public List<ZtreeInfo> selectSysOrgInfo();
	
	public int selectExitsUserByUsername(String userName);
	
	public List<TsysPostTreeinfo> selectSysPostInfo(Map<String,String> map);
	
	public void insertUserPost(TuserPost userPost);


	public void resetPassword(Map<String, Object> map);

	public void changeUserState(Map<String, Object> map);

	public List<ZtreeInfo> selectRoleInfo();

	public int queryUserCountByOrgId(String orgId);
	
	public List selectUserinfoByOrgId(String orgId);
	
	public List<String> selectChildByOrgId(Map<String, Object> map);

	public Map<String, Object> getSortNumByOrgId(String orgId);
	
	public void updateBySort(TsysUserinfo tsysuser);
	
	
}