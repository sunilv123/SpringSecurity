package net.sunil.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sunil.security.util.AesUtil;


public class EncryptedReq<T> {


    private String securityKey;
  
    
    private String payLoad;

    public T getData(Class<T> c) {
        try {
           // this.securityKey = AesUtil.decryptAngular(securityKey);

            ObjectMapper mapper = new ObjectMapper();
            String decryptedData = AesUtil.decryptAngular(payLoad);

            return mapper.readValue(decryptedData, c);

        } catch (Exception e) {
            throw new SecurityException("Could not decrypt data");
        }
    }

	public String getSecurityKey() {
		return securityKey;
	}

	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}

	public String getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(String payLoad) {
		this.payLoad = payLoad;
	}

}
