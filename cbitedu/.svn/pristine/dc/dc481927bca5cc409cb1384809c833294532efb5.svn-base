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
import com.creatorblue.cbitedu.system.domain.TsysPost;

public interface TsysPostMapper<T> extends BaseSqlMapper<T> {
       
	public List<T> selectPageTsysPostByMap(Map<String, Object> para);

	public List<T> selectPageTsysPost();

	public List<T> checkTheNameWithParentId(TsysPost tsysPost);
}