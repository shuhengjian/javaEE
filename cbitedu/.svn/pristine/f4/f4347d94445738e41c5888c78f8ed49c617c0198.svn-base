package com.creatorblue.cbitedu.core.exception;

public class HihSoftException extends RuntimeException {
	private static final long serialVersionUID = -4138902797063401170L;
	// 异常代码
	private String key;
	private Object[] values;

	// 构造器重载
	public HihSoftException() {
		super();
	}

	public HihSoftException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public HihSoftException(String message) {
		super(message);
	}

	public HihSoftException(Throwable throwable) {
		super(throwable);
	}

	public HihSoftException(String message, String key) {
		super(message);
		this.key = key;
	}

	public HihSoftException(String message, String key, Object value) {
		super(message);
		this.key = key;
		this.values = new Object[] { value };
	}

	public HihSoftException(String message, String key, Object[] values) {
		super(message);
		this.key = key;
		this.values = values;
	}

	public String getKey() {
		return key;
	}

	public Object[] getValues() {
		return values;
	}

}
