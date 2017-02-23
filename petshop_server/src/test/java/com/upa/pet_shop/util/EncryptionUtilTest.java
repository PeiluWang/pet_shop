package com.upa.pet_shop.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class EncryptionUtilTest {

	@Test
	public void testEncrypt(){
		String rawText = "hello";
		String encryptedText = EncryptionUtil.encrypt(rawText);
		System.out.println(encryptedText);
		assertEquals(encryptedText,"aaf4c61ddcc5e8a2dabede0f3b482cd9aea9434d");
	}
}
