package com.login.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.login.mapper.TeacherMapper;
import com.login.service.TeacherService;
import common.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/19 9:35
 */

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService{


    @Autowired
    TeacherMapper teacherMapper;


    @Override
    public Teacher selectTeacherName(String teacherName, String passWord) {
        return teacherMapper.selectTeacherName(teacherName,passWord);
    }
}
