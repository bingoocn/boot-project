package com.cngc.boot.core;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.lang.Nullable;

/**
 * 通过此类获取消息资源,继承{@link ResourceBundleMessageSource},增加了不传递Locale获取message的方法.
 *
 * @author maxD
 */
public class CngcResourceBundleMessageSource extends ResourceBundleMessageSource {

    public String getMessage(String code) {
        return super.getMessage(code, null, null);
    }

    public String getMessage(String code, @Nullable Object[] args) {
        return super.getMessage(code, args, null);
    }
}
