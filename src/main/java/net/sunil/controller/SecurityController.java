package net.sunil.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sunil.dto.EncryptedReq;
import net.sunil.dto.LoginBean;
import net.sunil.dto.UserBean;

@RestController
@RequestMapping("/api/security")
@CrossOrigin
public class SecurityController {

	@GetMapping("/get")
	public UserBean get()
	{
		return new UserBean("Sunil", "Kumar");
	}
	
	@PostMapping(value="/login", consumes = "application/json")
	public LoginBean login(@RequestBody EncryptedReq<LoginBean> req) throws Exception {
		System.out.println("encryptedString == "+req.getPayLoad());
		
		LoginBean bean = req.getData(LoginBean.class);
		
		System.out.println(bean.getUserName()+" --- "+bean.getPassword());
		
		return new LoginBean();
	}
	
}
