package com.upa.pet_shop.util;

import java.util.Date;

public class TokenUtil {

	/**
	 * 为用户生成令牌
	 */
	public static String newToken(String userName) {
		// 用户名+当前时间+随机盐拼接起来，取sha1
		String now = new Date().toString();
		String seed = userName + now + EncryptionUtil.salt();
		String token = EncryptionUtil.encrypt(seed);
		return token;
	}
	
	public static Date newTokenDueTime(){
		//7天后后过期
		return DateUtil.addDay(new Date(), 7); 
	}
}
