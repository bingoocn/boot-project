package com.cngc.boot.integration.mdm.service;

import com.cngc.boot.integration.mdm.model.OrgViewTb;
import org.springframework.stereotype.Service;

/**
 * @author:viking
 * @date:2019/4/8 14:35
 */
@Service
public abstract interface BizOrgUserService {
    public abstract OrgViewTb getCurrentOrgViewInfo(String paramString)
            throws Exception;

    public abstract OrgViewTb getOrgViewInfoByOrgCode(String paramString1,
                                                      String paramString2) throws Exception;

    public abstract OrgViewTb getCurrentCompViewInfo(String paramString)
            throws Exception;

    public abstract String getDataRightByOrgCode(String paramString1,
                                                 String paramString2) throws Exception;
}
