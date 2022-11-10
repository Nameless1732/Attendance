package com.jie.attendance.service;

import com.jie.attendance.entity.SelectedCourse;
import com.jie.attendance.entity.Course;
import com.jie.attendance.utils.PageBean;

import java.util.List;
import java.util.Map;

public interface SelectedCourseService {
    PageBean<SelectedCourse> queryPage(Map<String, Object> paramMap);

    int addSelectedCourse(SelectedCourse selectedCourse);

    int deleteSelectedCourse(Integer id);

    boolean isStudentId(Integer studentId);

    List<SelectedCourse> getAllBySid(Integer studentId);

    Course getCourseDetail(Integer studentId, Integer courseId);
}
