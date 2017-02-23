package com.upa.pet_shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.upa.pet_shop.util.HttpUtil;

/**
 * 日志拦截器：
 * 记录用户信息以及访问时间
 */
public class LogInterceptor extends HandlerInterceptorAdapter{

	
	final Logger SYSTEM_LOGGER = LoggerFactory.getLogger("system");
	final Logger API_REQUEST_LOGGER = LoggerFactory.getLogger("api_request");
	
	/**
	 * 在访问接口之前的操作：
	 * 记录用户ip，访问时间
	 */
	@Override
	public boolean preHandle(@RequestBody HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		// 获取ip
		String ip=HttpUtil.getRequestIp(request);
		// 获取要访问的接口地址
		String url=request.getServletPath();
		API_REQUEST_LOGGER.info("request ip:{} target:{}", ip, url);
		
		/* DEBUG模式下记录访问时间 */
		if(SYSTEM_LOGGER.isDebugEnabled()){
			long startTime = System.currentTimeMillis();
			request.setAttribute("requestStartTime", startTime);
			/* 记录访问者信息 */
			String sessionId = request.getSession().getId();
			SYSTEM_LOGGER.debug("request ip:{} target:{} session_id:{}", ip, url, sessionId);
		}
		return true;
	}
	
	/**
	 * 在接口处理请求之后的操作：
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{

	}
	
	/**
	 * 整个请求结束后的操作：
	 * 记录处理用时
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex)throws Exception{
		/* 收集一次用户访问的log信息  */
		if(SYSTEM_LOGGER.isDebugEnabled()){
			String sessionId = request.getSession().getId();
			// 计算整个请求花费的时间
			long startTime = (Long)request.getAttribute("requestStartTime");
			long endTime = System.currentTimeMillis();
			long executeTime = endTime - startTime;
			
			SYSTEM_LOGGER.debug("response status:{} handle_time:{}ms handler:{} session_id:{}",
					response.getStatus(), executeTime, getShortHandlerName(handler), sessionId);
		}
	}
	
	/**
	 * 截短handler的名称，去除相同的前缀部分
	 * 返回示例：SysInfoController.isAlive(String)
	 * 主要用于跟踪重定向controller的访问
	 * @param handler
	 * @return
	 */
	private String getShortHandlerName(Object handler){
		//切除返回变量与前缀字符串，只保留接口名称
		String handlerName=String.valueOf(handler);
		int cut_index = handlerName.lastIndexOf(".controller.");
		if(cut_index>0){
			handlerName=handlerName.substring(cut_index+12);
		}
		return handlerName.replace("java.lang.", "");
	}
}
