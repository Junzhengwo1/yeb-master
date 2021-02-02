package com.kou.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author JIAJUN KOU
 *
 * 公共的返回对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {

    //总条数
    private Long total;
    //数据
    private List<?> data;

}
