package com.jie.attendance.controller;

import com.jie.attendance.entity.Teacher;
import com.jie.attendance.service.TeacherService;
import com.jie.attendance.utils.Data;
import com.jie.attendance.utils.PageBean;
import com.jie.attendance.utils.R;
import com.jie.attendance.constants.UserConstant;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @RequestMapping("/teacher_list")
    public String teacherList() {
        return "/teacher/teacherList";
    }

    /**
     * 异步加载老师数据列表
     */
    @PostMapping("/getTeacherList")
    @ResponseBody
    public Object getTeacherList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                 @RequestParam(value = "clazzid", defaultValue = "0") String clazzid,
                                 String teacherName, String from, HttpSession session) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);
        if (!StringUtils.isEmpty(teacherName)) {
            paramMap.put("username", teacherName);
        }
        if (!"0".equals(clazzid)) {
            paramMap.put("clazzid", clazzid);
        }
        //判断是老师还是学生权限
        Teacher teacher = (Teacher) session.getAttribute(UserConstant.TEACHER);
        if (!StringUtils.isEmpty(teacher)) {
            //是老师权限，只能查询自己的信息
            paramMap.put("teacherid", teacher.getId());
        }
        PageBean<Teacher> pageBean = teacherService.queryPage(paramMap);
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
     * 删除教师
     */
    @PostMapping("/deleteTeacher")
    @ResponseBody
    public R<Boolean> deleteTeacher(Data data) {
        int count = teacherService.deleteTeacher(data.getIds());
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    /**
     * 添加教师
     */
    @RequestMapping("/addTeacher")
    @ResponseBody
    public R<Boolean> addTeacher(Teacher teacher) {
        int count = teacherService.addTeacher(teacher);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    @PostMapping("/editTeacher")
    @ResponseBody
    public R<Boolean> editTeacher(Teacher teacher) {
        int count = teacherService.editTeacher(teacher);
        System.out.println(count);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }
}
