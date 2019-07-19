package com.cngc.boot.integration.mdm.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author:viking
 * @date:2019/4/8 14:38
 */
@Data
public class OrgDataRightSqlModel {
    private String accordingCode;
    private BigDecimal viewId;
    private String orgCode;
    private BigDecimal vtId;
    private String iorgCode;
    private String viewName;
    private BigDecimal viewParentId;
    private String fRemarks;
    private String orgStandardcode;
}
