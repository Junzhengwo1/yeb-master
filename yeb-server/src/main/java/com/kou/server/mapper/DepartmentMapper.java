package com.kou.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kou.server.pojo.Department;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author koujiajun
 * @since 2021-01-21
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartments(Integer parentId);
}
