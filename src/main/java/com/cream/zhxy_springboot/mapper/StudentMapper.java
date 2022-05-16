package com.cream.zhxy_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cream.zhxy_springboot.bean.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMapper extends BaseMapper<Student> {
}
