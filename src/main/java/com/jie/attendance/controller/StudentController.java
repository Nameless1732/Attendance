package com.jie.attendance.controller;

import com.jie.attendance.constants.UserConstant;
import com.jie.attendance.entity.Student;
import com.jie.attendance.service.SelectedCourseService;
import com.jie.attendance.service.StudentService;
import com.jie.attendance.utils.Data;
import com.jie.attendance.utils.PageBean;
import com.jie.attendance.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final SelectedCourseService selectedCourseService;

    public StudentController(StudentService studentService, SelectedCourseService selectedCourseService) {
        this.studentService = studentService;
        this.selectedCourseService = selectedCourseService;
    }

    /**
     * 跳转学生列表页面
     */
    @GetMapping("/student_list")
    public String studentList() {
        return "/student/studentList";
    }

    /**
     * 异步加载学生列表
     */
    @RequestMapping("/getStudentList")
    @ResponseBody
    public Object getStudentList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                 @RequestParam(value = "clazzid", defaultValue = "0") String clazzid,
                                 String studentName, String from, HttpSession session) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);
        if (!StringUtils.isEmpty(studentName)) {
            paramMap.put("username", studentName);
        }
        if (!"0".equals(clazzid)) {
            paramMap.put("clazzid", clazzid);
        }
        //判断是老师还是学生权限
        Student student = (Student) session.getAttribute(UserConstant.STUDENT);
        if (!StringUtils.isEmpty(student)) {
            //是学生权限，只能查询自己的信息
            paramMap.put("studentid", student.getId());
        }
        PageBean<Student> pageBean = studentService.queryPage(paramMap);
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
     * 删除学生
     */
    @PostMapping("/deleteStudent")
    @ResponseBody
    public R<Boolean> deleteStudent(Data data) {
        List<Integer> ids = data.getIds();
        // 判断是否存在课程关联学生
        for (Integer id : ids) {
            if (!selectedCourseService.isStudentId(id)) {
                return R.fail("无法删除,存在课程关联学生");
            }
        }
        int count = studentService.deleteStudent(ids);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }


    /**
     * 添加学生
     */
    @RequestMapping("/addStudent")
    @ResponseBody
    public R<Boolean> addStudent(Student student) {
        int count = studentService.addStudent(student);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    /**
     * 修改学生信息
     */
    @PutMapping("/editStudent")
    @ResponseBody
    public R<Boolean> editStudent(Student student) {
        int i = studentService.editStudent(student);
        if (i > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }
}
