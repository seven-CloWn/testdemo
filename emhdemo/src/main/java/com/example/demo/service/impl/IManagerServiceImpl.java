package com.example.demo.service.impl;

import com.example.demo.mapper.ManagerMapper;
import com.example.demo.mapper.ManagersMapper;
import com.example.demo.pojo.Manager;
import com.example.demo.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IManagerService")
public class IManagerServiceImpl implements IManagerService{
    @Autowired
    private ManagersMapper managers;
    @Autowired
    private ManagerMapper manager;
    @Override
    public List<Manager> findAllManager() {
        return managers.findAllManager();
    }
    @Override
    public com.example.demo.common.domain.Manager findManagerBymid(Integer mid){
        return manager.selectByPrimaryKey(mid);
    }
}
