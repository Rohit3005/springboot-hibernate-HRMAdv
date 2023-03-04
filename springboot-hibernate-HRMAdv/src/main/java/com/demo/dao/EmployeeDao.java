package com.demo.dao;

import com.demo.model.Employee;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface EmployeeDao {

    public void signUp(Employee employee);

    public boolean signIn(String empEmailId, String empPassword);

    public List<Employee> getAllData();

    public Employee getDataById(int empId);

    public void updateData(int empId, Employee employee);

    public void deleteDataById(int empId);

    public void deleteAlldata();
}
