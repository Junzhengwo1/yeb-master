package com.kou.server.controller;

import com.kou.server.pojo.Admin;
import com.kou.server.pojo.RespBean;
import com.kou.server.service.IAdminService;
import com.kou.server.utils.FastDfsUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author JIAJUN KOU
 * 个人中心
 */
@RestController
public class AdminInfoController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "更新当前用户信息")
    @PutMapping("/admin/info")
    public RespBean updateAdmin(@RequestBody Admin admin, Authentication authentication){
        if(adminService.updateById(admin)){
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(admin,null,authentication.getAuthorities()));
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation(value = "更新用户密码")
    @PutMapping("/admin/pass")
    public RespBean updateAdminPassword(@RequestBody Map<String,Object> info){

        String oldPass = (String) info.get("oldPass");
        String pass = (String) info.get("pass");
        Integer adminId = (Integer) info.get("adminId");
        return adminService.updateAdminPassword(oldPass,pass,adminId);
    }

    @ApiOperation(value = "更换头像")
    @PostMapping("/admin/userFace")
    public RespBean updateAdminUserFace(MultipartFile file,Integer id,Authentication authentication){
        String[] filePath = FastDfsUtils.upload(file);
        //关键点，将我们的图片放在了FastDfs中了
        String url=FastDfsUtils.getTrackerUrl()+filePath[0]+"/"+filePath[1];
        return adminService.updateAdminUserFace(url,id,authentication);
    }

}
