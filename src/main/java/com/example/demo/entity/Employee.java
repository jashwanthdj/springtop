package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.redis.core.RedisHash;


@RedisHash
@Getter
@Setter
public class Employee {

    private Long id;
    private String name;
    private String departmentName;

}
