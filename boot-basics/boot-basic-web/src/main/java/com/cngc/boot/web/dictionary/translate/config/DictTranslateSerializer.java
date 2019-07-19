package com.cngc.boot.web.dictionary.translate.config;

import com.cngc.boot.web.dictionary.model.Dictionary;
import com.cngc.boot.web.dictionary.service.DictTranslateService;
import com.cngc.boot.web.dictionary.translate.DictTranslator;
import com.cngc.boot.web.dictionary.util.DictTranslatorServiceProxy;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DictTranslateSerializer extends JsonSerializer<String> implements ContextualSerializer {

    /**
     * 字典类型.
     */
    private String type;

    DictTranslateSerializer() {
        super();
    }
    DictTranslateSerializer(String type) {
        this.type = type;
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        Dictionary dictionary = new Dictionary();
        dictionary.setCode(value);
        dictionary.setName(DictTranslatorServiceProxy.getInstance().translateCodeToDisplayName(this.type, value));
        gen.writeObject(dictionary);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        DictTranslator dictTranslator = property.getAnnotation(DictTranslator.class);
        String type = dictTranslator.type() + dictTranslator.value();
        if(StringUtils.isEmpty(type)) {
            throw new JsonMappingException("未设置字典类型!");
        }
        return new DictTranslateSerializer(type);
    }
}
