package com.example.demo.controller;

import com.example.demo.common.domain.Dormitory;
import com.example.demo.pojo.Student;
import com.example.demo.service.IDormitoryService;
import com.example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IDormitoryService dormitoryService;

    @RequestMapping("/stus")
    public String getStuList(Model model){
        List<Student> students = studentService.findAllStu();
        System.out.println(students.get(0));
        model.addAttribute("stus",students);
        return "stus/list.html";
    }

    @GetMapping("/stu")
    public String toAddPage(){
        return "stus/add.html";
    }

    @PostMapping("/stu")
    public String addStu(Model model, Student student){
        System.out.println("新增student信息1："+student);
        //检查学生ID是否存在
        Student check = studentService.findStuById(student.getSid());
        String msg = "";
        if(check != null){
            msg = "学号已存在！";
            model.addAttribute("msg",msg);
            return "stus/add.html";
        }
        //检查宿舍号是否合法
        //实现查找床数
        Integer countBeds = studentService.countBeds(student.getDid());
        Dormitory fullBeds = dormitoryService.findDormitory(student.getDid());
        //System.out.println("检查："+ (fullBeds.getDid()));
        if(fullBeds != null && fullBeds.getBeds() - countBeds > 0) {
            studentService.addStu(student);
            return "redirect:/stus";
        }
        msg = "房间已满或不存在！";
        model.addAttribute("msg",msg);
        return "stus/add.html";
    }

    @GetMapping("/stu/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id,Model model){
        Student student = studentService.findStuById(id);
        model.addAttribute("stu",student);
        return ("stus/update.html");
    }

    @PostMapping("/updateStu")
    public String updateStu(Student student, RedirectAttributesModelMap modelMap){
        Dormitory checkDom = dormitoryService.findDormitory(student.getDid());
        Student current = studentService.findStuById(student.getSid());
        Dormitory check = dormitoryService.findDormBySid(student.getSid());
        //System.out.println(checkDom.getDid());
        Integer bedscount = studentService.countBeds(student.getDid());
        if(checkDom == null){
            String msg = "请输入正确的房间号！";
            modelMap.addFlashAttribute("msg",msg);
            int temp = student.getSid();
            return "redirect:/stu/" + temp;
        }
        boolean flag = true;
        if(check.getDid().equals(checkDom.getDid())){
            flag = false;
        }
        if(flag && checkDom.getBeds() - bedscount <= 0){
            String msg = "房间已满！";
            modelMap.addFlashAttribute("msg",msg);
            int temp = student.getSid();
            return "redirect:/stu/" + temp;
        }
        studentService.updateStu(student);
        return "redirect:/stus";
    }

    @GetMapping("/changePwd/{id}")
    public String tochangePwd(Model model, @PathVariable("id") Integer sid){
        com.example.demo.common.domain.Student student = studentService.findStuBySid(sid);
        System.out.println("test1111");
        model.addAttribute("stu", student);
        return "stus/changepwd";
    }

    //修改密码
    @PostMapping("/changePwd")
    public String changePwd(RedirectAttributesModelMap modelMap,Integer sid, String oldPwd, String newPwd){
        com.example.demo.common.domain.Student student = studentService.findStuBySid(sid);
        if(!student.getPassword().equals(oldPwd)){
            String msg = "原始密码错误！";
            modelMap.addFlashAttribute("msg", msg);
            return ("redirect:/toSingleStu");//确定跳转到哪里好，后边再改
        }
        student.setPassword(newPwd);
        studentService.updatePwd(student);
        System.out.println("test222");
        String msg = "修改成功，请尽快重新登录！";
        modelMap.addFlashAttribute("msg", msg);
        return ("redirect:/toSingleStu");//修改成功应该跳转到登录界面！
    }

    @GetMapping("/delStu/{id}")
    public String delStu(@PathVariable("id") Integer id){
        studentService.delStuById(id);
        return ("redirect:/stus");
    }

    @GetMapping("/search")
    public String findLikely(@RequestParam("sid") Integer id,Model model){
        List<Student> students = studentService.findLikely(id);
        model.addAttribute("stus",students);
        return "stus/list.html";
    }
}
