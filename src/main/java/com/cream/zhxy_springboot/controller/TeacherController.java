package com.cream.zhxy_springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cream.zhxy_springboot.bean.Teacher;
import com.cream.zhxy_springboot.service.TeacherService;
import com.cream.zhxy_springboot.util.MD5;
import com.cream.zhxy_springboot.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getTeachers/{pageNo}/{pageSize}")
    public Result getTeachers(@PathVariable("pageNo") Integer pageNo,
                              @PathVariable("pageSize") Integer pageSize,
                              String clazzName,
                              String name) {
        Page<Teacher> page = new Page<>(pageNo, pageSize);
        IPage<Teacher> pages = teacherService.getTeacherByOpr(page, clazzName, name);
        return Result.ok(pages);
    }

    @PostMapping("/saveOrUpdateTeacher")
    public Result saveOrUpdateTeacher(@RequestBody Teacher teacher) {
        if (teacher.getId() == null)
            teacher.setPassword(MD5.encrypt(teacher.getPassword()));
        teacherService.saveOrUpdate(teacher);
        return Result.ok();
    }

    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacher(@RequestBody List<Integer> ids) {
        teacherService.removeByIds(ids);
        return Result.ok();
    }
}
