package com.jie.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jie.attendance.entity.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherMapper extends BaseMapper<Teacher> {
    List<Teacher> queryList(Map<String, Object> paramMap);
    Integer queryCount(Map<String, Object> paramMap);
}
