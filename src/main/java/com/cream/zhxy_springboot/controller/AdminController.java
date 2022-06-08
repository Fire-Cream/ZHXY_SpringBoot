package com.cream.zhxy_springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cream.zhxy_springboot.bean.Admin;
import com.cream.zhxy_springboot.bean.Clazz;
import com.cream.zhxy_springboot.service.AdminService;
import com.cream.zhxy_springboot.util.MD5;
import com.cream.zhxy_springboot.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms/adminController")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/getAllAdmin/{pageNo}/{pageSize}")
    public Result getAllAdmin(@PathVariable("pageNo") Integer pageNo,
                              @PathVariable("pageSize") Integer pageSize,
                              String name) {
        Page<Admin> page = new Page<>(pageNo, pageSize);
        IPage<Admin> pages = adminService.getAdminByOpr(page, name);
        return Result.ok(pages);
    }

    @PostMapping("/saveOrUpdateAdmin")
    public Result saveOrUpdateAdmin(@RequestBody Admin admin) {
        if (admin.getId() == null)
            admin.setPassword(MD5.encrypt(admin.getPassword()));
        adminService.saveOrUpdate(admin);
        return Result.ok();
    }

    @DeleteMapping("/deleteAdmin")
    public Result deleteAdmin(@RequestBody List<Integer> ids) {
        adminService.removeByIds(ids);
        return Result.ok();
    }
}
