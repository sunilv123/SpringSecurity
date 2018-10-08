package net.sunil.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import javassist.bytecode.stackmap.BasicBlock.Catch;
import net.sunil.dto.AppConstants;
import net.sunil.dto.GenericResponse;
import net.sunil.modal.AppUser;
import net.sunil.service.LoginService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private LoginService loginService;

	private JwtTokenUtils jwtTokenUtil;
	
	
	JwtAuthenticationFilter(LoginService loginService, JwtTokenUtils jwtTokenUtil){
		this.jwtTokenUtil = jwtTokenUtil;
		this.loginService = loginService;
	}
	private static Logger logger = Logger.getLogger(JwtAuthenticationFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filter)
			throws ServletException, IOException {
	try {	
        String header = req.getHeader("X-Authorization");
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith(AppConstants.TOKEN_PREFIX)) {
            authToken = header.replace(AppConstants.TOKEN_PREFIX,"");
                username = jwtTokenUtil.getUsernameFromToken(authToken);
                if (username != null) {
                	
                	AppUser userDetails = loginService.loadUserByUsername(username);
                	
                	if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(
                				new SimpleGrantedAuthority("ROLE_ADMIN")));
                		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                		logger.info("authenticated user " + username + ", setting security context");
                		SecurityContextHolder.getContext().setAuthentication(authentication);
                		filter.doFilter(req, res);
                	}
                }
       
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }
       
	}catch(Exception e){
		e.printStackTrace();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        GenericResponse resp = new GenericResponse(AppConstants.GENERIC_RESPONSE_FAILURE, e.getMessage(),"403");
        String jsonRespString = ow.writeValueAsString(resp);
        res.setContentType("application/json");
        PrintWriter writer = res.getWriter();
        writer.write(jsonRespString);
      
        System.out.println("===============================");
	}
    }
		
	}



