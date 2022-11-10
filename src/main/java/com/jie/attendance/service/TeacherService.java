package com.jie.attendance.service;

import com.jie.attendance.dto.LoginDTO;
import com.jie.attendance.entity.Teacher;
import com.jie.attendance.utils.PageBean;

import java.util.List;
import java.util.Map;

public interface TeacherService {
    PageBean<Teacher> queryPage(Map<String, Object> paramMap);

    int deleteTeacher(List<Integer> ids);

    int addTeacher(Teacher teacher);

    int editTeacher(Teacher teacher);

    Teacher login(LoginDTO loginDTO);

    int editPswdByTeacher(Teacher teacher);
}
