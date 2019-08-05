package com.cngc.boot.web;

import com.cngc.boot.core.CngcResourceBundleMessageSource;
import com.cngc.boot.web.constant.WebMessageConstants;
import com.cngc.boot.web.exception.RequestBodyValidationException;
import com.cngc.boot.web.exception.ResourceNotFoundException;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理内置异常.
 *
 * @author maxD
 */
@ControllerAdvice
@Order
public class ServletExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(ServletExceptionHandler.class);

    @Autowired
    private CngcResourceBundleMessageSource cngcWebMessageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handle(new RequestBodyValidationException(ex.getBindingResult().getFieldErrors()));
    }

    /**
     * 处理操作资源不存在异常.
     *
     * @param e 异常对象
     * @return 响应体
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> notFoundHandle(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorMessage(StringUtils.isEmpty(e.getMessage()) ?
                        cngcWebMessageSource.getMessage(WebMessageConstants.ERROR_RESOURCE_NOT_FOUND) : e.getMessage()));
    }

    /**
     * 处理请求参数校验异常.
     *
     * @param exception 校验异常对象
     * @return 响应体
     * TODO 处理error中的code与attribution
     */
    @ExceptionHandler(RequestBodyValidationException.class)
    public ResponseEntity<Object> handle(RequestBodyValidationException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
                .body(new RequestParameterErrorMessage(cngcWebMessageSource.getMessage(
                        WebMessageConstants.ERROR_DEFAULT_PARAMETER_VALIDATION), exception.getFieldErrors()));
    }

    /**
     * 未进行指定处理的,其他类型的异常,皆返回500内部错误,响应体为{@link ErrorMessage}对象.
     *
     * @return 响应体
     */
    @ExceptionHandler
    public ResponseEntity<Object> serverErrorHandle(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorMessage(cngcWebMessageSource.getMessage(WebMessageConstants.ERROR_INTERNAL_SERVER)));
    }

    /**
     * 出现请求参数异常时,返回的消息体对象.
     */
    @Data
    private class RequestParameterErrorMessage {
        private RequestParameterErrorMessage(String message, @NotNull List<FieldError> errors) {
            this.message = message;
            for (FieldError error : errors) {
                this.errors.add(new Error(error));
            }
        }

        private String message;
        private List<Error> errors = new ArrayList<>();

        @Data
        private class Error {
            private Error(FieldError error) {
                this.code = error.getCode();
                this.attribute = error.getField();
                this.message = error.getDefaultMessage();
                this.rejectedValue = error.getRejectedValue();
            }

            private String code;
            private String attribute;
            private String message;
            private Object rejectedValue;
        }
    }

    @Data
    private class ErrorMessage {
        private String message;

        private ErrorMessage(String message) {
            this.message = message;
        }
    }
}
