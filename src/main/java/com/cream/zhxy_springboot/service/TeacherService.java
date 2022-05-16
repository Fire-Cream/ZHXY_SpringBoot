package com.cream.zhxy_springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cream.zhxy_springboot.bean.LoginForm;
import com.cream.zhxy_springboot.bean.Teacher;

public interface TeacherService extends IService<Teacher> {
    Teacher login(LoginForm loginForm);

    Teacher getInfoById(Long userId);
}
