package com.example.demo.mapper;

import com.example.demo.common.domain.Dormitory;
import com.example.demo.common.domain.DormitoryExample;
import com.example.demo.common.domain.DormitoryKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DormitoryMapper {
    long countByExample(DormitoryExample example);

    int deleteByExample(DormitoryExample example);

    int deleteByPrimaryKey(DormitoryKey key);

    int insert(Dormitory record);

    int insertSelective(Dormitory record);

    List<Dormitory> selectByExample(DormitoryExample example);

    Dormitory selectByPrimaryKey(DormitoryKey key);

    int updateByExampleSelective(@Param("record") Dormitory record, @Param("example") DormitoryExample example);

    int updateByExample(@Param("record") Dormitory record, @Param("example") DormitoryExample example);

    int updateByPrimaryKeySelective(Dormitory record);

    int updateByPrimaryKey(Dormitory record);
}