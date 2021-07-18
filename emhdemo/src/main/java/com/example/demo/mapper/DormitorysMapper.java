package com.example.demo.mapper;

import com.example.demo.pojo.Dormitory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DormitorysMapper {
    List<Dormitory> findAllDormitories();

    List<Dormitory> findAllStudentsByDormitory(Integer did);

    List<Dormitory> adminFindAllStudentsByDormitory();

    //Integer getFreeBeds(Integer did);
}
