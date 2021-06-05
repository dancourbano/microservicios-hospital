package com.daniel.cjavazuul.zuulms.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreLogsFilter extends ZuulFilter{
	private static Logger log=LoggerFactory.getLogger(PreLogsFilter.class);
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx=RequestContext.getCurrentContext();
		HttpServletRequest request=ctx.getRequest();
		log.info(String.format("%s con el metodo %s request en: %s",PreLogsFilter.class, 
				request.getMethod()
				,request.getRequestURL().toString()));
		long timeInit=System.currentTimeMillis();
		request.setAttribute("timeInit", timeInit);
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
