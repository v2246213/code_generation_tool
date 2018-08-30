package com.ndhc.cloud.logic.mpgenerator.entity;

/** 数据库表信息
 * @author yangnian
 * @datc 2018/8/29 15:09
 */
public class TableInfo  {
    //表名
    private  String tableName;
    //表引擎
    private  String engine;
    //表创建时间
    private String createTime;
    //表注释
    private  String tableComment;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
}
