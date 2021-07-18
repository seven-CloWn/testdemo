package com.example.demo.service;

import com.example.demo.pojo.Dormitory;
import com.example.demo.pojo.Repair;

import java.util.List;

public interface IRepairsService {
    List<Repair> findAllRepairs();
    Repair findRepairById(Integer rid);
    void delRepairById(Integer rid);
    void updateRepair(Repair repair);
    void stuUpdateRepair(Repair repair);
    void addRepair(Repair repair);
    List<Dormitory> findRepairByDid(Integer did);
    List<Dormitory> adminFindRepairByDid();

}
