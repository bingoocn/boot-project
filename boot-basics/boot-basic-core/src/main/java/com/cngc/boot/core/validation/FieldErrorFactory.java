package com.cngc.boot.core.validation;

import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;

/**
 * FieldError工厂.
 *
 * @author maxD
 */
public class FieldErrorFactory {

    /**
     * 生成FieldError.
     *
     * @param attribute      资源属性名
     * @param fieldErrorCode 错误编码枚举
     * @param message        错误消息
     * @return FieldError对象
     */
    public static FieldError createFieldError(String attribute, FieldErrorCode fieldErrorCode, String message) {
        return createFieldError(null, attribute, fieldErrorCode, message, null);
    }

    /**
     * 生成FieldError.
     *
     * @param resourceLocation 资源定位
     * @param attribute        资源属性名
     * @param fieldErrorCode   错误编码枚举
     * @param message          错误消息
     * @return FieldError对象
     */
    public static FieldError createFieldError(String resourceLocation, String attribute, FieldErrorCode fieldErrorCode, String message) {
        return createFieldError(resourceLocation, attribute, fieldErrorCode, message, null);
    }

    /**
     * 生成FieldError.
     *
     * @param attribute      资源属性名
     * @param fieldErrorCode 错误编码枚举
     * @param message        错误消息
     * @param rejectedValue  错误属性的值
     * @return FieldError对象
     */
    public static FieldError createFieldError(String attribute, FieldErrorCode fieldErrorCode, String message, Object rejectedValue) {
        return createFieldError(null, attribute, fieldErrorCode, message, rejectedValue);
    }

    /**
     * 生成FieldError.
     *
     * @param resourceLocation 资源定位
     * @param attribute        资源属性名
     * @param fieldErrorCode   错误编码枚举
     * @param message          错误消息
     * @param rejectedValue    错误属性的值
     * @return FieldError对象
     */
    public static FieldError createFieldError(String resourceLocation, String attribute, FieldErrorCode fieldErrorCode, String message, Object rejectedValue) {
        attribute = StringUtils.isEmpty(resourceLocation) ? attribute : ("[" + resourceLocation + "]." + attribute);
        return new FieldError("", attribute, rejectedValue, false,
                new String[]{fieldErrorCode.getCode()}, null, message);
    }
}
