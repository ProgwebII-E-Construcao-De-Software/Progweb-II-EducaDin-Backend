package com.g2.Progweb_II_EducaDin_Backend.enums;

import br.ueg.progweb2.arquitetura.exceptions.MessageCode;
import lombok.Getter;

@Getter
public enum ErrorValidation implements MessageCode {


    GENERAL(500, "Undefined error!"),
    INVALID_ID(400, "Invalid Id!"),
    NOT_FOUND(404, "Not found!"),
    MANDATORY_FIELD_VIOLATION(400, "Mandatory field must be filled up!"),
    USER_ALREADY_EXISTS(405,"User already exists"),
    BUSINESS_LOGIC_VIOLATION(407,"Business logic violation!");

    private Integer code;
    private String message;

    ErrorValidation( Integer code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getStatus() {
        return this.code;
    }
}