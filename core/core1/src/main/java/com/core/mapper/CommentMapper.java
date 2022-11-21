package com.core.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import common.domain.Comment;
import common.domain.Problem;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {


    int likeComment(Integer id);
}
