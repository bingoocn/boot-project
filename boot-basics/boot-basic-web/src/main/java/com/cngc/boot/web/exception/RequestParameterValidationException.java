package com.cngc.boot.web.exception;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestBindingException;

import java.util.List;

/**
 * 请求参数校验异常.
 *
 * @author maxD
 */
public class RequestParameterValidationException extends ServletRequestBindingException {

    private List<FieldError> errors;

    public RequestParameterValidationException(List<FieldError> errors) {
        super(null);
        this.errors = errors;
    }

    public List<FieldError> getFieldErrors() {
        return this.errors;
    }
}
