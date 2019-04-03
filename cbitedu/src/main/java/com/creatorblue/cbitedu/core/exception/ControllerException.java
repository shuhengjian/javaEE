package com.creatorblue.cbitedu.core.exception;

public class ControllerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ControllerException(String msg) {
		super(msg);
		// 日志
	}

	public ControllerException(String code, Throwable cause) {
		super(code, cause);
	}
}