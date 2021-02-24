package com.example.demo.service.impl;

import com.example.demo.dto.DepartmentRequestDTO;
import com.example.demo.dto.DepartmentResponseDTO;
import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService
{
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

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
    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    @Transactional
    public DepartmentResponseDTO updateDepartment
            (Long departmentId, DepartmentRequestDTO departmentRequestDTO) {
        Department department=departmentRepository.findById(departmentId).get();

        List<Employee> employeeList=employeeRepository.findByDepartment_Id(departmentId);

        department.setName(departmentRequestDTO.getName());
        Department savedDepartment=departmentRepository.save(department);






        employeeList.forEach(employee ->
        {
            employee.setCode(departmentRequestDTO.getDepartmentCode());
        });
                employeeRepository.saveAll(employeeList);
        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();
        BeanUtils.copyProperties(savedDepartment,responseDTO);

        return responseDTO;
    }

}
