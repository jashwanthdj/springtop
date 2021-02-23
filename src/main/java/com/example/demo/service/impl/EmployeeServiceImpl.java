package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl
        implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employeeRequestDto) {
        Employee employee = new Employee();

        //copy fields from dto to employee
        BeanUtils.copyProperties(employeeRequestDto, employee);

        //save employee to db
        Employee savedEmployee = employeeRepository.save(employee);

        // copy from employee to response dto
        EmployeeResponseDTO responseDto = new EmployeeResponseDTO();
        BeanUtils.copyProperties(savedEmployee, responseDto);


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
        if(employeeOptional.isPresent()){
            Employee employeeFromDb=employeeOptional.get();
            employeeFromDb.setName(employeeRequestDTO.getName());
            employeeFromDb.setDepartmentName(employeeRequestDTO.getDepartmentName());


            Employee savedEmployee=employeeRepository.save(employeeFromDb);


            EmployeeResponseDTO responseDTO=new EmployeeResponseDTO();
            BeanUtils.copyProperties(savedEmployee,responseDTO);

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





}