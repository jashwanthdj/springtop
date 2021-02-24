package com.example.demo.service.impl;

import com.example.demo.dto.DepartmentRequestDTO;
import com.example.demo.dto.DepartmentResponseDTO;
import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDTO) {
        Employee employee = new Employee();

        //copy fields from dto to employee
        BeanUtils.copyProperties(employeeRequestDTO, employee);
         Optional<Department> departmentOptional= departmentRepository.findById(employeeRequestDTO.getDepartment().getId());
         if(departmentOptional.isPresent()){
             employee.setDepartment(departmentOptional.get());
         }
         else{
             Department department=new Department();
             department.setName(employeeRequestDTO.getDepartment().getName());
             employee.setDepartment(department);
         }

        //save employee to db
        Employee savedEmployee = employeeRepository.save(employee);

        // copy from employee to response dto
        EmployeeResponseDTO responseDto = new EmployeeResponseDTO();
        BeanUtils.copyProperties(savedEmployee, responseDto);

        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        departmentResponseDTO.setId(savedEmployee.getDepartment().getId());
        departmentResponseDTO.setName(savedEmployee.getDepartment().getName());

        responseDto.setDepartment(departmentResponseDTO);
        return responseDto;
    }
    @Override
    public EmployeeResponseDTO getEmployeeById(Long id){
        Optional<Employee> employeeOptional=employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(employeeOptional.get(),responseDTO);
            return responseDTO;
        }
        return null;
    }

    @Override
    public EmployeeResponseDTO updateEmployeeById(Long id,EmployeeRequestDTO employeeRequestDTO){
        Optional<Employee> employeeOptional=employeeRepository.findById(id);
        Employee employee = new Employee();
        if(employeeOptional.isPresent()){
            Employee employeeFromDb=employeeOptional.get();
            employeeFromDb.setName(employeeRequestDTO.getName());
            Optional<Department> departmentOptional= departmentRepository.findById(employeeRequestDTO.getDepartment().getId());
            if(departmentOptional.isPresent()){
                employeeFromDb.setDepartment(departmentOptional.get());
            }
            else{
                Department department=new Department();
                department.setName(employeeRequestDTO.getDepartment().getName());

                employee.setDepartment(department);
            }


            Employee savedEmployee=employeeRepository.save(employeeFromDb);


            EmployeeResponseDTO responseDTO=new EmployeeResponseDTO();
            BeanUtils.copyProperties(savedEmployee,responseDTO);

            responseDTO.setDepartmentFromEntity(savedEmployee.getDepartment());

            return responseDTO;
        } return  null;
    }

    @Override
    public EmployeeResponseDTO deleteEmployeeById(Long id){

        Optional<Employee> employeeOptional=employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
            BeanUtils.copyProperties(employeeOptional,responseDTO);
            employeeRepository.deleteById(id);
            return responseDTO;


        } return null;


    }

    @Override
    public List<EmployeeResponseDTO> getEmployeeListByDepartment(Long departmentId) {
        //1)Department department=departmentRepository.findById(departmentId).get();
        //1)List<Employee> employeeList=employeeRepository.findByDepartment(department);
        //2)List<Employee> employeeList=employeeRepository.findByDepartment_Id(departmentId);
        List<Employee> employeeList= employeeRepository.GetEmployeeListByDepartmentId(departmentId);
        List<EmployeeResponseDTO> employeeResponseDTOList=new ArrayList<>();
        for(Employee employee:employeeList){
            EmployeeResponseDTO responseDto = new EmployeeResponseDTO();
            BeanUtils.copyProperties(employee, responseDto);
            responseDto.setDepartmentFromEntity(employee.getDepartment());
            employeeResponseDTOList.add(responseDto);
        }

        return employeeResponseDTOList;
    }



}