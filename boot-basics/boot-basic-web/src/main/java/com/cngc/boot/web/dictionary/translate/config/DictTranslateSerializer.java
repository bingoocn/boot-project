package com.cngc.boot.web.dictionary.translate.config;

import com.cngc.boot.web.dictionary.model.Dictionary;
import com.cngc.boot.web.dictionary.translate.DictTranslator;
import com.cngc.boot.web.dictionary.util.DictTranslatorServiceProxy;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class DictTranslateSerializer extends JsonSerializer<String> implements ContextualSerializer {

    /**
     * 字典类型.
     */
    private String type;

    /**
     * 字典转换注解对象.
     */
    private DictTranslator dictTranslator;

    DictTranslateSerializer() {
        super();
    }

    DictTranslateSerializer(String type, DictTranslator dictTranslator) {
        this.type = type;
        this.dictTranslator = dictTranslator;
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        Dictionary dictionary = new Dictionary();
        dictionary.setCode(value);
        dictionary.setName(DictTranslatorServiceProxy.getInstance().translateCodeToDisplayName(this.type, value, dictTranslator));
        gen.writeObject(dictionary);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        DictTranslator dictTranslator = AnnotationUtils.synthesizeAnnotation(property.getAnnotation(DictTranslator.class), null);
        String type = dictTranslator.type();
        if (StringUtils.isEmpty(type)) {
            throw new JsonMappingException("未设置字典类型!");
        }
        return new DictTranslateSerializer(type, dictTranslator);
    }
}
