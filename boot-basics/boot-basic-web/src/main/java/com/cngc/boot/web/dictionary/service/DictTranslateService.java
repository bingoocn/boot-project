package com.cngc.boot.web.dictionary.service;


import com.cngc.boot.web.dictionary.model.Dictionary;

/**
 * 数据字典转换服务接口.
 * @author duanyl
 */
public interface DictTranslateService {

    String translateCodeToDisplayName(String type, String code);
}
