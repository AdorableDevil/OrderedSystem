package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employee")
//@CacheConfig(cacheNames = "employee")
public class EmployeeController  {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee  employee){
        String password = employee.getPassword();
         password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        if(emp==null)  return R.error("用户未注册");

        if(!emp.getPassword().equals(password))return R.error("密码错误");
        if(emp.getStatus()==0) {
            return  R.error("账号已禁用");
        }
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);


    }
    @PostMapping("/logout")
    public  R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
//    @CachePut(value = "getEmployeeList")
    @CacheEvict(value = "getEmployeeList",allEntries = true)
    @PostMapping
    public R<String>  save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("新增员工。员工信息{}",employee.toString());

        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        Long empId=(Long)request.getSession().getAttribute("employee");
//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);
        employeeService.save(employee);
        return R.success("新增员工成功");

    }
    @Cacheable(value ="getEmployeeList")
    @GetMapping("/page")
    public R page(String name, int pageSize, int page){
        log.info("page={},pageSize={},name={}",page,pageSize,name);
        Page pageInfo=new Page (page,pageSize);
        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper();

        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo,queryWrapper);
       // System.out.println(R.success(pageInfo));
      //  System.out.println(employeeService.list());
      //  System.out.println(pageInfo.getRecords());
        //return pageInfo.getRecords();

         return R.success(pageInfo);
    }
    @CacheEvict(value = "getEmployeeList",allEntries = true)
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        Long empId = (Long)request.getSession().getAttribute("employee");
        employee.setUpdateTime(LocalDateTime.now());
       // employee.setUpdateTime(System.currentTimeMillis());
        employee.setUpdateUser(empId);
        employeeService.updateById(employee);
        return  R.success("员工信息修改成功");
    }

    @GetMapping("/{id}")
    public  R<Employee> getById(@PathVariable Long id )
    {
        Employee employee = employeeService.getById(id);
        if(employee!=null){
            return R.success(employee);
    }
        return R.error("没有查询到对应员工信息");
    }
}
