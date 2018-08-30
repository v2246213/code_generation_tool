package com.ndhc.cloud.logic.mpgenerator.entity;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**用户配置数据
 * @author yangnian
 * @datc 2018/8/30 14:18
 */
public class UserConfig {
    private  int id;
    /**
     * 项目名称
     */
    @NotBlank
    private  String projectName;
    /**
     * 项目配置都在这一个字段
     */
    @NotBlank
    private  String configJson;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
