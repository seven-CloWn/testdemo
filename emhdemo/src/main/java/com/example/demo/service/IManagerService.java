package com.example.demo.service;

import com.example.demo.pojo.Manager;

import java.util.List;

public interface IManagerService {
    List<Manager> findAllManager();
    com.example.demo.common.domain.Manager findManagerBymid(Integer mid);
}
