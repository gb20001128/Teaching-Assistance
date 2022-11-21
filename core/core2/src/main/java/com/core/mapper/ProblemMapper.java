package com.core.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import common.domain.Problem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemMapper   extends BaseMapper<Problem> {



    Problem getProblem(@Param("pid") Integer id);
}
