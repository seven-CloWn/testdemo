package com.example.demo.service.impl;

import com.example.demo.common.domain.DormitoryExample;
import com.example.demo.common.domain.Student;
import com.example.demo.common.domain.StudentExample;
import com.example.demo.mapper.DormitorysMapper;
import com.example.demo.mapper.DormitoryMapper;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.pojo.Dormitory;
import com.example.demo.service.IDormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IDormitoryService")
public class IDormitoryServiceImpl implements IDormitoryService {
    @Autowired
    private DormitorysMapper dormitorysMapper;
    @Autowired
    private DormitoryMapper dormitoryMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Dormitory> findAllDormitories() {
        return dormitorysMapper.findAllDormitories();
    }

    @Override
    public List<Dormitory> findAllStudentsByDormitory(Integer did) {
        return dormitorysMapper.findAllStudentsByDormitory(did);
    }

    @Override
    public List<Dormitory> adminFindAllStudentsByDormitory() {
        return dormitorysMapper.adminFindAllStudentsByDormitory();
    }

    @Override
    public com.example.demo.common.domain.Dormitory findDormitory(Integer did) {
        DormitoryExample dormitoryExample = new DormitoryExample();
        dormitoryExample.createCriteria().andDidEqualTo(did);
        List<com.example.demo.common.domain.Dormitory> res = dormitoryMapper.selectByExample(dormitoryExample);
        return res.isEmpty() ? null : res.get(0);
    }
    @Override
    public com.example.demo.common.domain.Dormitory findDormBySid(Integer sid){
        DormitoryExample dormitoryExample = new DormitoryExample();
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andSidEqualTo(sid);
        List<Student> temp = studentMapper.selectByExample(studentExample);
        dormitoryExample.createCriteria().andDidEqualTo(temp.get(0).getDid());
        List<com.example.demo.common.domain.Dormitory> res = dormitoryMapper.selectByExample(dormitoryExample);
        return res.isEmpty() ? null : res.get(0);
    }

    @Override
    public void addDormitory(com.example.demo.common.domain.Dormitory dormitory){
        dormitoryMapper.insert(dormitory);
    }

    @Override
    public void updateDormitory(com.example.demo.common.domain.Dormitory dormitory){
        DormitoryExample dormitoryExample = new DormitoryExample();
        dormitoryExample.createCriteria().andDidEqualTo(dormitory.getDid());
        dormitoryMapper.updateByExample(dormitory, dormitoryExample);
    }

    @Override
    public void deleteDormitory(com.example.demo.common.domain.Dormitory dormitory){
        dormitoryMapper.deleteByPrimaryKey(dormitory);
    }
}
