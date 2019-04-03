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
public class TsysUserprivilege extends BaseDomain{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//alias
	public static final String TABLE_ALIAS = "用户特权:不通过角色或岗位,单独给用户授权";
	public static final String ALIAS_USERPRVIID = "用户特权ID";
	public static final String ALIAS_USERID = "用户ID";
	public static final String ALIAS_MODULEID = "模块ID";
	public static final String ALIAS_OPERATEID = "模块操作ID";
	

    /**
     * 用户特权ID       db_column: USERPRVIID 
     */	
	private java.lang.String userprviid;
    /**
     * 用户ID       db_column: USERID 
     */	
	private java.lang.String userid;
    /**
     * 模块ID       db_column: MODULEID 
     */	
	private java.lang.String moduleid;
    /**
     * 模块操作ID       db_column: OPERATEID 
     */	
	private java.lang.String operateid;
	//columns END

	public TsysUserprivilege(){
	}

	public TsysUserprivilege(
		java.lang.String userprviid
	){
		this.userprviid = userprviid;
	}

	public void setUserprviid(java.lang.String value) {
		this.userprviid = value;
	}
	
	public java.lang.String getUserprviid() {
		return this.userprviid;
	}
	public void setUserid(java.lang.String value) {
		this.userid = value;
	}
	
	public java.lang.String getUserid() {
		return this.userid;
	}
	public void setModuleid(java.lang.String value) {
		this.moduleid = value;
	}
	
	public java.lang.String getModuleid() {
		return this.moduleid;
	}
	public void setOperateid(java.lang.String value) {
		this.operateid = value;
	}
	
	public java.lang.String getOperateid() {
		return this.operateid;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Userprviid",getUserprviid())
			.append("Userid",getUserid())
			.append("Moduleid",getModuleid())
			.append("Operateid",getOperateid())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getUserprviid())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof TsysUserprivilege == false) return false;
		if(this == obj) return true;
		TsysUserprivilege other = (TsysUserprivilege)obj;
		return new EqualsBuilder()
			.append(getUserprviid(),other.getUserprviid())
			.isEquals();
	}
}

