package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginMapper {
    String checkAdminLogin(@Param("mid") Integer mid,@Param("password") String password);
    //String checkAdminLogin( Integer mid, String password);
    String checkStuLogin(@Param("sid") Integer sid,@Param("password") String password);

}
