package com.cngc.boot.web.log;

import com.cngc.boot.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

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
     * @return 日志信息
     */
    RequestLogInfo extractRequestLog(String message, HttpServletRequest request);

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
        logInfo.setState(RequestLogInfo.LogRequestState.SUCCESS);
        logInfo.setMethod(request.getMethod());
    }
}
