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
@TableName("sys_roles")
public class SysRoles extends BaseEntity<SysRoles> {

    private static final long serialVersionUID = 1L;

    @TableField("c_roleName")
    private String cRolename;


    public String getcRolename() {
        return cRolename;
    }

    public void setcRolename(String cRolename) {
        this.cRolename = cRolename;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysRoles{" +
        "cRolename=" + cRolename +
        "}";
    }
}
