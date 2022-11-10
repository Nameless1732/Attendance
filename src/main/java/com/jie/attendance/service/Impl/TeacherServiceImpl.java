package com.jie.attendance.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jie.attendance.dto.LoginDTO;
import com.jie.attendance.entity.Teacher;
import com.jie.attendance.mapper.TeacherMapper;
import com.jie.attendance.service.TeacherService;
import com.jie.attendance.utils.PageBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherMapper teacherMapper;

    public TeacherServiceImpl(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }

    @Override
    public PageBean<Teacher> queryPage(Map<String, Object> paramMap) {
        PageBean<Teacher> pageBean = new PageBean<>((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<Teacher> datas = teacherMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = teacherMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public int addTeacher(Teacher teacher) {
        return teacherMapper.insert(teacher);
    }

    @Override
    public int deleteTeacher(List<Integer> ids) {
        return teacherMapper.deleteBatchIds(ids);
    }

    @Override
    public int editTeacher(Teacher teacher) {
        return teacherMapper.updateById(teacher);
    }

    @Override
    public Teacher login(LoginDTO loginDTO) {
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<Teacher>()
                .eq(Teacher::getUsername, loginDTO.getUsername())
                .eq(Teacher::getPassword, loginDTO.getPassword());
        return teacherMapper.selectOne(wrapper);
    }

    @Override
    public int editPswdByTeacher(Teacher teacher) {
        return teacherMapper.updateById(teacher);
    }
}
