package com.cngc.boot.integration.mdm.util;

import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.extension.ExtendedAttribute;
import com.icss.resourceone.sdk.extension.ExtendedAttributeList;
import com.icss.resourceone.sdk.framework.*;

import java.util.List;

/**
 * @author:viking
 * @date:2019/4/8 14:40
 */
public class BgOrgUserHelper {
    public static final String ORG_TYPE = "ORGTYPE";
    public static final String ORG_TYPE1 = "0";
    public static final String ORG_TYPE2 = "1";

    public static UserInfo getUserInfo() {
        UserInfo ui = null;
        try {
            Context ctx = Context.getInstance();
            ui = ctx.getCurrentLoginInfo();
            if (ui == null) {
                throw new NullPointerException("登录人员信息为空!");
            }
            return ui;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ui;
    }

    public static Person gePersonInfo() throws Exception {
        try {
            Context ctx = Context.getInstance();
            Person person = ctx.getCurrentPerson();
            if (person == null) {
                throw new NullPointerException("登录人员信息为空!");
            }
            return person;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static Organization getCurrentOrgInfo() throws Exception {
        try {
            Context ctx = Context.getInstance();
            UserInfo ui = ctx.getCurrentLoginInfo();
            if (ui == null) {
                throw new NullPointerException("登录人员信息为空!");
            }
            List orgs = ctx.getCurrentOrganization();
            if (orgs == null) {
                throw new NullPointerException("登录人员组织信息为空!");
            }
            String pOrgId = ui.getPrimaryOrguuid();

            Organization orgInfo = null;

            for (int i = 0; i < orgs.size(); ++i) {
                Organization org = (Organization) orgs.get(i);
                if (org.getOrgUuid().equalsIgnoreCase(pOrgId)) {
                    orgInfo = org;
                    break;
                }
            }
            return orgInfo;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static Organization getCurrentCompInfo() throws Exception {
        try {
            Organization orgInfo = null;
            Organization org = getCurrentOrgInfo();
            orgInfo = getUpperCompOrg(org);
            return orgInfo;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private static Organization getUpperCompOrg(Organization org) {
        if (org == null){
            return null;
        }
        try {
            ExtendedAttributeList extList = org.getExtendedAttributes();
            String orgTypeValue = null;
            if ((extList != null) && (!extList.isEmpty())){
                for (int i = 0; i < extList.size(); ++i) {
                    ExtendedAttribute ext = extList.get(i);
                    String orgCode = ext.getCode();
                    if ("ORGTYPE".equals(orgCode)) {
                        orgTypeValue = ext.getValue();
                        break;
                    }
                }
            }

            if ("0".equals(orgTypeValue)) {
                return org;
            }
            if (org.getParentOrg() != null) {
                return getUpperCompOrg(org.getParentOrg());
            }
            return null;
        } catch (EntityException e) {
            e.printStackTrace();
        }
           return org;
    }

    public static Organization getTopOrgInfoByOrgId(String orgUUID)
            throws Exception {
        try {
            EntityManager mgr = EntityManager.getInstance();
            Organization organization = mgr.findOrganizationByUuid(orgUUID);
            if (organization != null) {
                organization = getR1TopOrg(organization);
            }
            return organization;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private static Organization getR1TopOrg(Organization org) {
        if (org == null){
            return null;
        }
        try {
            if (org.isTopLevelOrg()) {
                return org;
            }
            return getR1TopOrg(org.getParentOrg());
        } catch (EntityException e) {
            e.printStackTrace();
        }
        return org;
    }

    public static String getUserUUID() throws Exception {
        try {
            Context ctx = Context.getInstance();
            UserInfo ui = ctx.getCurrentLoginInfo();
            if (ui == null) {
                throw new NullPointerException("登录人员信息为空!");
            }
            return ui.getPersonUuid();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static String getSystemUserId() throws Exception {
        try {
            Context ctx = Context.getInstance();
            return ctx.getCurrentUserid();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static String getUserName() throws Exception {
        try {
            Context ctx = Context.getInstance();
            UserInfo ui = ctx.getCurrentLoginInfo();
            if (ui == null) {
                throw new NullPointerException("登录人员信息为空!");
            }
            return ui.getCnName();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static Integer getCurrentSubsystemId() throws Exception {
        try {
            Context ctx = Context.getInstance();
            return ctx.getCurrentSubsystemId();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static String getCurrentSubsystemType() throws Exception {
        try {
            Context ctx = Context.getInstance();
            return ctx.getCurrentSubSystemType();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static String getCurrentSubsystemName() throws Exception {
        try {
            Context ctx = Context.getInstance();
            return ctx.getCurrentSubsystemName();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static Person findPersonByUuid(String uuid) throws Exception {
        try {
            EntityManager mgr = EntityManager.getInstance();
            if (mgr == null) {
                throw new NullPointerException("R1初始化实体管理器EntityManager!");
            }
            return mgr.findPersonByUid(uuid);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static Person findPersonByUserId(String userId) throws Exception {
        try {
            EntityManager mgr = EntityManager.getInstance();
            if (mgr == null) {
                throw new NullPointerException("R1初始化实体管理器EntityManager!");
            }
            return mgr.findPersonByUid(userId);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static List<Person> findPersonByPersonAtt(Person person)
            throws Exception {
        List list;
        if (person != null) {
            try {
                EntityManager mgr = EntityManager.getInstance();
                if (mgr == null) {
                    throw new NullPointerException("R1初始化实体管理器EntityManager!");
                }
                list = mgr.findPersons(person);
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            throw new Exception("Person对象未实例化！");
        }
        return list;
    }

    public static List<Person> findPersonByOrgUUID(String orgUUID)
            throws Exception {
        List list;
        try {
            EntityManager mgr = EntityManager.getInstance();
            if (mgr == null) {
                throw new NullPointerException("R1初始化实体管理器EntityManager!");
            }
            list = mgr.findPersonsByOrgUuid(orgUUID);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }

    public static List<Person> findPersonsRecursivelyByOrgUuid(String orgUUID)
            throws Exception {
        List list;
        try {
            EntityManager mgr = EntityManager.getInstance();
            if (mgr == null) {
                throw new NullPointerException("R1初始化实体管理器EntityManager!");
            }
            list = mgr.findPersonsRecursivelyByOrgUuid(orgUUID);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }

    public static List<Person> findPersonsByPersonGroupId(Integer groupLevel)
            throws Exception {
        List list;
        try {
            EntityManager mgr = EntityManager.getInstance();
            if (mgr == null) {
                throw new NullPointerException("R1初始化实体管理器EntityManager!");
            }
            list = mgr.findPersonsByPersonGroupId(groupLevel);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }

    public static List<Person> findPersonsByPositionGradeId(
            Integer positionLevel) throws Exception {
        List list = null;
        try {
            EntityManager mgr = EntityManager.getInstance();
            if (mgr == null) {
                throw new NullPointerException("R1初始化实体管理器EntityManager!");
            }
            list = mgr.findPersonsByPositionGradeId(positionLevel);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }

    public static Person findOrgLeader(String orgUUID) throws Exception {
        try {
            Organization org = new Organization();
            org.setUuid(orgUUID);

            EntityManager mgr = EntityManager.getInstance();
            if (mgr == null) {
                throw new NullPointerException("R1初始化实体管理器EntityManager!");
            }
            return mgr.findOrgLeader(org);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static List<Person> findPersonsByPositionCode(String positionCode)
            throws Exception {
        List list;
        try {
            EntityManager mgr = EntityManager.getInstance();
            if (mgr == null) {
                throw new NullPointerException("R1初始化实体管理器EntityManager!");
            }
            list = mgr.findPersonsByPositionCode(positionCode);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }

    public static Organization findOrganizationByUuid(String orgUUID)
            throws Exception {
        Organization org;
        try {
            EntityManager mgr = EntityManager.getInstance();
            if (mgr == null) {
                throw new NullPointerException("R1初始化实体管理器EntityManager!");
            }
            org = mgr.findOrganizationByUuid(orgUUID);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return org;
    }

    public static List<Organization> findOrganizationByUuid(
            Organization organization) throws Exception {
        List list;
        try {
            EntityManager mgr = EntityManager.getInstance();
            if (mgr == null) {
                throw new NullPointerException("R1初始化实体管理器EntityManager!");
            }
            list = mgr.findOrganizations(organization);
        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }
}
