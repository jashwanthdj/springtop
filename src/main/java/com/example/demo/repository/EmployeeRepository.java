package com.example.demo.repository;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository
        extends CrudRepository<Employee,Long> {


    List<Employee> findByDepartment(Department department);
    List<Employee> findByDepartment_Id(Long departmentId);
    @Query("FROM Employee  e WHERE  e.department.id=?1")
    List<Employee> GetEmployeeListByDepartmentId(Long departmentId);
   @Query("SELECT e FROM Employee e WHERE e.department.id=:departmentId")
    List<Employee> GetEmployeeListByDepartmentIdParam(@Param("departmentId") Long departmentId);

}
