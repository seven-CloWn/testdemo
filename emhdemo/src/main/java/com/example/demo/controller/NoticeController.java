package com.example.demo.controller;

import com.example.demo.common.domain.Manager;
import com.example.demo.pojo.Notice;
import com.example.demo.service.IManagerService;
import com.example.demo.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class NoticeController {

    @Autowired
    private INoticeService noticeService;
    @Autowired
    private IManagerService managerService;
    @GetMapping("/adminHome")
    public String AdminNoticeHomePage(Model model){
        List<Notice> notices = noticeService.findAllNotices();
        model.addAttribute("notices",notices);
        return "adminHome.html";
    }
    @GetMapping("/stuHome")
    public String StuHomePage(Model model){
        List<Notice> notices = noticeService.findAllNotices();
        model.addAttribute("notices",notices);
        return "stuHome.html";
    }

    @GetMapping("/findNotice/{id}")
    public String toSingleNoticePage(@PathVariable("id") Integer id,Model model){
        Notice notice = noticeService.findNoticeById(id);
        model.addAttribute("notice",notice);
        return ("notice/notice.html");
    }

    @GetMapping("/adminfindNotice/{id}")
    public String admintoSingleNoticePage(@PathVariable("id") Integer id,Model model){
        Notice notice = noticeService.findNoticeById(id);
        model.addAttribute("notice",notice);
        return ("notice/adminnotice.html");
    }


    @GetMapping("/notice")
    public String toAddPage(){
        return "notice/addNotice.html";
    }

    @PostMapping("/notice")
    public String addNotice(Notice notice, Model model){
        System.out.println("新增公告信息："+notice);
        Manager tempCheck = managerService.findManagerBymid(notice.getMid());
        if(tempCheck == null){
            String msg = "请输入正确的管理员ID！";
            model.addAttribute("msg", msg);
            return "notice/addNotice.html";
        }
        noticeService.addNotice(notice);
        return "redirect:/adminHome";
    }

    @GetMapping("/notice/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id, Model model){
        Notice notice = noticeService.findNoticeById(id);
        model.addAttribute("notice",notice);
        return ("notice/updateNotice.html");
    }

    @PostMapping("/updateNotice")
    public String updateNotice(Model model, Notice notice){
        //检查管理员id是否合法
        Manager check = managerService.findManagerBymid(notice.getMid());
        if(check != null){
            noticeService.updateNotice(notice);
            System.out.println(notice);
            return "redirect:/adminHome";
        }
        String msg = "请输入正确的管理员编号！";
        model.addAttribute("msg", msg);
        return ("notice/updateNotice.html");
    }

    @GetMapping("/delNotice/{id}")
    public String delNotice(@PathVariable("id") Integer tid){
        noticeService.delNoticeById(tid);
        return ("redirect:/adminHome");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }

}
