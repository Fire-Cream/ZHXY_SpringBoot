package com.cream.zhxy_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cream.zhxy_springboot.bean.LoginForm;
import com.cream.zhxy_springboot.bean.Student;

public interface StudentService extends IService<Student> {
    Student login(LoginForm loginForm);

    Student getInfoById(Long userId);
}
