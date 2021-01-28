package com.kou.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kou.server.mapper.MenuMapper;
import com.kou.server.pojo.Admin;
import com.kou.server.pojo.Menu;
import com.kou.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author JIAJUN KOU
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {


    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 根据用户id查询菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId() {

        Admin admin =(Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer adminId = admin.getId();

        //将我们的菜单信息放在redis中 将来好操作
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //这里就是从Redis中获取菜单数据 key获取值
        List<Menu> menus=(List<Menu>) valueOperations.get("menu_" + adminId);
        if(CollectionUtils.isEmpty(menus)){
            menus= menuMapper.getMenusByAdminId(adminId);
            //将数据设置到Redis中
            valueOperations.set("menu_"+adminId,menus);
        }

        return menus;
    }

    /**
     * 根据角色获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }
}
