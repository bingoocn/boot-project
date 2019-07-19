package com.cngc.boot.integration.mdm.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author:viking
 * @date:2019/4/8 14:21
 */
@Data
public class OrgViewTb {
    private static final long serialVersionUID = 1L;
    private BigDecimal id;
    private String orgId;
    private String orgCode;
    private String seqNo;
    private BigDecimal vtId;
    private String iorgCode;
    private String name;
    private BigDecimal viewParentId;
    private String fRemarks;
    private String isParent = "true";
    private String deptCode;
}
