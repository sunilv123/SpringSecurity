package net.sunil.security.util;

import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {
	    
	    public static String encrypt(String plainText) throws Exception {
	    	String key = "u/Gu5posvwDsXUnV5Zaq4g==";
	    	String iv = "5D9r9ZVzEYYgha93/aUK2w==";
	    	SecretKey secretKey = new SecretKeySpec(
	    			Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8)), "AES");
	    	
	    	AlgorithmParameterSpec algorithIV = new IvParameterSpec(
	    			Base64.getDecoder().decode(iv.getBytes(StandardCharsets.UTF_8))); 
	    	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    	cipher.init(Cipher.ENCRYPT_MODE, secretKey, algorithIV);
	    	
	    	String encryptedText = new String(Base64.getEncoder().encode(cipher.doFinal(
	        	    plainText.getBytes("UTF-8"))));
	    	
	    	return encryptedText;
	  }
	    
	    public static String decrypt(String encryptedText) throws Exception {
	    	
	    	String key = "u/Gu5posvwDsXUnV5Zaq4g==";
	    	String iv = "5D9r9ZVzEYYgha93/aUK2w==";
	    	SecretKey secretKey = new SecretKeySpec(
	    			Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8)), "AES");
	    	
	    	AlgorithmParameterSpec algorithIV = new IvParameterSpec(
	    			Base64.getDecoder().decode(iv.getBytes(StandardCharsets.UTF_8))); 
	    	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    	cipher.init(Cipher.DECRYPT_MODE, secretKey, algorithIV);
	    	byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
	    	String plainText = new String(original);
	    	
	    	return plainText;
	  }
	    
	    public static void main(String[] args) throws Exception {
	    	String s = "Hello world!";
	    	String en = encrypt(s);
	    	System.out.println("en == "+en);
	    	System.out.println(decrypt(en));
		}
	   
}
