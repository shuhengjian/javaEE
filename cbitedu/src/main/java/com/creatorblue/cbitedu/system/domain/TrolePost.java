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
public class TrolePost extends BaseDomain{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "岗位角色关联表";
	public static final String ALIAS_ROLE_POSTID = "rolePostid";
	public static final String ALIAS_ROLE_ID = "roleId";
	public static final String ALIAS_POST_ID = "岗位ID";
	

    /**
     * rolePostid       db_column: ROLE_POSTID 
     */	
	private java.lang.String rolePostid;
    /**
     * roleId       db_column: ROLE_ID 
     */	
	private java.lang.Long roleId;
    /**
     * 岗位ID       db_column: POST_ID 
     */	
	private java.lang.String postId;
	//columns END

	public TrolePost(){
	}

	public TrolePost(
		java.lang.String rolePostid
	){
		this.rolePostid = rolePostid;
	}

	public void setRolePostid(java.lang.String value) {
		this.rolePostid = value;
	}
	
	public java.lang.String getRolePostid() {
		return this.rolePostid;
	}
	public void setRoleId(java.lang.Long value) {
		this.roleId = value;
	}
	
	public java.lang.Long getRoleId() {
		return this.roleId;
	}
	public void setPostId(java.lang.String value) {
		this.postId = value;
	}
	
	public java.lang.String getPostId() {
		return this.postId;
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
			.append("RolePostid",getRolePostid())
			.append("RoleId",getRoleId())
			.append("PostId",getPostId())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getRolePostid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TrolePost == false) return false;
		if(this == obj) return true;
		TrolePost other = (TrolePost)obj;
		return new EqualsBuilder()
			.append(getRolePostid(),other.getRolePostid())
			.isEquals();
	}
}

