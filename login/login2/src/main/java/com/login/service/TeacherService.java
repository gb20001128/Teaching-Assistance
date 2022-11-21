package com.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import common.domain.Teacher;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/19 9:33
 */
public interface TeacherService extends IService<Teacher> {


    Teacher selectTeacherName(String teacherName,String passWord);
}
