package com.cngc.boot.integration.gmp;

import com.cngc.boot.web.dictionary.service.DictTranslateService;
import com.cngc.boot.web.dictionary.translate.DictTranslator;
import org.jeecg.common.system.sdk.entity.GmpCategoryDictItem;
import org.jeecg.common.system.sdk.entity.GmpDictItem;
import org.jeecg.common.system.sdk.service.IGmpDictService;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * gmp字典条目编码转换实现.
 *
 * @author maxD
 */
public class DictTranslateServiceImpl implements DictTranslateService {
    private IGmpDictService gmpDictService;

    public DictTranslateServiceImpl(IGmpDictService gmpDictService) {
        this.gmpDictService = gmpDictService;
    }

    @Override
    public String translateCodeToDisplayName(String type, String code, DictTranslator dictTranslator) {
        String appCode = dictTranslator.appCode();
        String gmpDictType = StringUtils.hasLength(appCode) ? (appCode + ":" + type) : type;
        if (dictTranslator.isMultiLevel()) {
            // 分类字典
            return Optional.ofNullable(gmpDictService.getCategoryDictItem(gmpDictType, code))
                    .map(GmpCategoryDictItem::getName)
                    .orElse("");
        } else {
            // 数据字典
            return Optional.ofNullable(gmpDictService.getDictItem(gmpDictType, code))
                    .map(GmpDictItem::getName)
                    .orElse("");
        }
    }
}
