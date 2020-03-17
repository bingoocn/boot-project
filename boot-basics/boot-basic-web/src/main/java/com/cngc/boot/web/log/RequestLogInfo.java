package com.cngc.boot.web.log;

import lombok.Data;

/**
 * 请求日志类.
 *
 * @author maxD
 */
@Data
public class RequestLogInfo {

    private String clientIp;
    private String method;
    private String uri;
    private String queryString;
    private String referer;
    private Long requestTime;
    private String message;
    private LogRequestState state;

    /**
     * 请求结果.
     */
    enum LogRequestState {
        // 成功
        SUCCESS,
        // 失败
        FAIL
    }

    protected void setState(LogRequestState state) {
        this.state = state;
    }
}
