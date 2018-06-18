package net.sunil.config;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import net.sunil.bean.AppConstants;
import net.sunil.modal.AppUser;
import net.sunil.service.LoginService;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	 @Autowired
	 private LoginService loginService;

	    @Autowired
	    private JwtTokenUtils jwtTokenUtil;
	    
	    private static Logger logger = Logger.getLogger(JwtAuthenticationFilter.class);
	    
	  
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filter)
			throws ServletException, IOException {
        String header = req.getHeader("X-Authorization");
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith(AppConstants.TOKEN_PREFIX)) {
            authToken = header.replace(AppConstants.TOKEN_PREFIX,"");
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch(SignatureException e){
                logger.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            AppUser userDetails = loginService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(
                		new SimpleGrantedAuthority("ROLE_ADMIN")));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filter.doFilter(req, res);
    }
		
	}



