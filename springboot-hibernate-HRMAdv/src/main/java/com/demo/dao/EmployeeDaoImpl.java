package com.demo.dao;

import com.demo.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    private static SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();

    @Override
    public void signUp(Employee employee) {

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(employee);
        transaction.commit();

    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {

        boolean flag = false;
        for (Employee employee : getAllData()) {
            if (employee.getEmpEmailId().equals(empEmailId)
                    && employee.getEmpPassword().equals(empPassword)) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public List<Employee> getAllData() {

        Session session = factory.openSession();
        return session.createQuery("from Employee").list();
    }

    @Override
    public Employee getDataById(int empId) {
        Session session = factory.openSession();
        return (Employee) session.get(Employee.class, empId);
    }

    @Override
    public void updateData(int empId, Employee employee) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee1 = getDataById(empId);
        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpAge(employee.getEmpAge());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpPassword(employee.getEmpPassword());

        session.update(employee1);
        transaction.commit();

    }

    @Override
    public void deleteDataById(int empId) {

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = getDataById(empId);
        session.delete(employee);
        transaction.commit();

    }

    @Override
    public void deleteAlldata() {

        Session session = factory.openSession();
        for (Employee employee : getAllData()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }

    }
}
