package com.demo.controller;

import com.demo.model.Employee;
import com.demo.service.EmployeeService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    EmployeeService employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Employee employee) {
        employeeServiceImpl.signUp(employee);
        return ResponseEntity.ok("SIGNUP DONE");
    }

    @GetMapping("/signin/{empEmailID}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailID, String empPasswpord) {
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailID, empPasswpord));
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>> getAllData() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @GetMapping("/getdatabyid/{empId}")
    public ResponseEntity<Employee> getDataByID(@PathVariable int empId) {
        return ResponseEntity.ok(employeeServiceImpl.getDataById(empId));
    }

    @GetMapping("/getdatabyname/{empName}")
    public ResponseEntity<List<Employee>> getDataByName(@PathVariable String empName) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(e -> e.getEmpName().equals(empName)).collect(Collectors.toList()));
    }

    @GetMapping("/getdatabycontactnumber/{empContactNumber}")
    public ResponseEntity<List<Employee>> getDataByContactNumber(@PathVariable long empContactNumber) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(e -> e.getEmpContactNumber() == empContactNumber).collect(Collectors.toList()));
    }

    @GetMapping("/getdatabyanyinput/{input}")
    public ResponseEntity<List<Employee>> getDataByAnyInput(@PathVariable String input) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(e -> e.getEmpId() == Integer.parseInt(input) || e.getEmpName().equals(input)
                || e.getEmpSalary() == Double.valueOf(input) || e.getEmpContactNumber() == Long.valueOf(input) || e.getEmpAge() == Integer.parseInt(input)
                || e.getEmpEmailId().equals(input)).collect(Collectors.toList()));

    }

    @PutMapping("/updatedata/{empId}")
    public ResponseEntity<String> updateData(@PathVariable int empId, @RequestBody Employee employee) {
        employeeServiceImpl.updateData(empId, employee);
        return ResponseEntity.ok("DATA UPDATED");
    }

    @GetMapping("/sortbyid")
    public ResponseEntity<List<Employee>> sortDataById() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingInt(Employee::getEmpId)).collect(Collectors.toList()));
    }

    @GetMapping("/sortdatabyname")
    public ResponseEntity<List<Employee>> sortDatByName() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpName)).collect(Collectors.toList()));
    }

    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>> sortBySalary() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).collect(Collectors.toList()));
    }

    @GetMapping("/sortdatabydob")
    public ResponseEntity<List<Employee>> sortDataByDOB() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpDOB)).collect(Collectors.toList()));
    }

    @GetMapping("/filterdatabysalary/{empSalary}")
    public ResponseEntity<List<Employee>> filterDataBySalary(@PathVariable double empSalary) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(e -> e.getEmpSalary() >= empSalary).collect(Collectors.toList()));
    }

    @GetMapping("/sortdatabyage")
    public ResponseEntity<List<Employee>> sortDataByAge() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingInt(Employee::getEmpAge)).collect(Collectors.toList()));
    }

    @GetMapping("/filterdatabyage/{empAge}")
    public ResponseEntity<List<Employee>> filterDataByAge(@PathVariable int empAge) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(e -> e.getEmpAge() >= empAge).collect(Collectors.toList()));
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int empId) {
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("DATA DELETED");
    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<String> deleteAllData() {
        employeeServiceImpl.deleteAlldata();
        return ResponseEntity.ok("ALL DATA DELETED");
    }

}
