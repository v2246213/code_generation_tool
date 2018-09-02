package com.hello.service.impl;

import com.hello.entity.Users;
import com.hello.mapper.UsersMapper;
import com.hello.service.IUsersService;
import com.hello.base.BaseServiceImp;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangnian123
 * @since 2018-09-02
 */
@Service
public class UsersServiceImpl extends BaseServiceImp<UsersMapper, Users> implements IUsersService {

}
