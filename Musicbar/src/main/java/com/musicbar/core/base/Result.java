package com.musicbar.core.base;

import org.json.JSONObject;

import com.github.pagehelper.Page;
/**
 * json数据格式转换类
 * @author Administrator
 *
 */
public class Result {
	private int code;//状态
	private Object data;//数据
	private String msg;//提示信息
	
	public static Result OK(){
		return new Result(1);
	}
	
	public static Result error(){
		return new Result(0);
	}
	
	public Result(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		if(data instanceof Page){
			JSONObject obj = new JSONObject();
			obj.put("list", data);
			Page p = (Page)data;
			obj.put("page", new JSONObject(new com.musicbar.core.base.Page(p.getPageNum(),p.getPageSize(),p.getTotal())));
			this.data = obj;
		}else{
			this.data = data;
		}
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new JSONObject(this).toString();
		//return net.sf.json.JSONObject.fromObject(this).toString();
	}
}
