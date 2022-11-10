package com.jie.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jie.attendance.entity.Attendance;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AttendanceMapper extends BaseMapper<Attendance> {
    List<Attendance> queryList(Map<String, Object> paramMap);
    int queryCount(Map<String, Object> paramMap);
    int edit_1(@Param("attendanceId") Integer id);
    int edit_2(@Param("attendanceId") Integer id);
    int edit_3(@Param("attendanceId") Integer id);
    int edit_4(@Param("attendanceId") Integer id);
    int edit_5(@Param("attendanceId") Integer id);
}
