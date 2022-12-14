package com.jie.attendance.interceptors;

import com.jie.attendance.entity.Admin;
import com.jie.attendance.entity.Student;
import com.jie.attendance.entity.Teacher;
import com.jie.attendance.constants.UserConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 访问某个页面之前，从 session 对象中拿到对应的登录用户信息，若无则跳转到登录页面
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        Admin user = (Admin) request.getSession().getAttribute(UserConstant.ADMIN);
        Teacher teacher = (Teacher) request.getSession().getAttribute(UserConstant.TEACHER);
        Student student = (Student) request.getSession().getAttribute(UserConstant.STUDENT);

        if (Objects.nonNull(user) || Objects.nonNull(teacher) || Objects.nonNull(student)) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath() + "/system/login");
            return false;
        }
    }

}
