package net.sunil.security.util;

import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesUtil {
	 /* public static String encrypt(String key, String initVector, String value) {
	        try {
	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));

	            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
	            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

	            byte[] encrypted = cipher.doFinal(value.getBytes());
	            String s = new String(Base64.getEncoder().encode(encrypted));
	            return s;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return null;
	    }

	    public static String decrypt(String key, String initVector, String encrypted) {
	        try {
	            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

	            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

	            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

	            return new String(original);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }

	        return null;
	    }
	    public static String decrypt() throws Exception {
	    	
	    	String secret = "René Über";
	    	String cipherText = "U2FsdGVkX1/M/O9/zo2I75+FTNIGZBCdh2f6rs8YqV682Yp/AMEehh7KmM0fbn+gm5mvY6DQkwYSx5G/jBotR2Bh30fXdBox3yBu7vjqyvc=";
	    	
	    	byte[] cipherData = Base64.getDecoder().decode(cipherText);
	    	byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);
	    	
	    	MessageDigest md5 = MessageDigest.getInstance("MD5");
	    	final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData, secret.getBytes(StandardCharsets.UTF_8), md5);
	    	SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
	    	IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);
	    	
	    	byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
	    	Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    	aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
	    	byte[] decryptedData = aesCBC.doFinal(encrypted);
	    	String decryptedText = new String(decryptedData, StandardCharsets.UTF_8);
	    	return decryptedText;
	    	
	    }
	    */

	   /* public static void main(String[] args) throws Exception {
	  
	    	String plainText = "Hello, World! This is a Java/Javascript AES test.";
	    	String encryptedText = encryptAngular(plainText);
	    	System.out.println("encryptedText ===  :: "+encryptedText);
	    	String decryptedText = decryptAngular(encryptedText);
	    	System.out.println("decryptedText === :: "+decryptedText);
	    	
	    	//System.out.println("Final decryt == "+decrypt());
	    }*/
	    
	    
	    public static String encryptAngular(String plainText) throws Exception {
	    	//String plainText = "Hello, World! This is a Java/Javascript AES test.";
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
	    
	    public static String decryptAngular(String encryptedText) throws Exception {
	    	
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
	    
	    public static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password, MessageDigest md) {

	        int digestLength = md.getDigestLength();
	        int requiredLength = (keyLength + ivLength + digestLength - 1) / digestLength * digestLength;
	        byte[] generatedData = new byte[requiredLength];
	        int generatedLength = 0;

	        try {
	            md.reset();

	            // Repeat process until sufficient data has been generated
	            while (generatedLength < keyLength + ivLength) {

	                // Digest data (last digest if available, password data, salt if available)
	                if (generatedLength > 0)
	                    md.update(generatedData, generatedLength - digestLength, digestLength);
	                md.update(password);
	                if (salt != null)
	                    md.update(salt, 0, 8);
	                md.digest(generatedData, generatedLength, digestLength);

	                // additional rounds
	                for (int i = 1; i < iterations; i++) {
	                    md.update(generatedData, generatedLength, digestLength);
	                    md.digest(generatedData, generatedLength, digestLength);
	                }

	                generatedLength += digestLength;
	            }

	            // Copy key and IV into separate byte arrays
	            byte[][] result = new byte[2][];
	            result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
	            if (ivLength > 0)
	                result[1] = Arrays.copyOfRange(generatedData, keyLength, keyLength + ivLength);

	            return result;

	        } catch (DigestException e) {
	            throw new RuntimeException(e);

	        } finally {
	            // Clean out temporary data
	            Arrays.fill(generatedData, (byte)0);
	        }
	    }
}
