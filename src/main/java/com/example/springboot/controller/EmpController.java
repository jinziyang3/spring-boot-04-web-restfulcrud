package com.example.springboot.controller;

import com.example.springboot.bean.Department;
import com.example.springboot.bean.Employee;
import com.example.springboot.dao.DepartmentDao;
import com.example.springboot.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author JinZiyang
 * @date 2019/12/1 - 14:01
 */
@Controller
public class EmpController {
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工返回列表页面
    @GetMapping(value = "/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        //放在请求域中
        model.addAttribute("emps", employees);
        return "emp/list";
    }

    //来到员工添加路径
    @GetMapping("/emp")
    public String toAddPage(Model model) {

        //来到添加页面,查出所有的部门在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";

    }

    //员工添加
    //SpringMVC自动将请求参数和入参对象的属性进行一一绑定；要求了请求参数的名字和javabean入参的对象里面的属性名等会一样的
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        System.out.println("保存的员工信息" + employee);
        employeeDao.save(employee);
        //redirect：表示重定向到一个地址  /代表当前项目路径
        //forward：表示转发到一个地址
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        System.out.println(employee.getDepartment().getId());
        //显示所有部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        //回到修改页面（add是修改添加二合一）
        return "emp/add";
    }

    @PutMapping("/emp")
    public String updateEmp(Employee employee) {
        System.out.println("修改的员工数据" + employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String delete(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
