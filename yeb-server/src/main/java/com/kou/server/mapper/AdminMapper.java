package com.kou.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kou.server.pojo.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author koujiajun
 * @since 2021-01-21
 */

public interface AdminMapper extends BaseMapper<Admin> {


    /**
     * 获取所有操作员
     * @param id
     * @param keyWord
     * @return
     */
    List<Admin> getAllAdmins(@Param("id") Integer id,@Param("keyword") String keyWord);
}
