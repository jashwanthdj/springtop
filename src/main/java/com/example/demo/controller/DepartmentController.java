package com.example.demo.controller;


import com.example.demo.dto.DepartmentRequestDTO;
import com.example.demo.dto.DepartmentResponseDTO;
import com.example.demo.dto.EmployeeRequestDTO;
import com.example.demo.dto.EmployeeResponseDTO;
import com.example.demo.entity.Department;
import com.example.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public DepartmentResponseDTO createDepartment (@RequestBody DepartmentRequestDTO departmentRequestDTO){
        return  departmentService.createDepartment(departmentRequestDTO);
    }
    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable("id") Long id){
        return departmentService.getDepartmentById(id);
    }

    @PutMapping({"/{id}"})
    public  DepartmentResponseDTO updateDepartment
            (@PathVariable("id") long departmentId,@RequestBody DepartmentRequestDTO departmentRequestDTO){
        return departmentService.updateDepartment(departmentId,departmentRequestDTO);
    }
}
