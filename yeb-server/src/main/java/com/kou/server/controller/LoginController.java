package com.kou.server.controller;

import com.kou.server.pojo.AdminLoginParam;
import com.kou.server.pojo.RespBean;
import com.kou.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JIAJUN KOU
 *
 * 登录
 */
@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean Login(AdminLoginParam adminLoginParam, HttpServletRequest request){

        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),request);
    }


}
