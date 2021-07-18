package com.example.demo.service;

import com.example.demo.pojo.Dormitory;
import com.example.demo.pojo.Student;

import java.util.List;

public interface IDormitoryService {
    List<Dormitory> findAllDormitories();
    List<Dormitory> findAllStudentsByDormitory(Integer did);
    List<Dormitory> adminFindAllStudentsByDormitory();
    com.example.demo.common.domain.Dormitory findDormitory(Integer did);
    com.example.demo.common.domain.Dormitory findDormBySid(Integer sid);
    void addDormitory(com.example.demo.common.domain.Dormitory dormitory);
    void updateDormitory(com.example.demo.common.domain.Dormitory dormitory);
    void deleteDormitory(com.example.demo.common.domain.Dormitory dormitory);
    //Integer getFreeBeds(Integer did);
}
