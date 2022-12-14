package com.jie.attendance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jie.attendance.entity.Clazz;

import java.util.List;
import java.util.Map;

public interface ClazzMapper extends BaseMapper<Clazz> {
    List<Clazz> queryList(Map<String, Object> paramMap);
    Integer queryCount(Map<String, Object> paramMap);
}
