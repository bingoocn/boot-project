package com.cngc.boot.web.dictionary.service;


import com.cngc.boot.web.dictionary.translate.DictTranslator;

/**
 * 数据字典转换服务接口.
 *
 * @author duanyl
 */
public interface DictTranslateService {


    /**
     * 通过字典类型与条目编码转换为字典条目名字.
     *
     * @param type 字典类型
     * @param code 字典条目编码
     * @param dictTranslator 字典注解
     * @return 字典条目名字
     */
    String translateCodeToDisplayName(String type, String code, DictTranslator dictTranslator);
}
