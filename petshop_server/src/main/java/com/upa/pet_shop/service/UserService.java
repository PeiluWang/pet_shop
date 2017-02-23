package com.upa.pet_shop.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upa.pet_shop.constant.ErrorCode;
import com.upa.pet_shop.entity.User;
import com.upa.pet_shop.exception.WebBackendException;
import com.upa.pet_shop.repository.UserRepository;
import com.upa.pet_shop.util.EncryptionUtil;
import com.upa.pet_shop.util.StringUtil;
import com.upa.pet_shop.util.TokenUtil;

@Service
@Transactional
public class UserService extends BaseService {

	final Logger SYSTEM_LOGGER = LoggerFactory.getLogger("system");
	
	@Autowired
	UserRepository userRepository;

	/**
	 * 添加User
	 * @return
	 * @throws WebBackendException 
	 */
	public User addUser(String userName, String email, String password) throws WebBackendException{
		/**
		 * 检查输入合法性
		 */
		if(StringUtil.isEmpty(userName)){
			throw new WebBackendException(ErrorCode.USERNAME_EMPTY);
		}
		if(StringUtil.isEmpty(email)){
			throw new WebBackendException(ErrorCode.EMAIL_EMPTY);
		}
		if(StringUtil.isEmpty(password)){
			throw new WebBackendException(ErrorCode.PASSWORD_EMPTY);
		}
		
		/**
		 * TODO:检查密码是否太简单
		 */
		
		/**
		 * 检查用户名与邮箱是否存在
		 */
		Iterable<User> checkUsers = userRepository.findByUserNameOrEmail(userName, email);
		for(User user: checkUsers){
			if(user.getUserName().equals(userName)){
				throw new WebBackendException(ErrorCode.USERNAME_EXIST);
			}
			if(user.getEmail().equals(email)){
				throw new WebBackendException(ErrorCode.EMAIL_EXIST);
			}
		}
		/**
		 * 赋值
		 */
		User user = new User();
		user.setUserName(userName);
		user.setEmail(email);
		//加密密码
		String encryptedPassword=EncryptionUtil.encrypt(password + EncryptionUtil.finalSalt());
		user.setPassword(encryptedPassword);
		user.setToken(TokenUtil.newToken(userName));
		user.setTokenDueTime(TokenUtil.newTokenDueTime());		
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		userRepository.save(user);
		SYSTEM_LOGGER.debug("add user success userName:{} email:{}",userName, email);
		return user;
	}
	
	/**
	 * 根据id获取User
	 * @param userId
	 * @return
	 */
	public User getUserById(long userId){
		return userRepository.findOne(userId);
	}
	
	/**
	 * 根据token获取User
	 * @param token
	 * @return
	 */
	public User getUserByToken(String token){
		return userRepository.findByToken(token);
	}

	/**
	 * token是否合规
	 * @return
	 */
	public boolean isTokenValid(String token, String api) throws WebBackendException{
		/**
		 * 判断token是否有权限
		 */
		return true;
	}
	
	/**
	 * 登录
	 * @param userName
	 * @param password
	 * @return
	 * @throws WebBackendException
	 */
	public User login(String userName, String password) throws WebBackendException{
		User user = userRepository.findByUserName(userName);
		if(user==null){
			throw new WebBackendException(ErrorCode.USER_NOT_FOUND);
		}
		String encryptedPassword=EncryptionUtil.encrypt(password + EncryptionUtil.finalSalt());
		if(!encryptedPassword.equals(user.getPassword())){
			throw new WebBackendException(ErrorCode.PASSWORD_NOT_CORRECT);
		}
		return user;
	}
	
	/**
	 * 注册
	 * @param userName
	 * @param email
	 * @param password
	 * @return
	 * @throws WebBackendException
	 */
	public User register(String userName, String email, String password) throws WebBackendException{
		return addUser(userName, email, password);
	}
}
