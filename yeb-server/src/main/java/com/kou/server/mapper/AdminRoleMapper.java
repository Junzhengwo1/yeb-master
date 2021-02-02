package com.kou.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kou.server.pojo.AdminRole;
import com.kou.server.pojo.RespBean;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author koujiajun
 * @since 2021-01-21
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    Integer updateAdminRole(@Param("adminId") Integer adminId,@Param("rids") Integer[] rids);

}
