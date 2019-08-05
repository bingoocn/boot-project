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
     * @param attribute 资源属性名
     * @param code      错误编码
     * @param message   错误消息
     * @return FieldError对象
     */
    public static FieldError createFieldError(String attribute, String code, String message) {
        return createFieldError(null, attribute, code, message, null);
    }

    /**
     * 生成FieldError.
     *
     * @param resourceLocation 资源定位
     * @param attribute        资源属性名
     * @param code             错误编码
     * @param message          错误消息
     * @return FieldError对象
     */
    public static FieldError createFieldError(String resourceLocation, String attribute, String code, String message) {
        return createFieldError(resourceLocation, attribute, code, message, null);
    }

    /**
     * 生成FieldError.
     *
     * @param attribute     资源属性名
     * @param code          错误编码
     * @param message       错误消息
     * @param rejectedValue 错误属性的值
     * @return FieldError对象
     */
    public static FieldError createFieldError(String attribute, String code, String message, Object rejectedValue) {
        return createFieldError(null, attribute, code, message, rejectedValue);
    }

    /**
     * 生成FieldError.
     *
     * @param resourceLocation 资源定位
     * @param attribute        资源属性名
     * @param code             错误编码
     * @param message          错误消息
     * @param rejectedValue    错误属性的值
     * @return FieldError对象
     */
    public static FieldError createFieldError(String resourceLocation, String attribute, String code, String message, Object rejectedValue) {
        attribute = StringUtils.isEmpty(resourceLocation) ? attribute : ("[" + resourceLocation + "]." + attribute);
        return new FieldError("", attribute, rejectedValue, false,
                new String[]{code}, null, message);
    }
}
