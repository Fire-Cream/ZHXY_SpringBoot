package com.cream.zhxy_springboot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cream.zhxy_springboot.bean.Grade;
import com.cream.zhxy_springboot.service.GradeService;
import com.cream.zhxy_springboot.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "班级控制器")
@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    /**
     * 分页查询
     *
     * @Return com.cream.zhxy_springboot.util.Result
     */
    @ApiOperation("根据班级名称模糊查询，带分页")
    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGrades(@PathVariable("pageNo") Integer pageNo,
                            @PathVariable("pageSize") Integer pageSize,
                            String gradeName) {
        //分页    待条件查询
        Page<Grade> page = new Page<>(pageNo, pageSize);
        //通过服务层进行查询
        IPage<Grade> pages = gradeService.getGradeByOpr(page, gradeName);

        //封装result对象返回
        return Result.ok(page);
    }

    /**
     * 增加或修改
     *
     * @Return com.cream.zhxy_springboot.util.Result
     */
    @ApiOperation("增加或修改Grade")
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(@ApiParam("json的Grade对象，根据有无id属性进行增加或修改") @RequestBody Grade grade) {
        //接收参数
        //调用方法完成增加或者修改
        gradeService.saveOrUpdate(grade);
        return Result.ok();
    }

    /**
     * 删除
     *
     * @Return com.cream.zhxy_springboot.util.Result
     */
    @ApiOperation("删除Grade信息")
    @DeleteMapping("/deleteGrade")
    public Result deleteGrade(@ApiParam("要删除的Grade的id的json集合") @RequestBody List<Integer> ids) {
        gradeService.removeByIds(ids);
        return Result.ok();
    }

    /**
     * 获取Grade信息
     *
     * @Return com.cream.zhxy_springboot.util.Result
     */
    @ApiOperation("获取Grade信息")
    @GetMapping("/getGrades")
    public Result getGrades() {
        List<Grade> grades = gradeService.getGreades();
        return Result.ok(grades);
    }
}
