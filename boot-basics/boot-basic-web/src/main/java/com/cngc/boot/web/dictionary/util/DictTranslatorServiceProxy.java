package com.cngc.boot.web.dictionary.util;


import com.cngc.boot.core.util.SpringContextUtil;
import com.cngc.boot.web.dictionary.service.DictTranslateService;

/**
 * DictTranslatorService代理类.
 *
 * @author duanyl
 */
public class DictTranslatorServiceProxy {
    private static volatile DictTranslateService dicTranslatorService;

    private DictTranslatorServiceProxy() {

    }

    public static DictTranslateService getInstance() {
        if(dicTranslatorService == null) {
            synchronized (DictTranslatorServiceProxy.class) {
                if(dicTranslatorService == null) {
                    dicTranslatorService = SpringContextUtil.getBean(DictTranslateService.class);
                }
            }
        }
        return dicTranslatorService;
    }

}
