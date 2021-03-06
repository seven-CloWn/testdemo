package com.example.demo.mapper;

import com.example.demo.pojo.Dormitory;
import com.example.demo.pojo.Repair;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RepairsMapper {

    List<Repair> findAllRepairs();

    Repair findRepairById(Integer tid);

    void delRepairById(Integer tid);

    void updateRepair(Repair repair);

    void stuUpdateRepair(Repair repair);

    void addRepair(Repair repair);

    List<Dormitory> findRepairByDid(Integer did);

    List<Dormitory> adminFindRepairByDid();
}
