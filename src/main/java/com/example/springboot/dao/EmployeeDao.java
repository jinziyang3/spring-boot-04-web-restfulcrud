package com.example.springboot.dao;

import com.example.springboot.bean.Department;
import com.example.springboot.bean.Employee;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JinZiyang
 * @create 2019-11-29 - 15:10
 */
@Repository
public class EmployeeDao {
    private static Map<Integer, Employee> employees = null;

    public static Date date(String s ){
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = dateformat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }
    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer, Employee>();

        employees.put(1001, new Employee(1001, "E-AA", "aa@qq.com", 1, new Department(101, "D-AA"),date("1994-09-23")));
        employees.put(1002, new Employee(1002, "E-BB", "bb@qq.com", 2, new Department(102, "D-BB"),date("1993-03-13")));
        employees.put(1003, new Employee(1003, "E-CC", "cc@qq.com", 1, new Department(103, "D-CC"),date("1987-05-12")));
        employees.put(1004, new Employee(1004, "E-DD", "dd@qq.com", 2, new Department(104, "D-DD"),date("1998-09-23")));
        employees.put(1005, new Employee(1005, "E-EE", "ee@qq.com", 1, new Department(105, "D-EE"),date("2000-12-03")));
    }

    private static Integer initId = 1006;

    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

    //查询所有员工
    public Collection<Employee> getAll() {
        return employees.values();
    }

    public Employee get(Integer id) {
        return employees.get(id);
    }

    public void delete(Integer id) {
        employees.remove(id);
    }


}
