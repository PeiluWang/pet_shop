package com.upa.pet_shop.service;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.upa.pet_shop.Application;
import com.upa.pet_shop.entity.User;
import com.upa.pet_shop.exception.WebBackendException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Test
	@Transactional
	public void testAddUser(){
		try {
			String userName = "Neo2";
			String email = "neo@matrix.com";
			String password = "iamtheone";
			User user = userService.addUser(userName, email, password);
			assertNotNull(user);
		} catch (WebBackendException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Transactional
	public void testGetUserById(){
		long userId = 1L;
		User user = userService.getUserById(userId);
		assertEquals((long)user.getId(),userId);
	}
	

}
