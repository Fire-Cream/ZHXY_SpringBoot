package com.cream.zhxy_springboot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cream.zhxy_springboot.bean.LoginForm;
import com.cream.zhxy_springboot.bean.Student;

public interface StudentService extends IService<Student> {
    Student login(LoginForm loginForm);

    Student getInfoById(Long userId);

    IPage<Student> getStudentByOpr(Page<Student> page, String clazzName, String name);
}
