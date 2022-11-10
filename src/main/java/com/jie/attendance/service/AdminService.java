package com.jie.attendance.service;

import com.jie.attendance.dto.LoginDTO;
import com.jie.attendance.entity.Admin;

public interface AdminService {

    /**
     * 管理员登录
     * @param loginDTO 登录参数
     * @return 管理员信息
     */
    Admin login(LoginDTO loginDTO);

    /**
     * 修改密码
     * @param admin 修改参数
     * @return 影响条数
     */
    int editPswdByAdmin(Admin admin);
}
