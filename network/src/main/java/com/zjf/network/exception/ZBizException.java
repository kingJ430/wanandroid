package com.zjf.network.exception;

/**
 * 一般业务异常
 */
public class ZBizException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int mCode;

	public ZBizException(String Message, int code) {
		super(Message);
		this.mCode = code;
	}

	public int getCode() {
		return mCode;
	}
}
