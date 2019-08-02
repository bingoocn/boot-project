package com.cngc.boot.security;

import com.cngc.boot.core.CngcResourceBundleMessageSource;
import com.cngc.boot.security.constant.SecurityMessageConstants;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * cngc-security异常处理类.
 *
 * @author maxD
 */
@ControllerAdvice
@Order(1)
public class SecurityExceptionHandler {

    @Autowired
    private CngcResourceBundleMessageSource cngcSecurityMessageSource;

    /**
     * 处理接口拒绝访问异常.
     * @param e 异常对象
     * @return 响应体
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> accessDeniedExceptionHandler(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ErrorMessage(StringUtils.isEmpty(e.getMessage()) ?
                        cngcSecurityMessageSource.getMessage(SecurityMessageConstants.ERROR_ACCESS_DENIED) : e.getMessage()));
    }

    @Data
    private class ErrorMessage {
        private String message;

        private ErrorMessage(String message) {
            this.message = message;
        }
    }
}
