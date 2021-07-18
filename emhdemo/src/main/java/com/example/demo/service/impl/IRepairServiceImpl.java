package com.example.demo.service.impl;

import com.example.demo.mapper.RepairsMapper;
import com.example.demo.pojo.Dormitory;
import com.example.demo.pojo.Repair;
import com.example.demo.service.IRepairsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IRepairsService")
public class IRepairServiceImpl implements IRepairsService{
    @Autowired
    private RepairsMapper repairsMapper;
    @Override
    public List<Repair> findAllRepairs() {
        return repairsMapper.findAllRepairs();
    }

    @Override
    public Repair findRepairById(Integer rid) {
        return repairsMapper.findRepairById(rid);
    }

    @Override
    public void delRepairById(Integer rid) {
        repairsMapper.delRepairById(rid);
    }

    @Override
    public void updateRepair(Repair repair) {
        repairsMapper.updateRepair(repair);
    }

    @Override
    public void stuUpdateRepair(Repair repair) {
        repairsMapper.stuUpdateRepair(repair);
    }

    @Override
    public void addRepair(Repair repair) {
        repairsMapper.addRepair(repair);
    }

    @Override
    public List<Dormitory> findRepairByDid(Integer did) {
        return repairsMapper.findRepairByDid(did);
    }

    @Override
    public List<Dormitory> adminFindRepairByDid() {
        return repairsMapper.adminFindRepairByDid();
    }
}
