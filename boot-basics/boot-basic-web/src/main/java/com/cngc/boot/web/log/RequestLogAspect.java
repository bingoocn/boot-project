package com.cngc.boot.web.log;

import com.cngc.boot.web.log.spel.LogEvaluationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 处理请求日志的切面.
 * TODO 无法在controller方法参数校验前进行调用.
 *
 * @author maxD
 */
@Component
@Aspect
public class RequestLogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private RequestLogService requestLogService;

    /**
     * 记录请求日志.
     *
     * @param pjp        切点
     * @param requestLog 请求日志注解
     * @return controller方法执行结果
     * @throws Throwable 异常
     */
    @Around("@annotation(requestLog)")
    public Object doRequestLog(ProceedingJoinPoint pjp, RequestLog requestLog) throws Throwable {
        // 使用代理后的注解,别名生效.
        requestLog = AnnotationUtils.synthesizeAnnotation(requestLog, null);
        // 寻找使用RequestLogParam注解的参数,将其作为rootObject设置为spel的上下文中.
        Map<String, Object> params = new HashMap<>(5);
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Parameter[] parameters = signature.getMethod().getParameters();
        for (int i = 0; i < parameters.length; i++) {
            int finalI = i;
            Optional.ofNullable(parameters[finalI].getAnnotation(RequestLogParam.class)).ifPresent(
                    requestLogParam -> params.put(requestLogParam.value(), pjp.getArgs()[finalI])
            );
        }

        String message = new SpelExpressionParser().parseExpression(requestLog.message(),
                new TemplateParserContext()).getValue(new LogEvaluationContext(params), String.class);

        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();

        // 处理日志内容.
        RequestLogInfo logInfo = requestLogService.extractRequestLog(message, request);

        Object retVal;
        try {
            retVal = pjp.proceed();
            logInfo.setState(RequestLogInfo.LogRequestState.SUCCESS);
        } catch (Exception e) {
            logInfo.setState(RequestLogInfo.LogRequestState.FAIL);
            throw e;
        } finally {
            kafkaTemplate.send("request-log", new ObjectMapper().writeValueAsString(logInfo));
        }
        return retVal;
    }
}
