package com.cngc.boot.core.exception;

/**
 * cngc-boot框架下的通用非检查型异常.
 *
 * @author maxD
 */
public class CngcBootRuntimeException extends RuntimeException {
    public CngcBootRuntimeException(String msg) {
        super(msg);
    }
}
