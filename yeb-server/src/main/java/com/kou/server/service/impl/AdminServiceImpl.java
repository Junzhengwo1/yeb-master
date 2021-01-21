package com.kou.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kou.server.mapper.AdminMapper;
import com.kou.server.pojo.Admin;
import com.kou.server.pojo.RespBean;
import com.kou.server.service.IAdminService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JIAJUN KOU
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Override
    public RespBean login(String username, String password, HttpServletRequest request) {
        return null;
    }



}
