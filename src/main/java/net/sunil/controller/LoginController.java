package net.sunil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sunil.bean.AppConstants;
import net.sunil.bean.GenericResponse;
import net.sunil.bean.LoginBean;
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
}
