package com.jie.attendance.controller;

import com.jie.attendance.constants.UserConstant;
import com.jie.attendance.entity.SelectedCourse;
import com.jie.attendance.entity.Student;
import com.jie.attendance.service.SelectedCourseService;
import com.jie.attendance.utils.R;
import com.jie.attendance.utils.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/selectedCourse")
public class SelectedCourseController {

    private final SelectedCourseService selectedCourseService;

    public SelectedCourseController(SelectedCourseService selectedCourseService) {
        this.selectedCourseService = selectedCourseService;
    }


    @GetMapping("/selectedCourse_list")
    public String selectedCourseList() {
        return "course/selectedCourseList";
    }

    /**
     * 异步加载选课信息列表
     */
    @PostMapping("/getSelectedCourseList")
    @ResponseBody
    public Object getClazzList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                               @RequestParam(value = "studentid", defaultValue = "0") String studentid,
                               @RequestParam(value = "courseid", defaultValue = "0") String courseid,
                               String from, HttpSession session) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);
        if (!"0".equals(studentid)) {
            paramMap.put("studentid", studentid);
        }
        if (!"0".equals(courseid)) {
            paramMap.put("courseid", courseid);
        }
        //判断是老师还是学生权限
        Student student = (Student) session.getAttribute(UserConstant.STUDENT);
        if (!StringUtils.isEmpty(student)) {
            //是学生权限，只能查询自己的信息
            paramMap.put("studentid", student.getId());
        }
        PageBean<SelectedCourse> pageBean = selectedCourseService.queryPage(paramMap);
        if (!StringUtils.isEmpty(from) && "combox".equals(from)) {
            return pageBean.getDatas();
        } else {
            Map<String, Object> result = new HashMap<>();
            result.put("total", pageBean.getTotalsize());
            result.put("rows", pageBean.getDatas());
            return result;
        }
    }

    /**
     * 学生进行选课
     */
    @PostMapping("/addSelectedCourse")
    @ResponseBody
    public R<Boolean> addSelectedCourse(SelectedCourse selectedCourse) {
        int count = selectedCourseService.addSelectedCourse(selectedCourse);
        if (count == 1) {
            return R.success();
        } else if (count == 0) {
            return R.fail("选课人数已满");
        } else if (count == 2) {
            return R.fail("已选过这门课程");
        }
        return R.fail("系统繁忙");
    }


    /**
     * 删除选课信息
     */
    @PostMapping("/deleteSelectedCourse")
    @ResponseBody
    public R<Boolean> deleteSelectedCourse(Integer id) {
        int count = selectedCourseService.deleteSelectedCourse(id);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }
}
