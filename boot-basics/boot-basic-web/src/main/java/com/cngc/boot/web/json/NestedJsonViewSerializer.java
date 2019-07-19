package com.cngc.boot.web.json;


import com.cngc.boot.core.util.SpringContextUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;

/**
 * 实现嵌套成员变量使用与其所在对象不同的JsonView功能.
 *
 * @author maxD
 */
public class NestedJsonViewSerializer extends JsonSerializer<Object>  implements ContextualSerializer {

    /**
     * 嵌套对象使用的JsonViewClass.
     */
    private Class<?> nestedJsonViewClass;

    public NestedJsonViewSerializer() {
        super();
    }

    private NestedJsonViewSerializer(Class<?> nestedJsonViewClass) {
        super();
        this.nestedJsonViewClass = nestedJsonViewClass;
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        SpringContextUtil.getBean(ObjectMapper.class).writerWithView(nestedJsonViewClass).writeValue(gen, value);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) {
        NestedJsonView nestedJsonView = property.getAnnotation(NestedJsonView.class);
        Class<?> nestedJsonViewClass = nestedJsonView.value();
        return new NestedJsonViewSerializer(nestedJsonViewClass);
    }
}
