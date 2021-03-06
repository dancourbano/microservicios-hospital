package com.daniel.cjavazuul.zuulms.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostLogsFilter extends ZuulFilter{
	private static Logger log=LoggerFactory.getLogger(PreLogsFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx=RequestContext.getCurrentContext();
		HttpServletRequest request=ctx.getRequest();
		log.info("Ingresando a FILTRO POST");
		Long timeInit=(Long)request.getAttribute("timeInit");
		Long timeEnd=System.currentTimeMillis();
		Long timeElapsed=timeEnd-timeInit;
		log.info(String.format("Tiempo transcurrido en segundos: %s",timeElapsed.doubleValue()/1000.0));
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
