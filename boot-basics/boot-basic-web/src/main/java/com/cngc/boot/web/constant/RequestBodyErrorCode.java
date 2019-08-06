package com.cngc.boot.web.constant;

import com.cngc.boot.core.validation.FieldErrorCode;

/**
 * 请求体数据属性错误编码.
 * 请转而使用{@link FieldErrorCode}
 *
 * @author maxD
 * @deprecated 请转而使用FieldErrorCode.
 */
@Deprecated
public class RequestBodyErrorCode {

    public final static String MISSING_ATTRIBUTE = "missing_attribute";
    public final static String ALREADY_EXISTS = "already_exists";
    public final static String INVALID_FORMAT = "invalid_format";
    public final static String MISSING_RESOURCE = "missing_resource";
    public final static String CUSTOM = "custom";
}
