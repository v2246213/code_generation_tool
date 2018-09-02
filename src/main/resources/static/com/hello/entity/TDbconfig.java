package com.hello.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.hello.base.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangnian123
 * @since 2018-09-02
 */
@TableName("t_dbconfig")
public class TDbconfig extends BaseEntity<TDbconfig> {

    private static final long serialVersionUID = 1L;

    /**
     * 项目名称
     */
    @TableField("project_name")
    private String projectName;
    /**
     * 项目配置都在这一个字段
     */
    @TableField("config_json")
    private String configJson;


    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getConfigJson() {
        return configJson;
    }

    public void setConfigJson(String configJson) {
        this.configJson = configJson;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TDbconfig{" +
        "projectName=" + projectName +
        ", configJson=" + configJson +
        "}";
    }
}
