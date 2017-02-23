package com.upa.pet_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**  
 * 获取系统基本信息
 * 检查系统是否正常运行
 * 改变系统配置
 */
@RestController
@RequestMapping(value = "/back/sysinfo")
public class SysInfoController {

	@Autowired
	private Environment env; //用来读取application.properties
	
	/**
	 * 获取工程绝对路径
	 */
	@RequestMapping("/path")
	public String getPath() {
		String path=System.getProperty("user.dir");
		return "Project location: "+path;
	}
	
	/**
	 * 汇报重要系统配置信息
	 */
	@RequestMapping("/report")
	public String getReport() {
		String output = "";
		output += "Project name: " + env.getProperty("projectName")+"<br>";
		output += "Version: " + env.getProperty("version")+"<br>";
		output += "Path: " + getPath()+"<br>";
		return output;
	}
	
	
	/**
	 * 输出版本号
	 * @throws WebBackendException 
	 */
	@RequestMapping("/version")
	public String getVersion() {
		String version = env.getProperty("version");
		return "Version: "+version;
	}
	
	/**
	 * 一点恶趣味
	 */
	@RequestMapping("/is_alive")
	public String isAlive(String name) {
		// 如果name不为空则输出特定问候
		if(name!=null && !name.isEmpty()){
			return "Hi " + name+ ", I am alive! Don't worry!";
		}
		return "Your Majesty, I am at your service!";
	}
	
}
