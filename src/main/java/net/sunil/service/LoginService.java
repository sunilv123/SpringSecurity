package net.sunil.service;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import net.sunil.bean.LoginBean;
import net.sunil.modal.AppUser;

public interface LoginService {

	public LoginBean login(LoginBean loginBean);

	public LoginBean signup(LoginBean loginBean);

	public List<AppUser> getAllUsers( Authentication authentication) throws Exception;

	public AppUser loadUserByUsername(String username);
	
}
