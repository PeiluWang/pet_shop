package com.upa.pet_shop.util;

/**
 * 字符串操作
 * @author peilu.wang
 *
 */
public class StringUtil {

	public static boolean isEmpty(String text){
		if(text==null || text.isEmpty()){
			return true;
		}
		return false;
	}
}
