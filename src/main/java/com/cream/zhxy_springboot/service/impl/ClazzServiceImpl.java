package com.cream.zhxy_springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cream.zhxy_springboot.bean.Clazz;
import com.cream.zhxy_springboot.mapper.ClazzMapper;
import com.cream.zhxy_springboot.service.ClazzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("clazzServiceImpl")
@Transactional
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {

    @Override
    public IPage<Clazz> getClazzByOpr(Page<Clazz> pageParam, String gradeName, String name) {
        QueryWrapper<Clazz> queryWrapper = new QueryWrapper<>();
        if (gradeName != null)
            queryWrapper.eq("grade_name", gradeName);
        if (name != null)
            queryWrapper.eq("name", name);
        Page<Clazz> page = baseMapper.selectPage(pageParam, queryWrapper);
        return page;
    }

    @Override
    public List<Clazz> getClazzs() {
        return baseMapper.selectList(null);
    }
}
