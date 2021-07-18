package com.example.demo.controller;

import com.example.demo.pojo.Dormitory;
import com.example.demo.pojo.Notice;
import com.example.demo.pojo.Repair;
import com.example.demo.pojo.Student;
import com.example.demo.service.IDormitoryService;
import com.example.demo.service.IRepairsService;
import com.example.demo.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
@Controller
public class RepairsController {
    @Autowired
    private IRepairsService repairsService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IDormitoryService dormitoryService;

    @RequestMapping("/adminRepairs")
    public String adminRepair(Model model){
        List<Dormitory> dormitories = repairsService.adminFindRepairByDid();
        model.addAttribute("dormitories",dormitories);
        return "repairs/adminRepairs.html";
    }

    @RequestMapping("/stuRepairs")
    public String StuRepair(Model model){
        Integer sid = LoginController.sid;
        Integer did = studentService.findDidBySid(sid);
        System.out.println("did:"+did);
        List<Dormitory> dormitories = repairsService.findRepairByDid(did);
        model.addAttribute("dormitories",dormitories);
        return "repairs/stuRepairs.html";
    }

    @GetMapping("/adminfindRepair/{id}")
    public String admintoSinglePage(@PathVariable("id") Integer rid, Model model){
        Repair repair = repairsService.findRepairById(rid);
        model.addAttribute("repair",repair);
        return ("repairs/checkRepair.html");
    }

    @GetMapping("/adminRepair/{id}")
    public String toUpdatePage(@PathVariable("id") Integer rid, Model model){
        Repair repair = repairsService.findRepairById(rid);
        model.addAttribute("repair",repair);
        return ("repairs/updateRepair.html");
    }

    @PostMapping("/updateRepair")
    public String updateRepair(Repair repair){
        repairsService.updateRepair(repair);
        System.out.println(repair);
        return "redirect:/adminRepairs";
    }

    @GetMapping("/stuRepair/{id}")
    public String toStuUpdatePage(@PathVariable("id") Integer rid, Model model){
        Repair repair = repairsService.findRepairById(rid);
        model.addAttribute("repair",repair);
        return ("repairs/stuUpdateRepair.html");
    }

    @GetMapping("/stuFindRepair/{id}")
    public String stuCheckRepair(@PathVariable("id") Integer rid, Model model){
        Repair repair = repairsService.findRepairById(rid);
        model.addAttribute("repair",repair);
        return ("repairs/checkStuRepair.html");
    }

    @PostMapping("/stuUpdateRepair")
    public String stuUpdateRepair(Repair repair){
        repairsService.stuUpdateRepair(repair);
        System.out.println(repair);
        return "redirect:/stuRepairs";
    }

    @GetMapping("/delRepair/{rid}")
    public String delRepair(@PathVariable("rid") Integer rid){
        repairsService.delRepairById(rid);
        return ("redirect:/adminRepairs");
    }
    @GetMapping("/addRepair")
    public String toAddRepair(){
        return "repairs/addRepair.html";
    }

    @PostMapping("/addRepair")
    public String addStuRepair(Model model, Repair repair){
        System.out.println("新增维修信息"+repair);
        com.example.demo.common.domain.Dormitory check = dormitoryService.findDormitory(repair.getDid());
        if(check == null){
            String msg = "请输入正确的房间号！";
            model.addAttribute("msg", msg);
            return "repairs/addRepair.html";
        }
        Repair checkRid = repairsService.findRepairById(repair.getRid());
        if(checkRid != null){
            String msg = "编号已存在！";
            model.addAttribute("msg", msg);
            return "repairs/addRepair.html";
        }
        repairsService.addRepair(repair);
        return "redirect:/stuRepairs";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }
}
