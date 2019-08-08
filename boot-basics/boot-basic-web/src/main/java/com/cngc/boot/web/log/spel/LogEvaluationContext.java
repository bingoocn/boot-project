package com.cngc.boot.web.log.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.PropertyAccessor;
import org.springframework.expression.TypedValue;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * {@link com.cngc.boot.web.log.RequestLog}中使用SpEL表达式,表达式使用此context作为上下文.
 * rootObject接收map类型对象,根据key进行取值.
 *
 * @author maxD
 */
public class LogEvaluationContext extends StandardEvaluationContext {

    public LogEvaluationContext(Object rootObject) {
        super(rootObject);
    }

    @Override
    public List<PropertyAccessor> getPropertyAccessors() {
        List<PropertyAccessor> accessors = new ArrayList<>();
        accessors.add(new PropertyAccessor() {

            @Override
            public Class<?>[] getSpecificTargetClasses() {
                return null;
            }

            @Override
            public boolean canRead(EvaluationContext context, Object target, String name) {
                return target instanceof Map && ((Map) target).containsKey(name);
            }

            @Override
            public TypedValue read(EvaluationContext context, Object target, String name) {
                return new TypedValue(((Map) target).get(name));
            }

            @Override
            public boolean canWrite(EvaluationContext context, Object target, String name) {
                return false;
            }

            @Override
            public void write(EvaluationContext context, Object target, String name, Object newValue) {

            }
        });
        accessors.add(new ReflectivePropertyAccessor());
        return accessors;
    }
}