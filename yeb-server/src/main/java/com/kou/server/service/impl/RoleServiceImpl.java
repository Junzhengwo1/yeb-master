package com.kou.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kou.server.mapper.RoleMapper;
import com.kou.server.pojo.Role;
import com.kou.server.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * @author JIAJUN KOU
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
}
