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
@TableName("sys_user_role")
public class SysUserRole extends BaseEntity<SysUserRole> {

    private static final long serialVersionUID = 1L;

    @TableField("n_userId")
    private Integer nUserid;
    @TableField("n_roleId")
    private Integer nRoleid;


    public Integer getnUserid() {
        return nUserid;
    }

    public void setnUserid(Integer nUserid) {
        this.nUserid = nUserid;
    }

    public Integer getnRoleid() {
        return nRoleid;
    }

    public void setnRoleid(Integer nRoleid) {
        this.nRoleid = nRoleid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysUserRole{" +
        "nUserid=" + nUserid +
        ", nRoleid=" + nRoleid +
        "}";
    }
}
