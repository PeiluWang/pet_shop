package com.upa.pet_shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.upa.pet_shop.entity.User;
import com.upa.pet_shop.exception.WebBackendException;
import com.upa.pet_shop.request.user.LoginRequest;
import com.upa.pet_shop.request.user.RegisterRequest;
import com.upa.pet_shop.response.BaseResponse;
import com.upa.pet_shop.response.user.LoginResponse;
import com.upa.pet_shop.response.user.RegisterResponse;
import com.upa.pet_shop.service.UserService;

/**
 * 用户相关操作
 * 不涉及管理员管理操作
 */
@RestController
@RequestMapping(value = "/back/users")
public class UserController {

	final Logger SYSTEM_LOGGER = LoggerFactory.getLogger("system");
	
	@Autowired
	UserService userService;
	
	/**
	 * 登录
	 * @param req，请求
	 * @return 回复
	 * @throws WebBackendException
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public BaseResponse login(@RequestBody LoginRequest req) throws WebBackendException{
		User user = userService.login(req.userName, req.password);
		return new LoginResponse(user.getId(), user.getUserName());
	}
	
	/**
	 * 注册
	 * @param req
	 * @return
	 * @throws WebBackendException
	 */
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public BaseResponse register(@RequestBody RegisterRequest req) throws WebBackendException{
		User user = userService.register(req.userName, req.email, req.password);
		return new RegisterResponse(user.getId(), user.getUserName());
	}
	
}
