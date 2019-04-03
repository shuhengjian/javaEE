package com.creatorblue.cbitedu.core.interceptor;

import com.creatorblue.cbitedu.core.constants.Constant.DATA_SCOPE;


/**
* @ClassName: AConfig
* @Description: 权限配置
* @author tbw
* @date 2014-5-6 上午11:50:58
 */
public class AConfig {
    private boolean isValid = true;

    /**
     * 用户列名
     */
    private String userCol = "USER_ID";

    /**
     * 机构列名
     */
    private String orgCol = "ORG_NUMBER";

    /**
     * 数据权限类型
     */
    private DATA_SCOPE dataScope;

    public AConfig() {
        super();
    }

    /**
     * 构造权限配置
     * @param userCol 用户id列名
     * @param orgCol 机构id列名
     */
    public AConfig(String userCol, String orgCol) {
        super();
        this.userCol = userCol;
        this.orgCol = orgCol;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getUserCol() {
        return userCol;
    }

    public void setUserCol(String userCol) {
        this.userCol = userCol;
    }

    public String getOrgCol() {
        return orgCol;
    }

    public void setOrgCol(String orgCol) {
        this.orgCol = orgCol;
    }

    public DATA_SCOPE getDataScope() {
        return dataScope;
    }

    public void setDataScope(DATA_SCOPE dataScope) {
        this.dataScope = dataScope;
    }
}
