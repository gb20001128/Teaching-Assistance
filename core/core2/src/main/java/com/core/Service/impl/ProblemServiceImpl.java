package com.core.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.core.Service.ProblemService;
import com.core.mapper.ProblemMapper;
import common.domain.Problem;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/23 9:18
 */
@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements ProblemService {


    @Autowired
    ProblemMapper problemMapper;


    public Problem getProblem(@Param("pid") Integer id){
        return problemMapper.getProblem(id);
    }
}
