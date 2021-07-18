package com.example.demo.service.impl;

import com.example.demo.common.domain.StudentExample;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.mapper.StudentsMapper;
import com.example.demo.pojo.Student;
import com.example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class IStudentServiceImpl implements IStudentService{
    @Autowired
    private StudentsMapper studentsMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAllStu() {
        return studentsMapper.findAllStu();
    }

    @Override
    public Student findStuById(Integer id) {
        return studentsMapper.findStuById(id);
    }

    @Override
    public List<Student> findLikely(Integer id) {
        return studentsMapper.findLikely(id);
    }

    @Override
    public void addStu(Student student) {
        studentsMapper.addStu(student);
    }

    @Override
    public void updateStu(Student student) {
        studentsMapper.updateStu(student);
    }

    @Override
    public void delStuById(Integer id) {
        studentsMapper.delStuById(id);
    }

    @Override
    public Integer findDidBySid(Integer sid) {
        return studentsMapper.findDidBySid(sid);
    }

    @Override
    public Integer countBeds(Integer did) {
        return studentsMapper.countBeds(did);
    }

    @Override
    public com.example.demo.common.domain.Student findStuBySid(Integer sid) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andSidEqualTo(sid);
        List<com.example.demo.common.domain.Student> students = studentMapper.selectByExample(studentExample);
        return students.isEmpty() ? null : students.get(0);
    }

    @Override
    public void updatePwd(com.example.demo.common.domain.Student student){
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andSidEqualTo(student.getSid());
        studentMapper.updateByExampleSelective(student,studentExample);
    }

    @Override
    public Integer findMaleStu(){
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andGenderEqualTo("男");
        List<com.example.demo.common.domain.Student> students = studentMapper.selectByExample(studentExample);
        return students.size();
    }
}
