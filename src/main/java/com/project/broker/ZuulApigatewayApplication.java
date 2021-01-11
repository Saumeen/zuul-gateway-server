package com.project.broker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.project.broker.proxy.BrokerFignClientProxy;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoConfiguration
@EnableFeignClients("com.project.broker.proxy")
public class ZuulApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApigatewayApplication.class, args);
	}

}
