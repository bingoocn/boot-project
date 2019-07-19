package com.cngc.boot.integration.mdm.service.imp;

import com.cngc.boot.integration.mdm.model.OrgDataRightSqlModel;
import com.cngc.boot.integration.mdm.model.OrgViewTb;
import com.cngc.boot.integration.mdm.service.BizOrgUserService;
import com.cngc.boot.integration.mdm.util.BgOrgUserHelper;
import com.cngc.boot.integration.mdm.util.DBUtils;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.Organization;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:viking
 * @date:2019/4/8 14:37
 */
@Service
public class BizOrgUserServiceImpl implements BizOrgUserService {

    public static final String ORG_TYPE = "ORGTYPE";
    public static final String ORG_TYPE1 = "0";
    public static final String ORG_TYPE2 = "1";

    public Organization getCurrentOrgInfo()
    {
        Organization orgInfo = null;
        try {
            Context ctx =
                    Context.getInstance();
            UserInfo ui = ctx.getCurrentLoginInfo();
            if (ui == null) {
                throw new NullPointerException("登录人员信息为空!");
            }
            List orgs = ctx.getCurrentOrganization();
            if (orgs == null) {
                throw new NullPointerException("登录人员组织信息为空!");
            }
            String pOrgId = ui.getPrimaryOrguuid();

            for (int i = 0; i < orgs.size(); ++i) {
                Organization org = (Organization)orgs.get(i);
                if (org.getOrgUuid().equalsIgnoreCase(pOrgId)) {
                    orgInfo = org;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        label114: return orgInfo;
    }

    @Override
    public OrgViewTb getCurrentOrgViewInfo(String vtId) {
        OrgViewTb orgViewTb = new OrgViewTb();
        try {
            String sql = "select t.view_id    AS id,\t\t\t\n       t.org_code         AS seqNo,        \n       t.vt_id            AS vtId,         \n       t.iorg_code        AS iorgCode,     \n       t.view_name        AS name,         \n       t.view_parent_id   AS viewParentId, \n       t.f_remarks        AS fRemarks,     \n       t.org_standardcode AS orgCode       \n  from ORG_VIEW_TB t\t\t\t\t\t\t\n where 1 = 1";
            Organization org = getCurrentOrgInfo();
            if (org != null) {
                String orgCode = org.getOrgcode();

                sql = sql + " AND t.vt_id='" + vtId + "'";
                sql = sql + " AND t.org_standardcode='" + orgCode + "'";
            }
            orgViewTb = DBUtils.queryBean(sql.toString(),OrgViewTb.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orgViewTb;
    }

    @Override
    public OrgViewTb getCurrentCompViewInfo(String vtId) {
        OrgViewTb orgViewTb = new OrgViewTb();
        try {
            String sql = "select t.view_id    AS id,\t\t\t\n       t.org_code         AS seqNo,        \n       t.vt_id            AS vtId,         \n       t.iorg_code        AS iorgCode,     \n       t.view_name        AS name,         \n       t.view_parent_id   AS viewParentId, \n       t.f_remarks        AS fRemarks,     \n       t.org_standardcode AS orgCode       \n  from ORG_VIEW_TB t\t\t\t\t\t\t\n where 1 = 1";
            Organization org = BgOrgUserHelper.getCurrentCompInfo();
            if (org != null) {
                String orgCode = org.getOrgcode();

                sql = sql + " AND t.vt_id='" + vtId + "'";
                sql = sql + " AND t.org_standardcode='" + orgCode + "'";
            }
            orgViewTb = DBUtils.queryBean(sql.toString(),OrgViewTb.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orgViewTb;
    }

    @Override
    public String getDataRightByOrgCode(String orgCode, String vtId)
            throws Exception {
        String retrunCode = null;
        StringBuffer sql = new StringBuffer(
                "select t1.view_id          AS viewId,\n       t1.org_code         AS orgCode,\n       t1.vt_id            AS vtId,\n       t1.iorg_code        AS iorgCode,\n       t1.view_name        AS viewName,\n       t1.view_parent_id   AS viewParentId,\n       t1.f_remarks        AS fRemarks,\n       t1.org_standardcode AS orgStandardcode,\n       t2.according_code   AS accordingCode\n  from ORG_VIEW_TB t1\n inner join org_dataright_tb t2\n    on t1.org_code = t2.org_code\n where 1 = 1");
        if (vtId != null) {
            sql.append(" AND t1.vt_id = '").append(vtId + "'   ");
        }
        if (orgCode != null) {
            sql.append(" AND t2.org_code = '").append(orgCode + "'   ");
        }
        OrgDataRightSqlModel orgDataRightVo = new OrgDataRightSqlModel();
        orgDataRightVo = DBUtils.queryBean(sql.toString(),OrgDataRightSqlModel.class);
        if (orgDataRightVo != null){
            retrunCode = orgDataRightVo.getAccordingCode();
        }
        else {
            retrunCode = orgCode;
        }
        return retrunCode;
    }

    @Override
    public OrgViewTb getOrgViewInfoByOrgCode(String orgCode, String vtId)
            throws Exception {
        OrgViewTb orgViewTb = new OrgViewTb();
        try {
            String sql = "select t.view_id    AS id,\t\t\t\n       t.org_code         AS seqNo,        \n       t.vt_id            AS vtId,         \n       t.iorg_code        AS iorgCode,     \n       t.view_name        AS name,         \n       t.view_parent_id   AS viewParentId, \n       t.f_remarks        AS fRemarks,     \n       t.org_standardcode AS orgCode       \n  from ORG_VIEW_TB t\t\t\t\t\t\t\n where 1 = 1";
            if (orgCode != null) {
                sql = sql + " AND t.vt_id='" + vtId + "'";
                sql = sql + " AND t.org_code='" + orgCode + "'";
            }
            orgViewTb = DBUtils.queryBean(sql.toString(),OrgViewTb.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orgViewTb;
    }
}
