package com.upa.pet_shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
/**
 * 支持跨域资源访问（CORS）
 * TODO: 本方法据说并不安全？有更好的解决策略？
 */
@Component
public class SimpleCORSFilter implements Filter {
	 @Override
	    public void init(FilterConfig filterConfig) throws ServletException {

	    }

	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    	HttpServletResponse res = (HttpServletResponse) response;
	        res.setHeader("Access-Control-Allow-Origin", "*");
	        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
	        res.setHeader("Access-Control-Max-Age", "3600");
	        res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	        chain.doFilter(request, response);
	    }

	    @Override
	    public void destroy() {

	    }
}
