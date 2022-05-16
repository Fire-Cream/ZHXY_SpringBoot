package com.cream.zhxy_springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cream.zhxy_springboot.bean.Clazz;
import com.cream.zhxy_springboot.mapper.ClazzMapper;
import com.cream.zhxy_springboot.service.ClazzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("clazzServiceImpl")
@Transactional
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {
}
