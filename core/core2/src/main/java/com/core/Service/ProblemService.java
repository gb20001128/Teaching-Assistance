package com.core.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import common.domain.Problem;
import org.apache.ibatis.annotations.Param;

public interface ProblemService extends IService<Problem> {

    Problem getProblem(@Param("pid") Integer id);
}
