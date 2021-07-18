package com.example.demo.service;

import com.example.demo.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IStudentService {
    //List<Student> findAllStu(Integer page);
    List<Student> findAllStu();

    Student findStuById(Integer sid);

    List<Student> findLikely(Integer sid);

    void addStu(Student student);

    void updateStu(Student student);

    void delStuById(Integer sid);

    Integer findDidBySid(Integer sid);

    Integer countBeds(Integer did);

    com.example.demo.common.domain.Student findStuBySid(Integer sid);

    void updatePwd(com.example.demo.common.domain.Student student);

    Integer findMaleStu();
}
