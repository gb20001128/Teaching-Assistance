package com.core.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.core.Service.GradeService;
import com.core.mapper.GradeMapper;
import common.domain.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/21 15:11
 */

@RestController
public class GradeController {


    @Autowired
    GradeService gradeService;

    @Autowired
    GradeMapper gradeMapper;

    @RequestMapping("/getGrades")
    public List<Grade> getGrades(){


        QueryWrapper<Grade> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("count_grade");
        List<Grade> grades = gradeMapper.selectList(queryWrapper);

        return grades;
    }


    @RequestMapping("/getGradeById/{id}")
    public Grade getGradeById(@PathVariable("id") Integer id){

        Grade grade = gradeService.getById(id);

        return grade;
    }


    //像这种微服务之间调用参数是对象的,应该用@RequestBody接收,好像一般的还不行
    @RequestMapping("/updateThisGrade")
    public int updateThisGrade(@RequestBody Grade grade){


        int i = gradeMapper.updateCount(grade);

        return i;
    }


}
