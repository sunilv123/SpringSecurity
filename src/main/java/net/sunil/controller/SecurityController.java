package net.sunil.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sunil.dto.AppConstants;
import net.sunil.dto.EncryptedReq;
import net.sunil.dto.GenericResponse;
import net.sunil.dto.LoginBean;
import net.sunil.security.util.AesUtil;
import net.sunil.security.util.RsaUtil;

@RestController
@RequestMapping("/api/security")
@CrossOrigin
public class SecurityController {

	@GetMapping(value = "/handshake")
	public GenericResponse handShake() throws Exception {
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_SUCCESS, RsaUtil.publicKey);
	}

	@PostMapping(value = "/login", consumes = "application/json")
	public GenericResponse loginWithRsa(@RequestBody EncryptedReq<LoginBean> req) throws Exception {
		LoginBean bean = req.getData(LoginBean.class);
		System.out.println(bean.getUserName() + " --- " + bean.getPassword());
		System.out.println("SecurityKey :: " + req.getSecurityKey() + " SecurityIv :: " + req.getSecurityIv());
		return new GenericResponse(AppConstants.GENERIC_RESPONSE_SUCCESS,
				AesUtil.encrypt(new ObjectMapper().writeValueAsString(bean)));
	}

}
