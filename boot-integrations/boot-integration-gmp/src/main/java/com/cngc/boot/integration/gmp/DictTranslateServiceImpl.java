package com.cngc.boot.integration.gmp;

import com.cngc.boot.web.dictionary.service.DictTranslateService;
import com.cngc.boot.web.dictionary.translate.DictTranslator;
import org.jeecg.common.system.sdk.entity.CategoryDictItem;
import org.jeecg.common.system.sdk.entity.DictItem;
import org.jeecg.common.system.sdk.service.IAdminDictService;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * gmp字典条目编码转换实现.
 *
 * @author maxD
 */
public class DictTranslateServiceImpl implements DictTranslateService {
    private IAdminDictService adminDictService;

    public DictTranslateServiceImpl(IAdminDictService adminDictService) {
        this.adminDictService = adminDictService;
    }

    @Override
    public String translateCodeToDisplayName(String type, String code, DictTranslator dictTranslator) {
        String appCode = dictTranslator.appCode();
        String gmpDictType = StringUtils.hasLength(appCode) ? (appCode + ":" + type) : type;
        if (dictTranslator.isMultiLevel()) {
            // 分类字典
            return Optional.ofNullable(adminDictService.getCategoryDictItem(gmpDictType, code))
                    .map(CategoryDictItem::getName)
                    .orElse("");
        } else {
            // 数据字典
            return Optional.ofNullable(adminDictService.getDictItem(gmpDictType, code))
                    .map(DictItem::getName)
                    .orElse("");
        }
    }
}
