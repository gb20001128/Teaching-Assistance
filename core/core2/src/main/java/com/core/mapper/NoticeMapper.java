package com.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import common.domain.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/20 14:58
 */
@Repository
public interface NoticeMapper  extends BaseMapper<Notice> {

    List<Notice> selectAll();

    int updateNotice(String id);
}
