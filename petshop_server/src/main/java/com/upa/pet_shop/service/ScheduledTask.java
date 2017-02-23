package com.upa.pet_shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时执行
 *
 */
@Component
public class ScheduledTask {

	final Logger SYSTEM_LOGGER = LoggerFactory.getLogger("system");
	
	/**
	 * 单位:ms
	 * 每半小时执行一次
	 */
	@Scheduled(fixedRate = 1800000)
	public void noticeUpdate(){
		/*
		 * TODO: 定时执行任务
		 */
	}
}
