package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    private Integer sid;
    private String name;
    private String gender;
    private Integer did;
    private Integer age;
    private String major;
    private String tel;
    //因为使用了lombok所以不用自己写get/set方法
    private Dormitory dormitory;

}
