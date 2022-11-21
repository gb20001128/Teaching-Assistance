package com.core.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import common.domain.Notice;

import java.util.List;


/**
 * @Description
 * @Author gb
 * @Data 2022/7/20 15:01
 */
public interface NoticeService extends IService<Notice> {

    List<Notice> selectAll();

    List<Notice> updateNotice(String id);

     List<Notice> saveNotice(Notice entity);

    boolean deleteById(String id);
}
