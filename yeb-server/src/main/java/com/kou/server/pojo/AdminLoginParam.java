package com.kou.server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author JIAJUN KOU
 *
 * 用户登录 专门用于登录的实体类
 */
@Data
@EqualsAndHashCode(callSuper = false)//不重写equals和HashCode
@Accessors(chain = true)//get//set方法
@ApiModel(value = "AdminLogin对象",description = "")
public class AdminLoginParam {

    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @ApiModelProperty(value = "密码",required = true)
    private String password;



}
