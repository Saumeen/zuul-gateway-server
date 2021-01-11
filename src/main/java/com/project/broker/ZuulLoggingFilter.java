package com.project.broker;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.project.broker.proxy.BrokerFignClientProxy;
import com.project.broker.proxy.IpModel;
import com.project.broker.utils.IpAddressUtils;

@Component
public class ZuulLoggingFilter extends ZuulFilter {

	@Autowired
	private BrokerFignClientProxy proxy;
	
	
	
	@Autowired
	private IpAddressUtils ipUtils;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		log.info("request -> {}  request uri -> {}", request, request.getRequestURI());
		IpModel ipModel = new IpModel();
		ipModel.setIpAddress(ipUtils.getIpAddress());
		ipModel.setUri(request.getRequestURI());
		String sendIpAddress = proxy.sendIpAddress(ipModel);
		log.info("IP Address is -> {} ",sendIpAddress);
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
