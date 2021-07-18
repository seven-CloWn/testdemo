package com.example.demo.service.impl;

import com.example.demo.mapper.LoginMapper;
import com.example.demo.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ILoginService")
public class ILoginServiceImpl implements ILoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Override
    public String checkStuLogin(Integer sid, String password) {
        return loginMapper.checkStuLogin(sid,password);
    }

    @Override
    public String checkAdminLogin(Integer mid, String password) {
        return loginMapper.checkAdminLogin(mid,password);
    }

}
