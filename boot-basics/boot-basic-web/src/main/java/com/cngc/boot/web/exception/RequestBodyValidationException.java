package com.cngc.boot.web.exception;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestBindingException;

import java.util.List;

/**
 * 请求体数据属性校验异常.
 *
 * @author maxD
 */
public class RequestBodyValidationException extends RuntimeException {

    private List<FieldError> errors;

    public RequestBodyValidationException(List<FieldError> errors) {
        this.errors = errors;
    }

    public List<FieldError> getFieldErrors() {
        return this.errors;
    }
}
