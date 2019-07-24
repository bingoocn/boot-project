package com.cngc.boot.web.dictionary.translate.config;

import com.cngc.boot.web.dictionary.translate.DictTranslator;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字典转换功能相关配置类.
 *
 * @author maxD
 */
@Configuration
public class DictTranslateConfig {
    private static final Logger logger = LoggerFactory.getLogger(DictTranslateConfig.class);

    private final static String DEFAULT_PROPERTY_STRATEGY_NAME = "propertyNamingStrategy";

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> {
            // 默认配置的propertyNamingStrategy.
            PropertyNamingStrategy propertyNamingStrategy = null;
            try {
                Field field = jacksonObjectMapperBuilder.getClass().getDeclaredField(DEFAULT_PROPERTY_STRATEGY_NAME);
                field.setAccessible(true);
                propertyNamingStrategy = (PropertyNamingStrategy) field.get(jacksonObjectMapperBuilder);
            } catch (Exception e) {
                logger.warn("获取默认propertyNamingStrategy失败!", e);
            }
            jacksonObjectMapperBuilder.propertyNamingStrategy(new DictPropertyNamingStrategy(propertyNamingStrategy));
        };
    }

    /**
     * 处理字典转换时,对属性名的处理.
     *
     * @author maxD
     */
    public class DictPropertyNamingStrategy extends PropertyNamingStrategy {

        private final static String KEY_SUFFIX = "Code";

        private final Pattern fieldNamePattern = Pattern.compile(KEY_SUFFIX + "$", Pattern.CASE_INSENSITIVE);

        Logger logger = LoggerFactory.getLogger(DictPropertyNamingStrategy.class);

        /**
         * 默认属性名处理策略.
         */
        private PropertyNamingStrategy defaultPropertyNamingStrategy;

        private DictPropertyNamingStrategy(PropertyNamingStrategy propertyNamingStrategy) {
            this.defaultPropertyNamingStrategy = propertyNamingStrategy;
        }

        @Override
        public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
            AtomicBoolean forceSetting = new AtomicBoolean(false);
            String fieldName = Optional.ofNullable(method.getAllAnnotations().get(DictTranslator.class))
                    .map(dict -> {
                                if (StringUtils.isEmpty(dict.serializeKeyName())) {
                                    try {
                                        PropertyDescriptor[] propertyDescriptors
                                                = Introspector.getBeanInfo(method.getDeclaringClass()).getPropertyDescriptors();
                                        for (PropertyDescriptor pd : propertyDescriptors) {
                                            if (method.getMember().equals(pd.getReadMethod())) {
                                                String name = pd.getName();
                                                Matcher matcher = fieldNamePattern.matcher(name);
                                                if (matcher.find()) {
                                                    return name.substring(0, matcher.start());
                                                }
                                                return null;
                                            }
                                        }
                                    } catch (Exception e) {
                                        logger.warn(e.getMessage(), e);
                                    }
                                    return null;
                                } else {
                                    forceSetting.set(true);
                                    return dict.serializeKeyName();
                                }
                            }
                    )
                    .orElse(defaultName);
            if(forceSetting.get()) {
                return fieldName;
            }
            return Optional.ofNullable(defaultPropertyNamingStrategy).map(strategy
                    -> strategy.nameForGetterMethod(config, method, fieldName)).orElse(fieldName);
        }

        @Override
        public String nameForField(MapperConfig<?> config, AnnotatedField field,
                                   String defaultName) {
            return Optional.ofNullable(defaultPropertyNamingStrategy).map(strategy
                    -> strategy.nameForField(config, field, defaultName)).orElse(defaultName);
        }

        @Override
        public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method,
                                          String defaultName) {
            AtomicBoolean forceSetting = new AtomicBoolean(false);
            String fieldName = Optional.ofNullable(method.getAllAnnotations().get(DictTranslator.class))
                    .map(dict -> {
                                if (StringUtils.isEmpty(dict.deSerializeKeyName())) {
                                    try {
                                        PropertyDescriptor[] propertyDescriptors
                                                = Introspector.getBeanInfo(method.getDeclaringClass()).getPropertyDescriptors();
                                        for (PropertyDescriptor pd : propertyDescriptors) {
                                            if (method.getMember().equals(pd.getWriteMethod())) {
                                                String name = pd.getName();
                                                Matcher matcher = fieldNamePattern.matcher(name);
                                                if (matcher.find()) {
                                                    return name;
                                                }
                                                return name + KEY_SUFFIX;
                                            }
                                        }
                                    } catch (Exception e) {
                                        logger.warn(e.getMessage(), e);
                                    }
                                    return null;
                                } else {
                                    forceSetting.set(true);
                                    return dict.deSerializeKeyName();
                                }
                            }
                    )
                    .orElse(defaultName);
            if(forceSetting.get()) {
                return fieldName;
            }
            return Optional.ofNullable(defaultPropertyNamingStrategy).map(strategy
                    -> strategy.nameForSetterMethod(config, method, fieldName)).orElse(fieldName);
        }

        @Override
        public String nameForConstructorParameter(MapperConfig<?> config, AnnotatedParameter ctorParam,
                                                  String defaultName) {
            return Optional.ofNullable(defaultPropertyNamingStrategy).map(strategy
                    -> strategy.nameForConstructorParameter(config, ctorParam, defaultName)).orElse(defaultName);
        }
    }
}
