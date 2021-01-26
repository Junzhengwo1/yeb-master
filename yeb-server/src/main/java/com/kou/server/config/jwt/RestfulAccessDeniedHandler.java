package com.kou.server.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kou.server.pojo.RespBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author JIAJUN KOU
 *
 * 当访问接口时没有权限，自定义返回结果
 *
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        RespBean error = RespBean.error("RestfulAccessDeniedHandler+权限不足，联系管理员");
        error.setCode(403);
        out.write(new ObjectMapper().writeValueAsString(error));
        out.flush();
        out.close();

    }
}
