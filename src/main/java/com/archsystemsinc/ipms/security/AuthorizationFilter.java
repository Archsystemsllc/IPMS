package com.archsystemsinc.ipms.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import com.archsystemsinc.ipms.sec.util.SecurityUtil;

@Component
public class AuthorizationFilter extends GenericFilterBean {

	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		if (SecurityUtil.hasPermission(((HttpServletRequest) req).getRequestURI()))
			chain.doFilter(req, res);
		else
			((HttpServletResponse) res).sendError(HttpStatus.SC_FORBIDDEN);
		
	}
}
