package com.example.demo.mapper;

import com.example.demo.common.domain.Announcement;
import com.example.demo.common.domain.AnnouncementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnnouncementMapper {
    long countByExample(AnnouncementExample example);

    int deleteByExample(AnnouncementExample example);

    int deleteByPrimaryKey(Integer tid);

    int insert(Announcement record);

    int insertSelective(Announcement record);

    List<Announcement> selectByExampleWithBLOBs(AnnouncementExample example);

    List<Announcement> selectByExample(AnnouncementExample example);

    Announcement selectByPrimaryKey(Integer tid);

    int updateByExampleSelective(@Param("record") Announcement record, @Param("example") AnnouncementExample example);

    int updateByExampleWithBLOBs(@Param("record") Announcement record, @Param("example") AnnouncementExample example);

    int updateByExample(@Param("record") Announcement record, @Param("example") AnnouncementExample example);

    int updateByPrimaryKeySelective(Announcement record);

    int updateByPrimaryKeyWithBLOBs(Announcement record);

    int updateByPrimaryKey(Announcement record);
}