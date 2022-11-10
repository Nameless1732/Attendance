package com.jie.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jie.attendance.entity.Student;

import java.util.List;
import java.util.Map;

public interface StudentMapper extends BaseMapper<Student> {
    List<Student> queryList(Map<String, Object> paramMap);
    Integer queryCount(Map<String, Object> paramMap);
    List<Student> isStudentByClazzId(Integer id);
    List<Student> getAllBySid(Integer studentid);
}
