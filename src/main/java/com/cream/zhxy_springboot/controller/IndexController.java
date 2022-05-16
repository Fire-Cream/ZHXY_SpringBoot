package com.cream.zhxy_springboot.controller;

import com.cream.zhxy_springboot.bean.Admin;
import com.cream.zhxy_springboot.bean.LoginForm;
import com.cream.zhxy_springboot.bean.Student;
import com.cream.zhxy_springboot.bean.Teacher;
import com.cream.zhxy_springboot.service.AdminService;
import com.cream.zhxy_springboot.service.StudentService;
import com.cream.zhxy_springboot.service.TeacherService;
import com.cream.zhxy_springboot.util.CreateVerifiCodeImage;
import com.cream.zhxy_springboot.util.JwtHelper;
import com.cream.zhxy_springboot.util.Result;
import com.cream.zhxy_springboot.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/sms/system")
public class IndexController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    /**
     * 获取验证码
     *
     * @Return com.cream.zhxy_springboot.util.Result
     */
    @GetMapping("/getVerifiCodeImage")
    public void getVerifiCodeImage(HttpServletRequest request, HttpServletResponse response) {
        //获取图片
        BufferedImage verifiCodeImage = CreateVerifiCodeImage.getVerifiCodeImage();
        //获取验证码
        String verificode = new String(CreateVerifiCodeImage.getVerifiCode());
        //将验证码保存到session
        HttpSession session = request.getSession();
        session.setAttribute("verificode", verificode);
        //将图片响应给浏览器
        try {
            ImageIO.write(verifiCodeImage, "JPEG", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     *
     * @Return com.cream.zhxy_springboot.util.Result
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        System.out.println(loginForm);
        //检验验证码
        HttpSession session = request.getSession();
        String sessionverificode = (String) session.getAttribute("verificode");
        String loginverificode = loginForm.getVerifiCode();
        //防止验证码过期
        if ("".equals(sessionverificode) || null == sessionverificode)
            return Result.fail().message("验证码失效，请刷新后重试");
        //判断验证码是否相等
        if (!sessionverificode.equalsIgnoreCase(loginverificode))
            return Result.fail().message("验证码错误，请刷新后重试");
        //验证码使用过了，所以移除现有验证码
        session.removeAttribute("verificode");
        //装备一个map用于存放响应数据
        Map<String, Object> map = new LinkedHashMap<>();
        switch (loginForm.getUserType()) {
            case 1:
                try {
                    Admin admin = adminService.login(loginForm);
                    if (admin != null) {
                        //用户的类型和用户id转换成一个密文，以token的名称向客户端反馈
                        String token = JwtHelper.createToken(admin.getId().longValue(), 1);
                        map.put("token", token);
                    } else {
                        throw new RuntimeException("用户名或者密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 2:
                try {
                    Student student = studentService.login(loginForm);
                    if (student != null) {
                        //用户的类型和用户id转换成一个密文，以token的名称向客户端反馈
                        String token = JwtHelper.createToken(student.getId().longValue(), 2);
                        map.put("token", token);
                    } else {
                        throw new RuntimeException("用户名或者密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
            case 3:
                try {
                    Teacher teacher = teacherService.login(loginForm);
                    if (teacher != null) {
                        //用户的类型和用户id转换成一个密文，以token的名称向客户端反馈
                        String token = JwtHelper.createToken(teacher.getId().longValue(), 3);
                        map.put("token", token);
                    } else {
                        throw new RuntimeException("用户名或者密码有误");
                    }
                    return Result.ok(map);
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    return Result.fail().message(e.getMessage());
                }
        }
        //用户类型验证
        return Result.fail().message("查无此用户");
    }

    /**
     * 获取用户信息
     *
     * @Return com.cream.zhxy_springboot.util.Result
     */
    @GetMapping("/getInfo")
    public Result getInfo(@RequestHeader("token") String token) {
        boolean expiration = JwtHelper.isExpiration(token);
        if (expiration)
            return Result.build(null, ResultCodeEnum.TOKEN_ERROR);
        //从token中解析出 用户id 和用户的类型
        Long userId = JwtHelper.getUserId(token);
        Integer userType = JwtHelper.getUserType(token);

        //装备一个map用于存放响应数据
        Map<String, Object> map = new LinkedHashMap<>();
        switch (userType) {
            case 1:
                Admin admin = adminService.getInfoById(userId);
                map.put("userType", 1);
                map.put("user", admin);
                break;
            case 2:
                Student student = studentService.getInfoById(userId);
                map.put("userType", 2);
                map.put("user", student);
                break;
            case 3:
                Teacher teacher = teacherService.getInfoById(userId);
                map.put("userType", 3);
                map.put("user", teacher);
                break;
        }
        return Result.ok(map);
    }
}
