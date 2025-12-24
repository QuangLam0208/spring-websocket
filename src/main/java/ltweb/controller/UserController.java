package ltweb.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ltweb.entity.UserInfo;
import ltweb.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	@RequestMapping("/new")
	public String addUser(@RequestBody UserInfo userInfo) {
		return userService.addUser(null);
	}
}
