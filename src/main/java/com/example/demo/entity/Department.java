package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Department {@Id
@GenericGenerator(name = "department_id_seq", strategy = "increment")
@GeneratedValue(generator = "department_id_seq", strategy = GenerationType.AUTO)
private Long id;
    private String name;
    @JoinColumn(referencedColumnName = "id", name = "department_id")
    @OneToMany
    List<Employee> employeeList;
}
