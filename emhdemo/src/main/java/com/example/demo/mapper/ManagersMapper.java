package com.example.demo.mapper;

import com.example.demo.pojo.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ManagersMapper {
    List<Manager> findAllManager();

}