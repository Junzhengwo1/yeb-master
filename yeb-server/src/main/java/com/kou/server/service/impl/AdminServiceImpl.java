package com.kou.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kou.server.config.jwt.JwtTokenUtil;
import com.kou.server.mapper.AdminMapper;
import com.kou.server.pojo.Admin;
import com.kou.server.pojo.RespBean;
import com.kou.server.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JIAJUN KOU
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    //这个对象是Security框架里面的
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired(required=false)
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private AdminMapper adminMapper;


    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {

        //获取session中我们之前保存的验证码
        String captcha = (String)request.getSession().getAttribute("captcha");
        System.out.println("验证的验证码"+captcha);
        //开始判断验证码
        if(StringUtils.isEmpty(code)||!captcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码错误，请重新输入");

        }

        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails==null||!passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("用户或密码不正确");
        }
        if(!userDetails.isEnabled()){
            return RespBean.error("账号被禁用，请联系管理员！");
        }
        //更新security登录用户对象//第三个参数权限列表
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        //生成的token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);

        return RespBean.success("登录成功",tokenMap);
    }

    @Override
    public Admin getAdminByUserName(String username) {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username)
                .eq("enabled", true));
        return admin;
    }


}
