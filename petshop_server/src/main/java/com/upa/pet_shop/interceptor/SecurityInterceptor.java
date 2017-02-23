package com.upa.pet_shop.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upa.pet_shop.constant.ErrorCode;
import com.upa.pet_shop.exception.WebBackendException;
import com.upa.pet_shop.response.BaseResponse;
import com.upa.pet_shop.response.ErrorResponse;
import com.upa.pet_shop.service.UserService;
import com.upa.pet_shop.util.HttpUtil;
import com.upa.pet_shop.util.StringUtil;


/**
 * 安全拦截器：
 * 检查用户是否有权限访问页面
 * 没做操作延长token的有效时间
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter{
	
	final Logger API_LOGGER = LoggerFactory.getLogger("api_request");
	
	UserService userService;
	
	public SecurityInterceptor(UserService userService) {
		this.userService=userService;
	}
	
	/**
	 * 在访问接口之前的拦截操作：验证用户是否有权限访问该接口
	 */
	@Override
	public boolean preHandle(@RequestBody HttpServletRequest request, HttpServletResponse response, 
			Object handler) throws Exception{
		/* 
		 * 获取token 
		 */
		// 先从url中获取token
		String token=request.getParameter("token");
		if(token==null || token.isEmpty()){
			// 从cookie中获取token
			if(request.getCookies()!=null){
				for(Cookie cookie :request.getCookies()){
					if(cookie.getName().equals("token")){
						token = cookie.getValue();
					}
				}
			}
		}
		/*
		 * 记录访问者访问的信息
		 */
		String ip=HttpUtil.getRequestIp(request);
		String url=request.getServletPath();
		API_LOGGER.info("ip:{} target:{} token:{}", ip, url, token);
		/* 检验token是否有权限访问该接口 */
		if(StringUtil.isEmpty(token)){
			writeResponse(new ErrorResponse(ErrorCode.TOKEN_EMPTY,"您需要提供token以验证身份"),response);
			return false;
		}
		String api=request.getServletPath();
		try{
			/*
			 * 检查token是否过期，是否有权限访问接口
			 */
			userService.isTokenValid(token, api);
		}catch(WebBackendException e){
			writeResponse(new ErrorResponse(e.getErrorCode(),e.getErrorMessage()),response);
			return false;
		}catch(Exception e){
			writeResponse(new ErrorResponse(ErrorCode.OTHER,e.getMessage()),response);
			return false;
		}
		return true;
	}
	
	/**
	 * 在访问接口之后的拦截操作：
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler, ModelAndView modelAndView) throws Exception{
		
	}
	
	/**
	 * 整个请求结束后的操作
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex)throws Exception{

	}

    private void writeResponse(BaseResponse content, HttpServletResponse response) throws Exception {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String str = new ObjectMapper().writeValueAsString(content);
        response.getWriter().write(str);
    }
	
}
