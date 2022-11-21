package com.login.service.impl;

import com.login.mapper.TeacherMapper;
import com.login.service.LoginService;
import com.login.service.TeacherService;
import common.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/19 9:21
 */

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    TeacherService teacherService;


    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public String Login(String teacherName,String passWord) {

        Map<String, Object> map = new HashMap<>();
        map.put("account_number", teacherName);
        map.put("pass_word", passWord);
        List<Teacher> teachers = teacherMapper.selectByMap(map);

        if (teachers.size()==0)
            return null;
        Teacher teacher = teachers.get(0);
        String name = teacher.getTeacherName();
        return name;

    }


}
