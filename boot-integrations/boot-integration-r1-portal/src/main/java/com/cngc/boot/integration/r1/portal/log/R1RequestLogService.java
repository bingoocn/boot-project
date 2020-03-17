package com.cngc.boot.integration.r1.portal.log;

import com.cngc.boot.web.log.RequestLogInfo;
import com.cngc.boot.web.log.RequestLogService;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class R1RequestLogService implements RequestLogService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public RequestLogInfo extractRequestLog(String message, HttpServletRequest request) {
        R1RequestLogInfo logInfo = new R1RequestLogInfo();
        RequestLogService.populateRequestInfo(logInfo, message, request);
        try {
            Context context = Context.getInstance();
            logInfo.setLoginAccount(context.getCurrentUserid());
            logInfo.setUserName(context.getCurrentPerson().getFullName());
            logInfo.setPersonId(context.getCurrentPersonUuid());
            logInfo.setSysId(context.getCurrentSubsystemId());
            logInfo.setSysName(context.getCurrentSubsystemName());
            logInfo.setOrgName(((Organization) context.getCurrentOrganization().get(0)).getName());
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
        return logInfo;
    }
}
