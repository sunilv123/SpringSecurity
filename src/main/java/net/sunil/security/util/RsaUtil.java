package net.sunil.security.util;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RsaUtil {

	 private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCr+Ne5MpDSi9mAkQCpQ6Rv2G6lnxSOobPBCw/ntdOAhClwZipFr2KITc+k1C7R0ATkZtgPJGt+TUc5zPhvqfD5szrgK9JZscYOqqQ9x278RTusl9PMDVxN0/vOE+wz8u7/A3YdOaG/2j/NriYdE0ufzDUVeEkDN8AGOP86blLsjwIDAQAB";
	    private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKv417kykNKL2YCRAKlDpG/YbqWfFI6hs8ELD+e104CEKXBmKkWvYohNz6TULtHQBORm2A8ka35NRznM+G+p8PmzOuAr0lmxxg6qpD3HbvxFO6yX08wNXE3T+84T7DPy7v8Ddh05ob/aP82uJh0TS5/MNRV4SQM3wAY4/zpuUuyPAgMBAAECgYBsVBV32M13g5bgMPcsqKp1te2FMN9fNacozFIZkOUJSLdd0U2BlghIelN5rhtMGXBrfAlQCI4aAo4CcAZzOKbNauUtPR4LibxodXHhOYls0FFYBB/JGEhJu9ECEgnAGzhtX7RolEbDM1FFLvEu6Ewql7rNfLQa1uhCrr7RPFG1qQJBAOUosQEMVlOh36If3EhwWttqP9hihF6G9pHJsnYK/O7X48Yr3J1jsOy1HLo3zsOd33vtTqsyfG4M2A9qv4wdHCsCQQDAHWhLxiOqF748di0Zs6z3tFbt8IgLMkU7OJnjsV3He81omZGixritIlxeWH6VdZBRVpu2dnRD35UuZr88zWstAkBx5HN0jekpz74SGbul1RGTE49/wBcB1BogAxrLSFLFck8mYw5WuwFn4+vPMYV1+7TjJuJ+e60UFRYdM3TX8m/ZAkAprbeUyNWv7xq8bz71ln9t9dfuIwLAuxU99dedCDu6LWewy7BGyioClbPIXBaKQkke/FUnzqcGmpj4yh2vCTm5AkEAzyPOrN6s6Hraeve5g5/eXIC1lcVejcgYBG91KyT3iu7tiUo82GkttALns8fjhrAeUm7r/KdyhprfL9d4Mazvpg==";

	    public static PublicKey getPublicKey(String base64PublicKey) {
	        PublicKey publicKey = null;
	        try {
	            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
	            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	            publicKey = keyFactory.generatePublic(keySpec);
	            return publicKey;
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        } catch (InvalidKeySpecException e) {
	            e.printStackTrace();
	        }
	        return publicKey;
	    }

	    public static PrivateKey getPrivateKey(String base64PrivateKey) {
	        PrivateKey privateKey = null;
	        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
	        KeyFactory keyFactory = null;
	        try {
	            keyFactory = KeyFactory.getInstance("RSA");
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        try {
	            privateKey = keyFactory.generatePrivate(keySpec);
	        } catch (InvalidKeySpecException e) {
	            e.printStackTrace();
	        }
	        return privateKey;
	    }

	    public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
	        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
	        return cipher.doFinal(data.getBytes());
	    }

	    public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
	        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	        cipher.init(Cipher.DECRYPT_MODE, privateKey);
	        return new String(cipher.doFinal(data));
	    }

	    public static String decrypt(String data) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
	        return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(privateKey));
	    }
	    
	    
	    public static void main(String[] args) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
			
	    	String data = "Hello World";
	    	byte[] encryptedByteArray = encrypt(data, publicKey);
	    	System.out.println(new String(encryptedByteArray));
	    	
	    	System.out.println(decrypt(encryptedByteArray, getPrivateKey(privateKey)));
	    	
		}
	    
	
}
