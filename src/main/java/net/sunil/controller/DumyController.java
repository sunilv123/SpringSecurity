package net.sunil.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sunil.bean.UserBean;
import net.sunil.bean.UserDto;

@RestController
@RequestMapping("/api/dummy")
public class DumyController {

	
	@GetMapping("/get")
	public UserBean get()
	{
		return new UserBean("Sunil", "Kumar");
	}
	
	@PostMapping("/test")
	public void saveUser(@RequestBody UserDto userDto) {
		System.out.println(userDto.toString());
	}
	
}
