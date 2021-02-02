package com.kou.server.controller;


import com.kou.server.pojo.Employee;
import com.kou.server.pojo.RespPageBean;
import com.kou.server.service.IEmployeeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author koujiajun
 * @since 2021-01-21
 */
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @ApiOperation(value = "查询所有员工，并分页")
    @GetMapping("/")
    public RespPageBean getAllEmployees(@RequestParam(defaultValue = "1")Integer currentPage,
                                        @RequestParam(defaultValue = "10") Integer size,
                                        Employee employee,
                                        LocalDate[] beginDateScope){

        return employeeService.getEmployeeByPage(currentPage,size,employee,beginDateScope);

    }

}
