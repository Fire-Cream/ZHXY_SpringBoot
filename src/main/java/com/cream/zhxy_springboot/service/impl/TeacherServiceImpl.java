package com.cream.zhxy_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cream.zhxy_springboot.bean.LoginForm;
import com.cream.zhxy_springboot.bean.Student;
import com.cream.zhxy_springboot.bean.Teacher;
import com.cream.zhxy_springboot.mapper.TeacherMapper;
import com.cream.zhxy_springboot.service.TeacherService;
import com.cream.zhxy_springboot.util.MD5;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("teacherServiceImpl")
@Transactional
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Override
    public Teacher login(LoginForm loginForm) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", loginForm.getUsername());
        queryWrapper.eq("password", MD5.encrypt(loginForm.getPassword()));
        Teacher teacher = baseMapper.selectOne(queryWrapper);
        return teacher;
    }

    @Override
    public Teacher getInfoById(Long userId) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        Teacher teacher = baseMapper.selectOne(queryWrapper);
        return teacher;
    }
}
