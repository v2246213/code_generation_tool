package com.hello.mapper;

import com.hello.entity.SysRolePermission;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yangnian123
 * @since 2018-09-02
 */
<!-- 开启二级缓存 -->
@CacheNamespaceRef(SysRolePermissionMapper.class)
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

}
