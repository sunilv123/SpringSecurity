package net.sunil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sunil.dto.AppConstants;
import net.sunil.dto.GenericResponse;
import net.sunil.dto.LoginBean;
import net.sunil.service.LoginService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@PostMapping("/login")
	public GenericResponse login(@RequestBody LoginBean loginBean) {
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_SUCCESS, loginService.login(loginBean));
	}
	
	@PostMapping("/signup")
	public GenericResponse signup(@RequestBody LoginBean loginBean) {
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_SUCCESS, loginService.signup(loginBean));
	}
	
	@GetMapping("/get-all-users")
	public GenericResponse getAllUsers( Authentication authentication) throws Exception {
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_SUCCESS, loginService.getAllUsers(authentication));
	}
}
