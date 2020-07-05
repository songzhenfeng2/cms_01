package com.szf.cms.util;
/**
 * 
 * @ClassName: CMSException 
 * @Description: 自定义异常
 * @author: 宋圳峰
 * @date: 2020年6月7日 上午9:32:14
 */
public class CMSException extends RuntimeException{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CMSException(String message) {
		super();
		this.message = message;
	}
	public CMSException() {
		super();
	}

	
}
