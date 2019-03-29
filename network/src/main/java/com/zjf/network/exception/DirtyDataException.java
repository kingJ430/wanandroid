package com.zjf.network.exception;

/**
 * 脏数据异常(一般检验不通过时，RX流中抛出的异常)
 */
public class DirtyDataException extends RuntimeException {



	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int code;

	public DirtyDataException(String Message) {
		super(Message);
	}

	public DirtyDataException(String Message, int code) {
		super(Message);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int pCode) {
		code = pCode;
	}
}
