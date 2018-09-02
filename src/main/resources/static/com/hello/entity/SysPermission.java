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
@TableName("sys_permission")
public class SysPermission extends BaseEntity<SysPermission> {

    private static final long serialVersionUID = 1L;

    @TableField("c_permisName")
    private String cPermisname;


    public String getcPermisname() {
        return cPermisname;
    }

    public void setcPermisname(String cPermisname) {
        this.cPermisname = cPermisname;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
        "cPermisname=" + cPermisname +
        "}";
    }
}
