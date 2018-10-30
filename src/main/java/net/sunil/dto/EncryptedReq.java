package net.sunil.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sunil.security.util.RsaUtil;

public class EncryptedReq<T> {

	private String securityKey;

	private String securityIv;

	private String payLoad;

	public T getData(Class<T> c) {
		try {

			ObjectMapper mapper = new ObjectMapper();
			String decryptedData = RsaUtil.decrypt(payLoad);

			return mapper.readValue(decryptedData, c);

		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException("Could not decrypt data");
		}
	}

	public String getSecurityKey() throws Exception{
		return  RsaUtil.decrypt(securityKey);
	}

	public String getPayLoad() {
		return payLoad;
	}


	public String getSecurityIv() throws Exception{
		return RsaUtil.decrypt(securityIv);
	}


}
