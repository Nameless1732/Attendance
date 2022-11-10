package com.jie.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jie.attendance.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseMapper extends BaseMapper<Course> {
    List<Course> queryList(Map<String, Object> paramMap);
    Integer queryCount(Map<String, Object> paramMap);
    int addStudentNum(Integer courseId);
}
