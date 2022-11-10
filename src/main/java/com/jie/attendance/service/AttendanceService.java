package com.jie.attendance.service;

import com.jie.attendance.entity.Attendance;
import com.jie.attendance.utils.PageBean;

import java.util.Map;

public interface AttendanceService {
    PageBean<Attendance> queryPage(Map<String, Object> paramMap);

    boolean checkAttendance(Attendance attendance);

    int addAttendance(Attendance attendance);

    int deleteAttendance(Integer id);

    int edit_1(Integer id);

    int edit_2(Integer id);

    int edit_3(Integer id);

    int edit_4(Integer id);

    int edit_5(Integer id);
}
