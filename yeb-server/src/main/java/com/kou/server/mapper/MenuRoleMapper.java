package com.kou.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kou.server.pojo.MenuRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author koujiajun
 * @since 2021-01-21
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {


    /**
     * 批量更新角色菜单
     * @param rid
     * @param mids
     */
    Integer insertRecord(@Param("rid") Integer rid,@Param("mids") Integer[] mids);
}
