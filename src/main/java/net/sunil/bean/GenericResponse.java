package net.sunil.bean;

import lombok.Data;

@Data
public class GenericResponse {

	private String status;
	
	private String errorMessage;
	
	private Object payLoad;
	
	public GenericResponse(String status, String errorMessage, Object payLoad) {
	
		this.status = status;
		this.errorMessage = errorMessage;
		this.payLoad = payLoad;
	}
	
	public GenericResponse(String status, String errorMessage) {
		
		this.status = status;
		this.errorMessage = errorMessage;
	}

	public GenericResponse(String status, Object payLoad) {
		
		this.status = status;
		this.payLoad = payLoad;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(Object payLoad) {
		this.payLoad = payLoad;
	}
	
}
