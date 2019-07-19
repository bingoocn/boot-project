package com.cngc.boot.web.json;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于类中对象类型的成员变量上.使成员变量可使用与其所在对象不同的JsonView.
 *
 * @author maxD
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@JacksonAnnotationsInside
@JsonSerialize(using = NestedJsonViewSerializer.class)
public @interface NestedJsonView {

    Class<?> value() default Void.class;
}
