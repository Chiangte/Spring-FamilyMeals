package com.example.entity;

/**
 * @Author: Awan
 * @Description:
 * @Date Created in 14:22  2018/11/27
 */
public class UnloginException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnloginException() {
	}
	public UnloginException(String message) {
		super(message);
	}
	public UnloginException(Throwable cause) {
		super(cause);
	}
	public UnloginException(String message, Throwable cause) {
		super(message, cause);
	}
	public UnloginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
