package com.jie.attendance.service;

import com.jie.attendance.dto.LoginDTO;
import com.jie.attendance.entity.Student;
import com.jie.attendance.utils.PageBean;

import java.util.List;
import java.util.Map;

public interface StudentService {
    PageBean<Student> queryPage(Map<String, Object> paramMap);

    int deleteStudent(List<Integer> ids);

    int addStudent(Student student);

    int editStudent(Student student);

    Student login(LoginDTO loginDTO);

    boolean isStudentByClazzId(Integer next);

    int editPswdByStudent(Student student);

    List<Student> getAllBySid(Integer studentId);
}
