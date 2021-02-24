package com.example.demo.service.impl;

import com.example.demo.dto.DepartmentRequestDTO;
import com.example.demo.dto.DepartmentResponseDTO;
import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService
{
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO departmentRequestDTO) {

        Department department=new Department();

        //copy fields from dto to employee
        BeanUtils.copyProperties(departmentRequestDTO,department);

        //save employee to db
        Department savedDepartment= departmentRepository.save(department);

        // copy from employee to response dto
        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();

        BeanUtils.copyProperties(savedDepartment, responseDTO);


        return responseDTO;
    }

}
