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
@TableName("users")
public class Users extends BaseEntity<Users> {

    private static final long serialVersionUID = 1L;

    @TableField("c_username")
    private String cUsername;
    @TableField("c_pwd")
    private String cPwd;
    @TableField("c_phone")
    private String cPhone;
    @TableField("n_age")
    private Integer nAge;
    @TableField("n_sex")
    private Integer nSex;
    @TableField("c_createDate")
    private String cCreatedate;
    @TableField("n_creater")
    private Long nCreater;
    @TableField("c_updateDate")
    private String cUpdatedate;
    @TableField("n_updater")
    private Long nUpdater;
    @TableField("n_deleted")
    private Integer nDeleted;
    @TableField("n_status")
    private Integer nStatus;


    public String getcUsername() {
        return cUsername;
    }

    public void setcUsername(String cUsername) {
        this.cUsername = cUsername;
    }

    public String getcPwd() {
        return cPwd;
    }

    public void setcPwd(String cPwd) {
        this.cPwd = cPwd;
    }

    public String getcPhone() {
        return cPhone;
    }

    public void setcPhone(String cPhone) {
        this.cPhone = cPhone;
    }

    public Integer getnAge() {
        return nAge;
    }

    public void setnAge(Integer nAge) {
        this.nAge = nAge;
    }

    public Integer getnSex() {
        return nSex;
    }

    public void setnSex(Integer nSex) {
        this.nSex = nSex;
    }

    public String getcCreatedate() {
        return cCreatedate;
    }

    public void setcCreatedate(String cCreatedate) {
        this.cCreatedate = cCreatedate;
    }

    public Long getnCreater() {
        return nCreater;
    }

    public void setnCreater(Long nCreater) {
        this.nCreater = nCreater;
    }

    public String getcUpdatedate() {
        return cUpdatedate;
    }

    public void setcUpdatedate(String cUpdatedate) {
        this.cUpdatedate = cUpdatedate;
    }

    public Long getnUpdater() {
        return nUpdater;
    }

    public void setnUpdater(Long nUpdater) {
        this.nUpdater = nUpdater;
    }

    public Integer getnDeleted() {
        return nDeleted;
    }

    public void setnDeleted(Integer nDeleted) {
        this.nDeleted = nDeleted;
    }

    public Integer getnStatus() {
        return nStatus;
    }

    public void setnStatus(Integer nStatus) {
        this.nStatus = nStatus;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Users{" +
        "cUsername=" + cUsername +
        ", cPwd=" + cPwd +
        ", cPhone=" + cPhone +
        ", nAge=" + nAge +
        ", nSex=" + nSex +
        ", cCreatedate=" + cCreatedate +
        ", nCreater=" + nCreater +
        ", cUpdatedate=" + cUpdatedate +
        ", nUpdater=" + nUpdater +
        ", nDeleted=" + nDeleted +
        ", nStatus=" + nStatus +
        "}";
    }
}
