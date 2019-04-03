/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2014 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

package com.creatorblue.cbitedu.system.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.creatorblue.cbitedu.core.baseclass.domain.BaseDomain;
public class TuserPost extends BaseDomain{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "用户岗位关联表";
	public static final String ALIAS_USER_JOBID = "userJobid";
	public static final String ALIAS_POST_ID = "岗位ID";
	public static final String ALIAS_USER_ID = "用户ID";
	

    /**
     * userJobid       db_column: USER_JOBID 
     */	
	private java.lang.String userJobid;
    /**
     * 岗位ID       db_column: POST_ID 
     */	
	private java.lang.String postId;
    /**
     * 用户ID       db_column: USER_ID 
     */	
	private java.lang.String userId;
	//columns END

	public TuserPost(){
	}

	public TuserPost(
		java.lang.String userJobid
	){
		this.userJobid = userJobid;
	}

	public void setUserJobid(java.lang.String value) {
		this.userJobid = value;
	}
	
	public java.lang.String getUserJobid() {
		return this.userJobid;
	}
	public void setPostId(java.lang.String value) {
		this.postId = value;
	}
	
	public java.lang.String getPostId() {
		return this.postId;
	}
	public void setUserId(java.lang.String value) {
		this.userId = value;
	}
	
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	private TsysUserinfo tsysUserinfo;
	
	public void setTsysUserinfo(TsysUserinfo tsysUserinfo){
		this.tsysUserinfo = tsysUserinfo;
	}
	
	public TsysUserinfo getTsysUserinfo() {
		return tsysUserinfo;
	}
	
	private TsysPost tsysPost;
	
	public void setTsysPost(TsysPost tsysPost){
		this.tsysPost = tsysPost;
	}
	
	public TsysPost getTsysPost() {
		return tsysPost;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("UserJobid",getUserJobid())
			.append("PostId",getPostId())
			.append("UserId",getUserId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserJobid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TuserPost == false) return false;
		if(this == obj) return true;
		TuserPost other = (TuserPost)obj;
		return new EqualsBuilder()
			.append(getUserJobid(),other.getUserJobid())
			.isEquals();
	}
}

