package com.cngc.boot.web.log;

import com.cngc.boot.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 请求日志相关服务接口.
 *
 * @author max
 */
public interface RequestLogService {

    /**
     * 提取请求日志信息.
     *
     * @param message 请求描述
     * @param request 请求对象
     * @param paramMap 请求参数Map
     * @return 日志信息
     */
    default RequestLogInfo extractRequestLog(String message, HttpServletRequest request, HashMap<Object,Object> paramMap){
        return this.extractRequestLog(message, request);
    }

    /**
     * 提取请求日志信息.
     *
     * @param message 请求描述
     * @param request 请求对象
     * @return 日志信息
     */
    default RequestLogInfo extractRequestLog(String message, HttpServletRequest request){
        return this.extractRequestLog(message, request, null);
    }

    /**
     * 填充日志信息.
     *
     * @param logInfo     日志对象
     * @param message 请求描述
     * @param request 请求对象
     */
    static void populateRequestInfo(RequestLogInfo logInfo, String message, HttpServletRequest request) {
        logInfo.setMessage(message);
        logInfo.setUri(request.getRequestURI());
        logInfo.setQueryString(request.getQueryString());
        logInfo.setClientIp(WebUtils.getClientIpAddress(request));
        logInfo.setReferer(request.getHeader("referer"));
        logInfo.setRequestTime(System.currentTimeMillis());
        logInfo.setMethod(request.getMethod());
    }
}
