package com.jie.attendance.controller;

import com.jie.attendance.constants.UserConstant;
import com.jie.attendance.entity.*;
import com.jie.attendance.service.*;
import com.jie.attendance.utils.PageBean;
import com.jie.attendance.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final SelectedCourseService selectedCourseService;
    private final ClazzService clazzService;
    private final CourseService courseService;
    private final StudentService studentService;

    public AttendanceController(AttendanceService attendanceService, SelectedCourseService selectedCourseService, ClazzService clazzService, CourseService courseService, StudentService studentService) {
        this.attendanceService = attendanceService;
        this.selectedCourseService = selectedCourseService;
        this.clazzService = clazzService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping("/attendance_list")
    public String attendanceList() {
        return "/attendance/attendanceList";
    }

    /**
     * 异步获取考勤列表数据
     */
    @RequestMapping("/getAttendanceList")
    @ResponseBody
    public Object getAttendanceList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "rows", defaultValue = "100") Integer rows,
                                    @RequestParam(value = "studentid", defaultValue = "0") String studentid,
                                    @RequestParam(value = "clazzid", defaultValue = "0") String clazzid,
                                    @RequestParam(value = "courseid", defaultValue = "0") String courseid,
                                    String type, String date, String from, HttpSession session) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageno", page);
        paramMap.put("pagesize", rows);
        if (!"0".equals(studentid)) {
            paramMap.put("studentid", studentid);
        }
        if (!"0".equals(courseid)) {
            paramMap.put("courseid", courseid);
        }
        if (!"0".equals(clazzid)) {
            paramMap.put("clazzid", clazzid);
        }
        if (!StringUtils.isEmpty(type)) {
            paramMap.put("type", type);
        }
        if (!StringUtils.isEmpty(date)) {
            paramMap.put("date", date);
        }

        Student student = (Student) session.getAttribute(UserConstant.STUDENT);
        if (!StringUtils.isEmpty(student)) {
            // 学生用户，只能查询自己的信息
            paramMap.put("studentid", student.getId());
        }

        PageBean<Attendance> pageBean = attendanceService.queryPage(paramMap);
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
     * 通过选课中的课程id查询学生所选择的课程
     */
    @RequestMapping("/getStudentSelectedCourseList")
    @ResponseBody
    public Object getStudentSelectedCourseList(@RequestParam(value = "studentid", defaultValue = "0") String studentid) {
        // 通过学生id查询选课信息
        List<SelectedCourse> selectedCourseList = selectedCourseService.getAllBySid(Integer.valueOf(studentid));
        // 通过选课中的课程id查询学生所选择的课程
        List<Integer> ids = new ArrayList<>();
        for (SelectedCourse selectedCourse : selectedCourseList) {
            ids.add(selectedCourse.getCourseId());
        }
        // 学生选课列表
        List<Course> courseById = courseService.getCourseById(ids);
        if (CollectionUtils.isEmpty(courseById)) {
            return R.fail("该学生未进行选课");
        }
        return courseById;
    }

    /**
     * 通过选课中的课程id查询学生所选择的班级
     */
    @RequestMapping("/getStudentSelectedClazzList")
    @ResponseBody
    public Object getStudentSelectedClazzList(@RequestParam(value = "studentid", defaultValue = "0") String studentid) {
        // 通过学生id查询班级信息
        List<Student> studentList = studentService.getAllBySid(Integer.valueOf(studentid));
        List<Integer> ids = new ArrayList<>();
        for (Student student : studentList) {
            ids.add(student.getClazzId());
        }
        // 学生班级列表
        List<Clazz> clazzById = clazzService.getClazzById(ids);
        if (CollectionUtils.isEmpty(clazzById)) {
            return R.fail("该学生未进行选课");
        }
        return clazzById;
    }


    /**
     * 添加考勤签到
     */
    @PostMapping("/addAttendance")
    @ResponseBody
    public R<Boolean> addAttendance(Attendance attendance) {
        // 判断是否已签到
        boolean checkAttendance = attendanceService.checkAttendance(attendance);
        if (checkAttendance) {
            // true 为已签到
            return R.fail("已签到，请勿重复签到");
        } else {
            Course course = selectedCourseService.getCourseDetail(attendance.getStudentId(), attendance.getCourseId());

            // 保存签到信息
            int count = attendanceService.addAttendance(attendance);
            if (count > 0) {
                return R.success();
            }
            return R.fail();
        }
    }

    /**
     * 删除
     */
    @PostMapping("/deleteAttendance")
    @ResponseBody
    public R<Boolean> deleteAttendance(Integer id) {
        int count = attendanceService.deleteAttendance(id);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    /**
     * 编辑
     */
    @PostMapping("/edit_1")
    @ResponseBody
    public R<Boolean> edit_1(Integer id) {
        int count = attendanceService.edit_1(id);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    @PostMapping("/edit_2")
    @ResponseBody
    public R<Boolean> edit_2(Integer id) {
        int count = attendanceService.edit_2(id);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    @PostMapping("/edit_3")
    @ResponseBody
    public R<Boolean> edit_3(Integer id) {
        int count = attendanceService.edit_3(id);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    @PostMapping("/edit_4")
    @ResponseBody
    public R<Boolean> edit_4(Integer id) {
        int count = attendanceService.edit_4(id);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }

    @PostMapping("/edit_5")
    @ResponseBody
    public R<Boolean> edit_5(Integer id) {
        int count = attendanceService.edit_5(id);
        if (count > 0) {
            return R.success();
        } else {
            return R.fail();
        }
    }

}
