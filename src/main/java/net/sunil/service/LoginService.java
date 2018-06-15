package net.sunil.service;

import net.sunil.bean.LoginBean;

public interface LoginService {

	public LoginBean login(LoginBean loginBean);

	public LoginBean signup(LoginBean loginBean);
	
}
