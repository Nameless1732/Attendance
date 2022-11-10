package com.jie.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jie.attendance.entity.Course;
import com.jie.attendance.entity.SelectedCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SelectedCourseMapper extends BaseMapper<SelectedCourse> {
    List<SelectedCourse> queryList(Map<String, Object> paramMap);
    int queryCount(Map<String, Object> paramMap);
    List<SelectedCourse> getAllBySid(Integer studentid);
    Course getCourseDetail(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);
}
