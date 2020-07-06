package com.cngc.boot.web.log;

public interface RequestLogSend {

    void send(RequestLogInfo logInfo) throws Throwable ;

}
