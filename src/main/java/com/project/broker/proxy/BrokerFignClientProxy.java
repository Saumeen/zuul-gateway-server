package com.project.broker.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "project-broker-api",url = "https://broker-micro.herokuapp.com")
public interface BrokerFignClientProxy {

	@PostMapping("/api/master/sendIpAddress")
	public String sendIpAddress(IpModel ipModel);
}

	

