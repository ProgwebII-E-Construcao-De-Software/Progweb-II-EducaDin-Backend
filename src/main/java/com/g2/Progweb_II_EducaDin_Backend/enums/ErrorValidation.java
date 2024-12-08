package com.g2.Progweb_II_EducaDin_Backend.enums;

import br.ueg.progweb2.arquitetura.exceptions.MessageCode;
import lombok.Getter;

public enum ErrorValidation implements MessageCode {

    GENERAL( "MSG-100", 500),
    INVALID_ID("MSG-101",400),
    NOT_FOUND("MSG-102", 404),
    MANDATORY_FIELD_VIOLATION("ME004", 400),
    USER_ALREADY_EXISTS("MSG-104", 405),
    BUSINESS_LOGIC_VIOLATION("MSG-105", 407);

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