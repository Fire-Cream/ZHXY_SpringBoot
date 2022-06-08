package com.cream.zhxy_springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cream.zhxy_springboot.bean.Clazz;
import com.cream.zhxy_springboot.service.ClazzService;
import com.cream.zhxy_springboot.util.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "班级控制类")
@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping("/getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzsByOpr(@PathVariable("pageNo") Integer pageNo,
                                 @PathVariable("pageSize") Integer pageSize,
                                 String gradeName,
                                 String name) {
        //分页待条件查询
        Page<Clazz> page = new Page<>(pageNo, pageSize);
        //通过服务层进行查询
        IPage<Clazz> pages = clazzService.getClazzByOpr(page, gradeName, name);
        return Result.ok(pages);
    }

    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(@RequestBody Clazz clazz) {
        clazzService.saveOrUpdate(clazz);
        return Result.ok();
    }

    @DeleteMapping("/deleteClazz")
    public Result deleteClazz(@RequestBody List<Integer> ids) {
        clazzService.removeByIds(ids);
        return Result.ok();
    }

    @GetMapping("/getClazzs")
    public Result getClazzs() {
        List<Clazz> clazzs = clazzService.getClazzs();
        return Result.ok(clazzs);
    }
}
