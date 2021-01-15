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
     * @param fieldErrorEnum 错误编码枚举
     * @param message        错误消息
     * @return FieldError对象
     */
    public static FieldError createFieldError(String attribute, FieldErrorCode fieldErrorEnum, String message) {
        return createFieldError(null, attribute, fieldErrorEnum, message, null);
    }

    /**
     * 生成FieldError.
     *
     * @param resourceLocation 资源定位
     * @param attribute        资源属性名
     * @param fieldErrorEnum   错误编码枚举
     * @param message          错误消息
     * @return FieldError对象
     */
    public static FieldError createFieldError(String resourceLocation, String attribute, FieldErrorCode fieldErrorEnum, String message) {
        return createFieldError(resourceLocation, attribute, fieldErrorEnum, message, null);
    }

    /**
     * 生成FieldError.
     *
     * @param attribute      资源属性名
     * @param fieldErrorEnum 错误编码枚举
     * @param message        错误消息
     * @param rejectedValue  错误属性的值
     * @return FieldError对象
     */
    public static FieldError createFieldError(String attribute, FieldErrorCode fieldErrorEnum, String message, Object rejectedValue) {
        return createFieldError(null, attribute, fieldErrorEnum, message, rejectedValue);
    }

    /**
     * 生成FieldError.
     *
     * @param resourceLocation 资源定位
     * @param attribute        资源属性名
     * @param fieldErrorEnum   错误编码枚举
     * @param message          错误消息
     * @param rejectedValue    错误属性的值
     * @return FieldError对象
     */
    public static FieldError createFieldError(String resourceLocation, String attribute, FieldErrorCode fieldErrorEnum, String message, Object rejectedValue) {
        return createCustomFieldError(resourceLocation, attribute, fieldErrorEnum.getCode(), message, rejectedValue);
    }

    /**
     * 创建自定义类型的请求体属性错误.
     *
     * @param attribute      资源数据名
     * @param fieldErrorCode 错误编码
     * @param message        错误消息
     * @return FieldError对象
     */
    public static FieldError createCustomFieldError(String attribute, String fieldErrorCode, String message) {
        return createCustomFieldError(null, attribute, fieldErrorCode, message, null);
    }

    public static FieldError createCustomFieldError(String resourceLocation, String attribute, String fieldErrorCode, String message) {
        return createCustomFieldError(resourceLocation, attribute, fieldErrorCode, message, null);
    }

    public static FieldError createCustomFieldError(String attribute, String fieldErrorCode, String message, Object rejectedValue) {
        return createCustomFieldError(null, attribute, fieldErrorCode, message, rejectedValue);
    }

    public static FieldError createCustomFieldError(String resourceLocation, String attribute,
                                                    String fieldErrorCode, String message, Object rejectedValue) {
        attribute = StringUtils.isEmpty(resourceLocation) ? attribute : ("[" + resourceLocation + "]." + attribute);
        return new FieldError("", attribute, rejectedValue, false,
                new String[]{fieldErrorCode}, null, message);
    }
}
