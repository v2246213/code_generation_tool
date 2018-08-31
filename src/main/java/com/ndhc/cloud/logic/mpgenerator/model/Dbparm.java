package com.ndhc.cloud.logic.mpgenerator.model;

import com.ndhc.cloud.logic.mpgenerator.entity.DbConfig;
import com.ndhc.cloud.logic.mpgenerator.entity.UserConfig;

import java.util.List;

/**
 * @author yangnian
 * @datc 2018/8/30 11:29
 */
public class Dbparm {
    List<DbConfig> dbConfigList;
    List<UserConfig> userConfigs;

    public List<UserConfig> getUserConfigs() {
        return userConfigs;
    }

    public void setUserConfigs(List<UserConfig> userConfigs) {
        this.userConfigs = userConfigs;
    }

    public List<DbConfig> getDbConfigList() {
        return dbConfigList;
    }

    public void setDbConfigList(List<DbConfig> dbConfigList) {
        this.dbConfigList = dbConfigList;
    }
}
