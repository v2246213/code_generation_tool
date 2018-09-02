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
@TableName("sys_role_permission")
public class SysRolePermission extends BaseEntity<SysRolePermission> {

    private static final long serialVersionUID = 1L;

    @TableField("n_permission_id")
    private Long nPermissionId;
    @TableField("n_role_id")
    private Long nRoleId;


    public Long getnPermissionId() {
        return nPermissionId;
    }

    public void setnPermissionId(Long nPermissionId) {
        this.nPermissionId = nPermissionId;
    }

    public Long getnRoleId() {
        return nRoleId;
    }

    public void setnRoleId(Long nRoleId) {
        this.nRoleId = nRoleId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysRolePermission{" +
        "nPermissionId=" + nPermissionId +
        ", nRoleId=" + nRoleId +
        "}";
    }
}
