package com.kou.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.kou.server.pojo.MenuRole;
import com.kou.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author koujiajun
 * @since 2021-01-21
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
