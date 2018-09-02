package com.hello.mapper;

import com.hello.entity.SysUserRole;
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
@CacheNamespaceRef(SysUserRoleMapper.class)
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

}
