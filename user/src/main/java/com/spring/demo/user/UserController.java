package com.spring.demo.user;

import com.spring.demo.user.dao.UserRepository;
import com.spring.demo.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("{id}")
	User findById(@PathVariable("id") Integer id) {
		return userRepository.findOne(id);
	}

	@PostMapping("save")
	User save(@RequestBody User user) {
		return userRepository.save(user);
	}
}
