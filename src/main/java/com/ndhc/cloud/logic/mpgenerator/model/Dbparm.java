package com.ndhc.cloud.logic.mpgenerator.model;

import com.ndhc.cloud.logic.mpgenerator.entity.DbConfig;

import java.util.List;

/**
 * @author yangnian
 * @datc 2018/8/30 11:29
 */
public class Dbparm {
    List<DbConfig> dbConfigList;

    public List<DbConfig> getDbConfigList() {
        return dbConfigList;
    }

    public void setDbConfigList(List<DbConfig> dbConfigList) {
        this.dbConfigList = dbConfigList;
    }
}
