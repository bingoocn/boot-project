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
     * @param attribute 属性名
     * @param code      错误编码
     * @param message   错误消息
     * @return FieldError对象
     */
    public static FieldError createFieldError(String attribute, String code, String message) {
        return new FieldError("", attribute, null, false,
                new String[]{code}, null, message);
    }

    /**
     * 生成FieldError.
     *
     * @param resourceId 资源id
     * @param attribute  属性名
     * @param code       错误编码
     * @param message    错误消息
     * @return FieldError对象
     */
    public static FieldError createFieldError(String resourceId, String attribute, String code, String message) {
        attribute = StringUtils.isEmpty(resourceId) ? attribute : ("[" + resourceId + "]." + attribute);
        return new FieldError("", attribute, null, false,
                new String[]{code}, null, message);
    }
}
