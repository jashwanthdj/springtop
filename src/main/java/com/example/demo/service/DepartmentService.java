package com.example.demo.service;

import com.example.demo.dto.DepartmentRequestDTO;
import com.example.demo.dto.DepartmentResponseDTO;
import com.example.demo.entity.Department;

public interface DepartmentService {

    DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO);
    Department getDepartmentById(Long id);

    DepartmentResponseDTO updateDepartment(Long departmentId, DepartmentRequestDTO departmentRequestDTO);
}
