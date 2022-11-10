package com.jie.attendance.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jie.attendance.entity.Course;
import com.jie.attendance.entity.SelectedCourse;
import com.jie.attendance.mapper.CourseMapper;
import com.jie.attendance.mapper.SelectedCourseMapper;
import com.jie.attendance.service.SelectedCourseService;
import com.jie.attendance.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SelectedCourseServiceImpl implements SelectedCourseService {

    private final SelectedCourseMapper selectedCourseMapper;
    private final CourseMapper courseMapper;

    public SelectedCourseServiceImpl(SelectedCourseMapper selectedCourseMapper, CourseMapper courseMapper) {
        this.selectedCourseMapper = selectedCourseMapper;
        this.courseMapper = courseMapper;
    }

    @Override
    public PageBean<SelectedCourse> queryPage(Map<String, Object> paramMap) {
        PageBean<SelectedCourse> pageBean = new PageBean<>((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<SelectedCourse> datas = selectedCourseMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = selectedCourseMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addSelectedCourse(SelectedCourse selectedCourse) {
        LambdaQueryWrapper<SelectedCourse> wrapper = new LambdaQueryWrapper<SelectedCourse>()
                .eq(SelectedCourse::getStudentId, selectedCourse.getStudentId())
                .eq(SelectedCourse::getCourseId, selectedCourse.getCourseId());
        SelectedCourse selectOne = selectedCourseMapper.selectOne(wrapper);

        if (Objects.isNull(selectOne)) {
            int count = courseMapper.addStudentNum(selectedCourse.getCourseId());
            if (count == 1) {
                selectedCourseMapper.insert(selectedCourse);
                return count;
            }
            if (count == 0) {
                return count;
            }
        } else {
            return 2;
        }
        return 3;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteSelectedCourse(Integer selectedCourseId) {
        SelectedCourse selectedCourse = selectedCourseMapper.selectById(selectedCourseId);
        if (Objects.isNull(selectedCourse)) {
            return -1;
        }
        // 退课
        return selectedCourseMapper.deleteById(selectedCourseId);
    }

    @Override
    public boolean isStudentId(Integer studentId) {
        LambdaQueryWrapper<SelectedCourse> wrapper = new LambdaQueryWrapper<SelectedCourse>()
                .eq(SelectedCourse::getStudentId, studentId);
        List<SelectedCourse> selectedCourseList = selectedCourseMapper.selectList(wrapper);
        return selectedCourseList.isEmpty();
    }

    @Override
    public List<SelectedCourse> getAllBySid(Integer studentid) {
        LambdaQueryWrapper<SelectedCourse> wrapper = new LambdaQueryWrapper<SelectedCourse>()
                .eq(SelectedCourse::getStudentId, studentid);
        List<SelectedCourse> selectedCourses = selectedCourseMapper.selectList(wrapper);
        System.out.println(selectedCourses.toString());
        return selectedCourseMapper.getAllBySid(studentid);
    }

    @Override
    public Course getCourseDetail(Integer studentId, Integer courseId) {
        return selectedCourseMapper.getCourseDetail(studentId, courseId);
    }
}
