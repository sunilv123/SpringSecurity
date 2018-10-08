package net.sunil.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import net.sunil.service.LoginService;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private LoginService loginService;
	
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	 /* 
	  @Bean
	    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
	        return new JwtAuthenticationFilter();
	    }*/
	
	     
	    @Bean
	    public JwtTokenUtils jwtTokenUtils() throws Exception {
	        return new JwtTokenUtils();
	    }
	    
	
	@Override
	public void configure(final WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/api/login", "/api/signup", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**", 
				  "/configuration/**", "/swagger-ui.html", "/webjars/**", "/api/security/**");
	}
	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/api/**").fullyAuthenticated().antMatchers("/**").permitAll().and().
		exceptionHandling().and().anonymous().and()
		// Disable Cross site references
		.csrf().disable()
		
		.authorizeRequests().anyRequest().authenticated().and()
		.addFilterBefore(new JwtAuthenticationFilter(loginService, jwtTokenUtils()), BasicAuthenticationFilter.class);
	                
	    }
	
	@Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
	
}
