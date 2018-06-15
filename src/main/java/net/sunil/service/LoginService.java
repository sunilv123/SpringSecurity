package net.sunil.service;

import java.util.List;

import net.sunil.bean.LoginBean;
import net.sunil.modal.AppUser;

public interface LoginService {

	public LoginBean login(LoginBean loginBean);

	public LoginBean signup(LoginBean loginBean);

	public List<AppUser> getAllUsers(String xAuth) throws Exception;
	
}
