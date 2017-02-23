package com.upa.pet_shop.repository;

import org.springframework.data.repository.CrudRepository;

import com.upa.pet_shop.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	/**
	 * 根据userName查找User
	 * @param userName
	 * @return
	 */
	public User findByUserName(String userName);
	
	/**
	 * 根据token查找User
	 * @param token
	 * @return
	 */
	public User findByToken(String token);
	
	/**
	 * 根据用户名查找User
	 * @param token
	 * @return
	 */
	public Iterable<User> findByUserNameOrEmail(String userName, String email);
}
