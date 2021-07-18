package com.example.demo.controller;

import com.example.demo.pojo.*;
import com.example.demo.service.IDormitoryService;
import com.example.demo.service.IManagerService;
import com.example.demo.service.IRepairsService;
import com.example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DormitoryController {
    @Autowired
    private IDormitoryService dormitoryService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IManagerService managerService;

    @Autowired
    private IRepairsService repairsService;

    @GetMapping("/dormitory")
    String getDormitories(Model model) {
        List<Status> statuses = new ArrayList<>();
        List<Dormitory> dormitories = dormitoryService.findAllDormitories();
        for (Dormitory dormitory : dormitories) {
            Status status = new Status();
            Integer countBeds = studentService.countBeds(dormitory.getDid());
            status.setDid(dormitory.getDid());
            status.setBid(dormitory.getBid());
            status.setDname(dormitory.getDName());
            status.setBeds(dormitory.getBeds());
            status.setFee(dormitory.getFee());
            status.setCurrentBeds(countBeds);
            status.setFreeBeds(dormitory.getBeds() - countBeds);
            statuses.add(status);
            //System.out.println(dormitory);
        }
        for (Status status1 : statuses) {
            System.out.println(status1);
        }
        model.addAttribute("statuses", statuses);
        return "dormitory/dormitoryList.html";
    }

    @GetMapping("/stuDormitory")
    String getStuDormitory(Model model) {
        Integer sid = LoginController.sid;
        Integer did = studentService.findDidBySid(sid);
        com.example.demo.common.domain.Dormitory current = dormitoryService.findDormitory(did);
        List<Dormitory> dormitories = dormitoryService.findAllStudentsByDormitory(did);
        model.addAttribute("dormitories", dormitories);
        model.addAttribute("current", current);
        return "dormitory/stuDormitoryList.html";
    }

    @GetMapping("/dormitorydetial/{did}")
    String getDormitorydetial(Model model, @PathVariable("did") Integer did) {
        List<Dormitory> dormitories = dormitoryService.findAllStudentsByDormitory(did);
        model.addAttribute("dormitories", dormitories);
        return "dormitory/adminDormitoryList.html";
    }

    @GetMapping("/addDormitory")
    public String toupdateDormitory(){
        return "dormitory/addDormitory.html";
    }

    //重定向有问题，还要在研究一下
    @PostMapping("/addDormitory")
    public String updateDormitory(Model model, com.example.demo.common.domain.Dormitory dormitory){
        com.example.demo.common.domain.Dormitory checkDid = dormitoryService.findDormitory(dormitory.getDid());
        if(checkDid != null){
            String msg = "房间已存在！";
            model.addAttribute("msg", msg);
            return "dormitory/addDormitory.html";
        }
        dormitory.setFee(0.00F);
        dormitoryService.addDormitory(dormitory);
        System.out.println(dormitory);
        return ("redirect:/dormitory");
    }

    @GetMapping("/updateDormitory/{id}")
    public String toUpdate(@PathVariable("id") Integer did, Model model){
        com.example.demo.common.domain.Dormitory dormitory = dormitoryService.findDormitory(did);
        model.addAttribute("dormitory", dormitory);
        return "dormitory/updateDormitory";
    }

    @PostMapping("/updateDormitory")
    public String updateDorm(com.example.demo.common.domain.Dormitory dormitory){
        dormitoryService.updateDormitory(dormitory);
        System.out.println(dormitory);
        return "redirect:/dormitory";
    }

    @GetMapping("/delDormitory/{did}")
    public String delDormitory(RedirectAttributesModelMap modelMap, @PathVariable("did") Integer did){
        com.example.demo.common.domain.Dormitory current = dormitoryService.findDormitory(did);
        //先检查是否还有学生，然后检查公告
        com.example.demo.common.domain.Dormitory checkstu = dormitoryService.findDormitory(did);
        Integer countBeds = studentService.countBeds(checkstu.getDid());
        if(countBeds != 0){
            String msg = "请确保宿舍中已无学生入住！";
            modelMap.addFlashAttribute("msg", msg);
            System.out.println(2);
            return "redirect:/dormitory";
        }
        List<Dormitory> checkRepairs = repairsService.findRepairByDid(did);
        if(!checkRepairs.isEmpty()){
            String msg = "请确保该宿舍已无待维修信息！";
            modelMap.addFlashAttribute("msg", msg);
            System.out.println(1);
            return "redirect:/dormitory";
        }
        dormitoryService.deleteDormitory(current);
        return "redirect:/dormitory";
    }

    @GetMapping("/DormGraphy")
    public String dormGraphy(){
        return "dormitory/Dormgraphy";
    }

    @GetMapping("/SexGraphy")
    public String sexGraphy(){
        return "dormitory/Sexgraphy";
    }

    @RequestMapping("/SexJosn")
    @ResponseBody
    public List<Sexnum> loadSexNumJosn(){
        List<Sexnum> res = new ArrayList();
        int maleNum = studentService.findMaleStu();
        int femaleNum = studentService.findAllStu().size() - maleNum;
        Sexnum temp = new Sexnum(maleNum,"男生");
        Sexnum temp1 = new Sexnum(femaleNum,"女生");
        res.add(temp);res.add(temp1);
        return res;
    }

    @RequestMapping("/JobJosn")
    @ResponseBody
    public List<Jobnum> loadJobNumJosn(){
        List<Jobnum> res = new ArrayList();
        int stu = studentService.findAllStu().size();
        int manager = managerService.findAllManager().size();
        Jobnum managers = new Jobnum(manager,"管理员");
        Jobnum stus = new Jobnum(stu,"学生");
        Jobnum repairer = new Jobnum(6,"维修人员");
        Jobnum teacher = new Jobnum(5,"教师");
        res.add(stus);res.add(managers);res.add(repairer);res.add(teacher);
        return res;
    }

    @GetMapping("/test")
    public String test(){
        return "dormitory/test";
    }
}
