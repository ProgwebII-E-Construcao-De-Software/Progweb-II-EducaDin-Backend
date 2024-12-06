package com.g2.Progweb_II_EducaDin_Backend.enums;

import br.ueg.progweb2.arquitetura.exceptions.MessageCode;
import lombok.Getter;

public enum ErrorValidation implements MessageCode {


    GENERAL( "Undefined error!", 500),
    INVALID_ID("Invalid Id!",400),
    NOT_FOUND("Not found!", 404),
    MANDATORY_FIELD_VIOLATION("Mandatory field must be filled up!", 400),
    USER_ALREADY_EXISTS("User already exists", 405),
    BUSINESS_LOGIC_VIOLATION("Business logic violation!", 407);

    private final String code;

    private final Integer status;

    /**
     * Construtor da classe.
     *
     * @param code -
     * @param status -
     */
    ErrorValidation(final String code, final Integer status) {
        this.code = code;
        this.status = status;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @see Enum#toString()
     */
    @Override
    public String toString() {
        return code;
    }
}