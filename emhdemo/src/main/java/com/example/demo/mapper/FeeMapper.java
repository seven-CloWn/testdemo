package com.example.demo.mapper;

import com.example.demo.common.domain.Fee;
import com.example.demo.common.domain.FeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FeeMapper {
    long countByExample(FeeExample example);

    int deleteByExample(FeeExample example);

    int deleteByPrimaryKey(Integer did);

    int insert(Fee record);

    int insertSelective(Fee record);

    List<Fee> selectByExample(FeeExample example);

    Fee selectByPrimaryKey(Integer did);

    int updateByExampleSelective(@Param("record") Fee record, @Param("example") FeeExample example);

    int updateByExample(@Param("record") Fee record, @Param("example") FeeExample example);

    int updateByPrimaryKeySelective(Fee record);

    int updateByPrimaryKey(Fee record);
}