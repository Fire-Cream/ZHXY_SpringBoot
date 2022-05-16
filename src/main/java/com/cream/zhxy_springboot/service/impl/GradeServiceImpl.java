package com.cream.zhxy_springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cream.zhxy_springboot.bean.Grade;
import com.cream.zhxy_springboot.mapper.GradeMapper;
import com.cream.zhxy_springboot.service.GradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("gradeServiceImpl")
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {
}
