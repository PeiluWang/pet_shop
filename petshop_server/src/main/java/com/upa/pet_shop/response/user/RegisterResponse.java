package com.upa.pet_shop.response.user;

import com.upa.pet_shop.response.BaseResponse;

public class RegisterResponse extends BaseResponse{

	public Long userId;
	public String userName;
	
	public RegisterResponse(Long userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}
	
	
}
