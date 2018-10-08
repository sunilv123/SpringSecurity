package net.sunil.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import net.sunil.config.JwtTokenUtils;
import net.sunil.dto.AppConstants;
import net.sunil.dto.LoginBean;
import net.sunil.modal.AppUser;
import net.sunil.repo.AppUserRepository;
import net.sunil.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{

//	@Autowired
//    private AuthenticationManager authenticationManager;
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenUtils jwtTokenUtils;
	
	@Override
	public LoginBean login(LoginBean loginBean) {
		
		Assert.notNull(loginBean.getEmail(), "User name doesn't exist");
		Assert.notNull(loginBean.getPassword(), "Password doesn't exist");
		
		AppUser appUser = appUserRepository.findByEmail(loginBean.getEmail());
		
		Assert.notNull(appUser, "User name or password something went wrong");
		
		Assert.isTrue( (passwordEncoder.matches(loginBean.getPassword(), appUser.getPassword()) ), "User name or password something went wrong");
		
		return getLoggedInUserBean(appUser);
	}
	
	private LoginBean getLoggedInUserBean(AppUser appUser) {
		
		LoginBean loginBean = new LoginBean();
		
		loginBean.setName(appUser.getName());
		loginBean.setEmail(appUser.getEmail());
		loginBean.setMobile(appUser.getMobile());
		loginBean.setToken(AppConstants.TOKEN_PREFIX+jwtTokenUtils.generateToken(appUser));

		return loginBean;
	}
	
    private String getHashedPassword(String password) {
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(password);
	}

	@Override
	public LoginBean signup(LoginBean loginBean) {
	
		Assert.notNull(loginBean.getEmail(), "User name doesn't exist");
		Assert.notNull(loginBean.getMobile(), "Mobile number doesn't exist");
		Assert.notNull(loginBean.getName(), "name doesn't exist");
		
	  //  Assert.isNull(appUserRepository.findByEmail(loginBean.getEmail()), loginBean.getEmail() +" already exist");
	   // Assert.isNull(appUserRepository.findByMobile(loginBean.getMobile()), loginBean.getMobile() +" already exist");
		
	    AppUser appUser = new AppUser();
	    
	    
	    appUser.setName(loginBean.getName());
	    appUser.setEmail(loginBean.getEmail());
	    appUser.setMobile(loginBean.getMobile());
	    appUser.setPassword(getHashedPassword(loginBean.getPassword()));
	    
	    appUserRepository.save(appUser);
	    System.out.println("saving appuser "+appUser.getName());
		return getLoggedInUserBean(appUser);
		
	}

	@Override
	public List<AppUser> getAllUsers( Authentication authentication) throws Exception {
		
		  try {
//			  System.out.println("xAuth : "+xAuth);
//	            final String user = Jwts.parser().setSigningKey(AppConstants.SECRET.getBytes())
//            		 .parseClaimsJws(xAuth.replace(AppConstants.TOKEN_PREFIX, ""))
//	            		 .getBody()
//	            		 .getSubject();
//	           Assert.notNull(user, "Invalid token");
//	            
	        } catch (final SignatureException e) {
	            throw new ServletException("Invalid token");
	        }
		
	 return appUserRepository.findAll();
	
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		return appUserRepository.findByEmail(username);
	}
	
}
