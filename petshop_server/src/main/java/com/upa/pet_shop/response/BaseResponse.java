package com.upa.pet_shop.response;

import com.upa.pet_shop.constant.ErrorCode;

/**
 * 基础回复，包含错误码
 */
public class BaseResponse {

	// 错误码
	public int errorId;
	
	public BaseResponse(int errorId) {
		this.errorId = errorId;
	}
	
	public BaseResponse(){
		this.errorId = ErrorCode.SUCCESS.getErrorId();
	}
	
	public BaseResponse(ErrorCode errorCode) {
		this.errorId = errorCode.getErrorId();
	}
}
