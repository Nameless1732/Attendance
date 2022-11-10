package com.jie.attendance.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jie.attendance.dto.LoginDTO;
import com.jie.attendance.entity.Admin;
import com.jie.attendance.mapper.AdminMapper;
import com.jie.attendance.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    /**
     * 管理员登录
     * @param loginDTO 登录参数
     * @return 管理员信息
     */
    @Override
    public Admin login(LoginDTO loginDTO) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<Admin>()
                .eq(Admin::getUsername, loginDTO.getUsername())
                .eq(Admin::getPassword, loginDTO.getPassword());
        return adminMapper.selectOne(wrapper);
    }

    /**
     * 修改密码
     * @param admin 修改参数
     * @return 影响条数
     */
    @Override
    public int editPswdByAdmin(Admin admin) {
        return adminMapper.updateById(admin);
    }

}
