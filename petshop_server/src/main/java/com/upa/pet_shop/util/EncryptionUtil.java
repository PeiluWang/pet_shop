package com.upa.pet_shop.util;

import java.security.MessageDigest;
import java.util.Random;

public class EncryptionUtil {
	
	/**
	 * 加密用的盐的基础项，防止对方破解随机数，16位
	 */
	private final static String baseSalt="er32QW!@<}9U*&+m";

	/**
	 * 以sha1方式加密
	 * @param rawText
	 * @return
	 */
	public static String encrypt(String rawText){
		return encrypt(rawText,"sha1");
	}
	
	/**
	 * 以指定方式编码加密
	 * @param rawText，加密内容
	 * @param algorithm，加密（编码）方式，md5 or sha1
	 * @return
	 */
	public static String encrypt(String rawText, String algorithm){
		if(rawText==null){
			rawText="";
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(rawText.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 加密用的盐，固定
	 * @return
	 */
	public static String finalSalt(){
		return baseSalt;
	}
	
	/**
	 * 生成加密用的盐
	 * @return
	 */
	public static String salt(){
		/*
		 * 随机字符串
		 */
		Random rand = new Random();
		StringBuffer salt=new StringBuffer();
		for (int i=0;i<16;++i){
			//ascii id:33~126
			int ascii = rand.nextInt(93)+33;
			salt.append((char)ascii);
		}
		return salt.toString()+baseSalt;
	}
	
	/**
	 * 把二进制digest转成字符串
	 */
	private static String getFormattedText(byte[] byteDigest) {
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < byteDigest.length; offset++) {
			i = byteDigest[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}
}
