package com.zjf.network.exception;

/**
 * Code类型错误
 */
public class ZNumberFormatException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String mCode;

	public ZNumberFormatException(String Message, String code) {
		super(Message);
		this.mCode = code;
	}

	public String getCode() {
		return mCode;
	}
}
