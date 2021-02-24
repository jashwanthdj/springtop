package com.example.demo.controller;

import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


@Autowired
EmployeeService employeeService;

    @PostMapping
    public EmployeeResponseDTO createEmployee (@RequestBody EmployeeRequestDTO employeeRequestDTO){
        return  employeeService.createEmployee(employeeRequestDTO);
    }

    @GetMapping("/{id}")
    public  EmployeeResponseDTO getEmployeeById(@PathVariable("id") long id){

        return  employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(@PathVariable("id") long id,@RequestBody EmployeeRequestDTO employeeRequestDTO){
        return employeeService.updateEmployeeById(id,employeeRequestDTO);
    }
    @DeleteMapping("/{id}")
    public EmployeeResponseDTO deleteEmployee(@PathVariable("id") long id){
        return employeeService.deleteEmployeeById(id);
    }
    @GetMapping({"/department/{id}"})
    public List<EmployeeResponseDTO> getEmployeeListByDepartment(
            @PathVariable("id") Long departmentId) {
        return employeeService.getEmployeeListByDepartment(departmentId);
    }

}
