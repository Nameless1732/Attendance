package com.jie.attendance.service;

import com.jie.attendance.entity.Clazz;
import com.jie.attendance.utils.PageBean;

import java.util.List;
import java.util.Map;

public interface ClazzService {

    PageBean<Clazz> queryPage(Map<String, Object> paramMap);

    int addClazz(Clazz clazz);

    int deleteClazz(List<Integer> ids);

    int editClazz(Clazz clazz);

    List<Clazz> getClazzById(List<Integer> ids);
}
