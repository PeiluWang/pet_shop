package com.upa.pet_shop.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 在网站启动时初始化
 * 可用于给系统环境变量初始化赋值
 * @author peilu.wang
 *
 */
@Component
public class EnvConfig {
	
	final Logger SYSTEM_LOGGER = LoggerFactory.getLogger("system");
	
	/**
	 * 初始化系统变量
	 */
	public void init(){
		/**
		 * TODO: 初始化环境变量
		 * 从数据库中加载
		 * 可以加载不同的配置
		 */
		
	}
	
}
