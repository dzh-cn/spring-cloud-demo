package com.spring.demo.ticket.feign;

import com.spring.demo.ticket.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user")
public interface UserFeignClient {
	@GetMapping("{id}")
	User findById(@PathVariable("id") Integer id);

	@PostMapping("save")
	User save(@RequestBody User user);
}
