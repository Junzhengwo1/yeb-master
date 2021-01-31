package com.kou.server.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.kou.server.pojo.Department;
import com.kou.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author koujiajun
 * @since 2021-01-21
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 获取所有部门列表
     * @return
     */
    List<Department> getAllDepartments();

    /**
     * 添加部门
     * @param dep
     * @return
     */
    RespBean addDep(Department dep);

    /**
     * 删除部门
     * @param id
     * @return
     */
    RespBean deleteDep(Integer id);
}
