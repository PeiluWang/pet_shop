package com.upa.pet_shop.response.user;

import com.upa.pet_shop.response.BaseResponse;

public class LoginResponse extends BaseResponse{

	public Long userId;
	public String userName;
	
	public LoginResponse(Long userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}
	
	
}
