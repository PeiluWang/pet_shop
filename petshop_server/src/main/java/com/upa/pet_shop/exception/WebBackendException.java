package com.upa.pet_shop.exception;

import com.upa.pet_shop.constant.ErrorCode;


/**
 * 用于定义网站后台业务生成的异常事件
 * 能够记录错误代码与错误信息，错误信息用于跟踪错误
 */
public class WebBackendException extends Exception {
	
	ErrorCode errorCode;//错误代码
	String errorMessage;//错误信息

	public WebBackendException(ErrorCode errorCode){
		this.errorCode=errorCode;
		this.errorMessage=errorCode.getErrorMsg();
	}
	
	public WebBackendException(ErrorCode errorCode, String errorMessage){
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
