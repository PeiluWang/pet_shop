package com.upa.pet_shop.response;

import com.upa.pet_shop.constant.ErrorCode;

/**
 * 出现错误时的回复
 */
public class ErrorResponse extends BaseResponse {
	
	// 错误信息
	String errorMessage;
	
	public ErrorResponse(ErrorCode errorCode){
		super(errorCode.getErrorId());
		this.errorMessage=errorCode.getErrorMsg();
	}

	public ErrorResponse(ErrorCode errorCode, String errorMessage){
		super(errorCode.getErrorId());
		this.errorMessage=errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
