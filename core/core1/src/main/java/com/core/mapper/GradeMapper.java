package com.core.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import common.domain.Grade;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeMapper extends BaseMapper<Grade> {


    Integer updateCount(Grade grade);

}
