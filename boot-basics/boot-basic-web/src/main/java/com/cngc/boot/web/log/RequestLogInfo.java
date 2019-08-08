package com.cngc.boot.web.log;

import lombok.Data;

/**
 * 请求日志类.
 *
 * @author maxD
 */
@Data
public class RequestLogInfo {

    private String loginAccount;
    private String userName;
    private String orgName;
    private String clientIp;
    private String method;
    private String uri;
    private String queryString;
    private String referer;
    private Long requestTime;
    private String message;
    private Object requestBody;

    private String sysName;
    private Integer sysId;
    private String personId;
    private LogRequestState state;


    enum LogRequestState {
        SUCCESS, FAIL;
    }

}
