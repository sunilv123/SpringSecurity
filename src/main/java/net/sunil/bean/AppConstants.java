package net.sunil.bean;

public class AppConstants {

	public static final long EXPIRATION_TIME = 365 * 24 * 60 * 60 * 1000;

	public static final String GENERIC_RESPONSE_SUCCESS = "SUCCESS";
	
	public static final String GENERIC_RESPONSE_FAILURE = "FAILURE";
	
    public static final String SECRET = "SecretKeyToGenJWTs";
    
    public static final String TOKEN_PREFIX = "Bearer ";
    
    public static final String HEADER_STRING = "Authorization";
	
    
    public static void main(String[] args) {
		
    	System.out.println(rev("Sunil"));
    	
	}
    
    
    public static String rev(String str) {
    	
    	if(str.length() == 0) {
    		return "";
    	}else {
    		
    		return rev(str.substring(1, str.length()))+""+str.charAt(0);
    		
    	}
    	
    }
    
    
}
