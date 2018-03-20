package com.spring.demo.ticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class TicketCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(com.spring.demo.ticket.TicketCloudApplication.class, args);
	}

	@Bean
	@LoadBalanced // 实现负载均衡
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
