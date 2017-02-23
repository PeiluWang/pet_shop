package com.upa.pet_shop.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.upa.pet_shop.constant.ErrorCode;
import com.upa.pet_shop.response.BaseResponse;
import com.upa.pet_shop.response.ErrorResponse;

/**
 * 错误处理的控制器
 * 统一处理Exception
 */
@ControllerAdvice
public class WebBackendExceptionHandler {
	
	final Logger SYSTEM_LOGGER = LoggerFactory.getLogger("system");
	
	/**
	 * 统一处理一般错误
	 * 注：该方法对拦截器中的异常无效
	 */
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
    	//若有后台定义的错误码则按该错误码返回
    	if(e instanceof WebBackendException){
    		e.printStackTrace();
    		WebBackendException webExp=(WebBackendException)e;
    		SYSTEM_LOGGER.warn("WebBackendException errorCode:{} errorMessage:{}", webExp.getErrorCode(), webExp.getErrorMessage());
    		return new ErrorResponse(webExp.getErrorCode(),webExp.getErrorMessage());
    	} else {
    	//其他
    		e.printStackTrace();
    		SYSTEM_LOGGER.warn("OTHER Exception \n"+e.getStackTrace().toString());
    		return new ErrorResponse(ErrorCode.OTHER);
    	}
    }
	
	/**
	 * 处理404 not found的错误
	 */
	@ExceptionHandler(value = NoHandlerFoundException.class)
	public BaseResponse handleError404(HttpServletRequest req, Exception e){
		SYSTEM_LOGGER.warn("404 not found! url:{}", req.getServletPath());
		return new ErrorResponse(ErrorCode.API_NOT_FOUND);
	}
}
