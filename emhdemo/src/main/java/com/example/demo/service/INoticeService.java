package com.example.demo.service;

import com.example.demo.pojo.Notice;

import java.util.List;

public interface INoticeService {
    List<Notice> findAllNotices();
    Notice findNoticeById(Integer tid);
    void delNoticeById(Integer tid);
    void updateNotice(Notice notice);
    void addNotice(Notice notice);
}
