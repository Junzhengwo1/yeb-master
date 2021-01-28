package com.kou.server.controller;

import com.kou.server.pojo.Admin;
import com.kou.server.pojo.AdminLoginParam;
import com.kou.server.pojo.RespBean;
import com.kou.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

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
    @ResponseBody
    public RespBean Login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){

        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("admin/info")
    public Admin getAdminInfo(Principal principal){
        if(principal==null){
            return null;
        }
        //这个获取到的就是我们的用户名
        String username = principal.getName();
        Admin admin=adminService.getAdminByUserName(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;

    }


    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout(){

        return RespBean.success("注销成功~");
    }



}
