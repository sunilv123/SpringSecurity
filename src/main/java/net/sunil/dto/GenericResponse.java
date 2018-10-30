package net.sunil.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class GenericResponse {

    private String status;

    private String message;

    private Object payLoad;

    public GenericResponse(String status, String message, Object payLoad) {
        this.status = status;
        this.message = message;
        this.payLoad = payLoad;
    }

    public GenericResponse(String status, Object payLoad) {

        this.status = status;
        this.payLoad = payLoad;
    }

	
}
