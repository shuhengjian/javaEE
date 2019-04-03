package com.creatorblue.cbitedu.system.domain;

import com.creatorblue.cbitedu.core.baseclass.domain.BaseDomain;

public class TsysSerialno extends BaseDomain{
	private static final long serialVersionUID = 5283605814555955276L;
	/*
	 * 主键
	 */
	private String serialno_id;
	/*
	 * 名称
	 */
	private String serial_name;
	/*
	 * 字段名
	 */
	private String secound_name;
	/*
	 * 规则 {YYYY}{MM}{DD}-{NO}
	 */
	private String formular_regx;
	/*
	 * 原规则 {YYYY}{MM}{DD}-{NO} 
	 */
	private String formular_regx_old;
	/*
	 * 生成方式:1递增 2\每日生成(每日从初始值记数,递增) 3\每月生成(每月从初始值记数,递增) 4\每年生成(每年从初始值记数递增)
	 */
	private String create_type;
	/*
	 * 流水号长度
	 */
	private String serial_length;
	/*
	 * 步长
	 */
	private String step;
	/*
	 * 初始值
	 */
	private String init_value;
	/*
	 * 当前值
	 */
	private String current_value;
	/*
	 * 备注说明
	 */
	private String remark;
	/*
	 * 表名
	 */
	private String tab_name;
	
	public String getSerialno_id() {
		return serialno_id;
	}
	public void setSerialno_id(String serialno_id) {
		this.serialno_id = serialno_id;
	}
	public String getSerial_name() {
		return serial_name;
	}
	public void setSerial_name(String serial_name) {
		this.serial_name = serial_name;
	}
	public String getSecound_name() {
		return secound_name;
	}
	public void setSecound_name(String secound_name) {
		this.secound_name = secound_name;
	}
	public String getFormular_regx() {
		return formular_regx;
	}
	public void setFormular_regx(String formular_regx) {
		this.formular_regx = formular_regx;
	}
	public String getCreate_type() {
		return create_type;
	}
	public void setCreate_type(String create_type) {
		this.create_type = create_type;
	}
	public String getSerial_length() {
		return serial_length;
	}
	public void setSerial_length(String serial_length) {
		this.serial_length = serial_length;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getInit_value() {
		return init_value;
	}
	public void setInit_value(String init_value) {
		this.init_value = init_value;
	}
	public String getCurrent_value() {
		return current_value;
	}
	public void setCurrent_value(String current_value) {
		this.current_value = current_value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTab_name() {
		return tab_name;
	}
	public void setTab_name(String tab_name) {
		this.tab_name = tab_name;
	}
	public String getFormular_regx_old() {
		return formular_regx_old;
	}
	public void setFormular_regx_old(String formular_regx_old) {
		this.formular_regx_old = formular_regx_old;
	}
	
	
	
}
