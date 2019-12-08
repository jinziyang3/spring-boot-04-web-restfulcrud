package com.example.springboot.dao;

import com.example.springboot.bean.Department;
import com.example.springboot.bean.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JinZiyang
 * @create 2019-11-29 - 15:13
 */
@Repository
public class DepartmentDao {
    private static Map<Integer, Department> Departments = null;

    static {
        Departments = new HashMap<Integer, Department>();

        Departments.put(101,new Department(101,"D-AA"));
        Departments.put(102,new Department(102,"D-BB"));
        Departments.put(103,new Department(103,"D-CC"));
        Departments.put(104,new Department(104,"D-DD"));
        Departments.put(105,new Department(105,"D-EE"));

    }

    public Collection<Department> getDepartments(){
        return Departments.values();
    }

    public Department getDepartment(Integer id){
        return Departments.get(id);
    }
}
