package com.upa.pet_shop.constant;

/**
 * 错误码
 * 枚举名称命名规范：不要添加无意义词，比如IS
 * 名词在前，形容词动词灾后
 * @author peilu.wang
 */
public enum ErrorCode {
	
	//成功:1000
	SUCCESS(1000,"success"),
	//资源类型错误:1001~2000
	API_NOT_FOUND(1001,"访问的API不存在"),
	TOKEN_INVALID(1002,"token不合规"),
	TOKEN_EMPTY(1003,"token不存在"),
	//用户类型错误:2001~3000
	USER_NOT_FOUND(2001,"用户不存在"),
	USERNAME_EMPTY(2002,"用户名为空"),
	PASSWORD_EMPTY(2003,"密码为空"),
	EMAIL_EMPTY(2003,"邮箱为空"),
	PHONE_EMPTY(2004,"电话为空"),
	USERNAME_EXIST(2005,"用户名已经存在"),
	EMAIL_EXIST(2006,"邮箱已存在"),
	PHONE_EXIST(2007,"电话已存在"),
	PASSWORD_NOT_CORRECT(2008,"密码不正确"),
	USER_ID_NOT_EXIST(2009,"用户id不正确"),
	//其它类型错误:999999
	OTHER(999999,"其它错误");
	/*
	 * TODO: 并行开发
	 * 错误码位数有含义
	 */
	
	private final int errorId;
	private final String errorMsg;
	
	ErrorCode(int errorId, String errorMsg){
		this.errorId=errorId;
		this.errorMsg=errorMsg;
	}

	public Integer getErrorId() {
		return errorId;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
