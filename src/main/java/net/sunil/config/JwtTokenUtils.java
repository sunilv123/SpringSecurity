package net.sunil.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.sunil.dto.AppConstants;
import net.sunil.modal.AppUser;

public class JwtTokenUtils{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1029281748694725202L;

	public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }
 
	public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

	    private Claims getAllClaimsFromToken(String token) {
	        return Jwts.parser()
	                .setSigningKey(AppConstants.SECRET)
	                .parseClaimsJws(token)
	                .getBody();
	    }

	    private Boolean isTokenExpired(String token) {
	        final Date expiration = getExpirationDateFromToken(token);
	        return expiration.before(new Date());
	    }

	    public String generateToken(AppUser user) {
	        return doGenerateToken(user.getEmail());
	    }

	    private String doGenerateToken(String subject) {

	        Claims claims = Jwts.claims().setSubject(subject);
	        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));

	        return Jwts.builder()
	                .setClaims(claims)
	               // .setIssuer("http://devglan.com")
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + AppConstants.EXPIRATION_TIME))
	                .signWith(SignatureAlgorithm.HS256, AppConstants.SECRET)
	                .compact();
	    }

	    public Boolean validateToken(String token, AppUser userDetails) {
	        final String username = getUsernameFromToken(token);
	        return (
	              username.equals(userDetails.getEmail())
	                    && !isTokenExpired(token));
	    }

	

}
