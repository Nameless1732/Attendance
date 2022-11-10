package com.jie.attendance.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jie.attendance.entity.Attendance;
import com.jie.attendance.mapper.AttendanceMapper;
import com.jie.attendance.service.AttendanceService;
import com.jie.attendance.utils.PageBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceMapper attendanceMapper;

    public AttendanceServiceImpl(AttendanceMapper attendanceMapper) {
        this.attendanceMapper = attendanceMapper;
    }

    @Override
    public PageBean<Attendance> queryPage(Map<String, Object> paramMap) {
        PageBean<Attendance> pageBean = new PageBean<>(
                (Integer) paramMap.get("pageno"),
                (Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<Attendance> attendanceList = attendanceMapper.queryList(paramMap);
        pageBean.setDatas(attendanceList);

        Integer totalSize = attendanceMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalSize);
        return pageBean;
    }

    @Override
    public boolean checkAttendance(Attendance attendance) {
        LambdaQueryWrapper<Attendance> wrapper = new LambdaQueryWrapper<Attendance>()
                .eq(Attendance::getCourseId, attendance.getCourseId())
                .eq(Attendance::getStudentId, attendance.getStudentId())
                .last("LIMIT 1");
        return Objects.nonNull(attendanceMapper.selectOne(wrapper));
    }

    @Override
    public int addAttendance(Attendance attendance) {
        return attendanceMapper.insert(attendance);
    }

    @Override
    public int deleteAttendance(Integer id) {
        return attendanceMapper.deleteById(id);
    }

    @Override
    public int edit_1(Integer id) {
        return attendanceMapper.edit_1(id);
    }

    @Override
    public int edit_2(Integer id) {
        return attendanceMapper.edit_2(id);
    }

    @Override
    public int edit_3(Integer id) {
        return attendanceMapper.edit_3(id);
    }

    @Override
    public int edit_4(Integer id) {
        return attendanceMapper.edit_4(id);
    }

    @Override
    public int edit_5(Integer id) {
        return attendanceMapper.edit_5(id);
    }
}
