package com.example.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;

import com.example.common.SystemConstant;
import com.example.pojo.userInfo.UserInfoBean;
import com.example.util.SessionUtil;

@Order(1)
@WebFilter(filterName = "webfilter", urlPatterns = "/*")
public class Webfilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String requestUrl = httpRequest.getRequestURI();
		if (requestUrl.indexOf("login") < 0&&requestUrl.indexOf("res") < 0&&requestUrl.indexOf("script") < 0) {
			//登陆，csss,js等静态资源不需要校验
			UserInfoBean user = (UserInfoBean) SessionUtil.getObjectAttribute(httpRequest, SystemConstant.USER_INFO);
			if (user == null) {
				// 用户session失效重新登陆
				httpResponse.sendRedirect("/html/login.jsp");
				return;
			}
		}
		chain.doFilter(httpRequest, httpResponse);
	}

	@Override
	public void destroy() {
	}
}
