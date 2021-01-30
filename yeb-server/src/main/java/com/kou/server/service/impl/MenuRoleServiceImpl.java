package com.kou.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kou.server.mapper.MenuRoleMapper;
import com.kou.server.pojo.MenuRole;
import com.kou.server.pojo.RespBean;
import com.kou.server.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author JIAJUN KOU
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        //先删除该角色下面的所有菜单
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid", rid));
        //再判断
        if (null == mids || 0 == mids.length) {
            return RespBean.success("更新成功!");
        }
        Integer res = menuRoleMapper.insertRecord(rid, mids);
        if (res == mids.length) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }
}
