package com.kou.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.kou.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author koujiajun
 * @since 2021-01-21
 */

public interface IMenuService extends IService<Menu> {

    /**
     * 根据id查询菜单
     * @return
     */
    List<Menu> getMenusByAdminId();
}
