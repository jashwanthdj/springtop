package com.example.demo.repository;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository
        extends CrudRepository<Department,Long> {
}
