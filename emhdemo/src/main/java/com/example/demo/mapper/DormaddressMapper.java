package com.example.demo.mapper;

import com.example.demo.common.domain.Dormaddress;
import com.example.demo.common.domain.DormaddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DormaddressMapper {
    long countByExample(DormaddressExample example);

    int deleteByExample(DormaddressExample example);

    int deleteByPrimaryKey(Integer bid);

    int insert(Dormaddress record);

    int insertSelective(Dormaddress record);

    List<Dormaddress> selectByExample(DormaddressExample example);

    Dormaddress selectByPrimaryKey(Integer bid);

    int updateByExampleSelective(@Param("record") Dormaddress record, @Param("example") DormaddressExample example);

    int updateByExample(@Param("record") Dormaddress record, @Param("example") DormaddressExample example);

    int updateByPrimaryKeySelective(Dormaddress record);

    int updateByPrimaryKey(Dormaddress record);
}