package com.demo.service;

import com.demo.model.Employee;

import java.util.List;

public interface EmployeeService {

    public void signUp(Employee employee);

    public boolean signIn(String empEmailId, String empPassword);

    public List<Employee> getAllData();

    public Employee getDataById(int empId);

    public void updateData(int empId, Employee employee);

    public void deleteDataById(int empId);

    public void deleteAlldata();
}
