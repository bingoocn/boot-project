package com.cngc.boot.web.exception;

/**
 * 操作资源不存在时,抛出的异常.
 *
 * @author maxD
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}
