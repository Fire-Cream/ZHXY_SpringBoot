package com.cream.zhxy_springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cream.zhxy_springboot.bean.Admin;
import com.cream.zhxy_springboot.bean.Student;
import com.cream.zhxy_springboot.service.StudentService;
import com.cream.zhxy_springboot.util.MD5;
import com.cream.zhxy_springboot.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms/studentController")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/a")
    public String a() {
        return "aaaa";
    }

    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentByOpr(@PathVariable("pageNo") Integer pageNo,
                                  @PathVariable("pageSize") Integer pageSize,
                                  String clazzName,
                                  String name) {
        Page<Student> page = new Page<>(pageNo, pageSize);
        IPage<Student> pages = studentService.getStudentByOpr(page, clazzName, name);
        return Result.ok(pages);
    }

    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(@RequestBody Student student) {
        if (student.getId() == null)
            student.setPassword(MD5.encrypt(student.getPassword()));
        studentService.saveOrUpdate(student);
        return Result.ok();
    }

    @DeleteMapping("/delStudentById")
    public Result delStudentById(@RequestBody List<Integer> ids) {
        studentService.removeByIds(ids);
        return Result.ok();
    }

}
