package com.example.demo.dto;

import com.example.demo.entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDTO {

    private Long id;
    private String name;
    private DepartmentResponseDTO department;

    public void setDepartmentFromEntity(Department departmentEntity){
        DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
        departmentResponseDTO.setId(departmentEntity.getId());
        departmentResponseDTO.setName(department.getName());
        this.department = departmentResponseDTO;
    }
}
