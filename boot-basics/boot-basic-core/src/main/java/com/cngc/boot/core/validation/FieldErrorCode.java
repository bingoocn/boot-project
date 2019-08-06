package com.cngc.boot.core.validation;

/**
 * 属性错误编码枚举.
 *
 * @author maxD
 */
public enum FieldErrorCode {
    /**
     * 属性错误编码枚举.
     */
    MISSING_ATTRIBUTE("missing_attribute", "必填校验错误"),
    ALREADY_EXISTS("already_exists", "唯一性校验错误"),
    INVALID_FORMAT("invalid_format", "不符合规定的格式要求"),
    MISSING_RESOURCE("missing_resource", "属性值需要关联某一资源"),
    CUSTOM("custom", "自定义类型属性值错误");

    private String code;

    FieldErrorCode(String code, String reasonPhrase) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
