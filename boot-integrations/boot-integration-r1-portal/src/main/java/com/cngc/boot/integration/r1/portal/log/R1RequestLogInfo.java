package com.cngc.boot.integration.r1.portal.log;

import com.cngc.boot.web.log.RequestLogInfo;
import lombok.Data;

@Data
public class R1RequestLogInfo extends RequestLogInfo {
    private String loginAccount;
    private String userName;
    private String orgName;
    private String sysName;
    private Integer sysId;
    private String personId;
}
