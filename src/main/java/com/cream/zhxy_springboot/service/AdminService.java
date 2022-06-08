package com.cream.zhxy_springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cream.zhxy_springboot.bean.Admin;
import com.cream.zhxy_springboot.bean.LoginForm;

public interface AdminService extends IService<Admin> {
    Admin login(LoginForm loginForm);

    Admin getInfoById(Long userId);

    IPage<Admin> getAdminByOpr(Page<Admin> page, String name);
}
