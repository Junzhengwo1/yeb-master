package com.kou.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JIAJUN KOU
 *
 * 公共返回对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespBean {

    private long code;
    private String message;
    private Object object;

    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }

    /**
     * 成功返回的重载
     * @param message
     * @param object
     * @return
     */
    public static RespBean success(String message,Object object){
        return new RespBean(200,message,object);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }

    /**
     * 失败返回结果重载
     * @param message
     * @return
     */
    public static RespBean error(String message,Object object){
        return new RespBean(500,message,object);
    }




}
