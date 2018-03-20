package com.spring.demo.ticket;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.demo.ticket.dao.TicketRepository;
import com.spring.demo.ticket.entity.Ticket;
import com.spring.demo.ticket.entity.User;
import com.spring.demo.ticket.feign.UserFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	DiscoveryClient discoveryClient;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	UserFeignClient userFeignClient;

	static ObjectMapper objectMapper = new ObjectMapper();
	static {
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
	}

	Logger logger = LoggerFactory.getLogger(TicketController.class);

	@RequestMapping("all")
	List<Ticket> all() throws JsonProcessingException {

		URI uri = this.discoveryClient.getInstances("user").get(0).getUri();
		logger.info("user service uri:{}", uri);
		User user = restTemplate.getForObject("http://user/1", User.class);
		logger.info(">>USER:" + objectMapper.writeValueAsString(user));
		return ticketRepository.findAll();
	}

	@RequestMapping("user/{userId}")
	User userDetail(@PathVariable Integer userId) {
		return userFeignClient.findById(userId);
	}

	@RequestMapping("user/save")
	User save(User user) throws JsonProcessingException {
		logger.info("TicketController#save user: {}", objectMapper.writeValueAsString(user));
		return userFeignClient.save(user);
	}

	@GetMapping("instance/{serviceId}")
	List<ServiceInstance> showInfo(@PathVariable String serviceId) {
		return this.discoveryClient.getInstances(serviceId);
	}
}
