package com.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import common.domain.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/19 9:25
 */
@Repository
public interface TeacherMapper extends BaseMapper<Teacher> {

    Teacher selectTeacher(Integer id);

    Teacher selectTeacherName(@Param("teacherName") String teacherName,@Param("passWord")  String passWord);
}
