package com.core.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.core.Service.NoticeService;
import com.core.mapper.NoticeMapper;
import common.domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/20 15:07
 */
@Service
@CacheConfig(cacheNames="notice")//抽取缓存的公共配置,比如cacheNames="notice"就等于此类所以的缓存组件名都是notice
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper,Notice> implements NoticeService {


    @Autowired
    NoticeMapper noticeMapper;


    @Cacheable( key="#root.methodName")//key是方法名
     public List<Notice> selectAll(){
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("time");

        return noticeMapper.selectList(queryWrapper);
    }


    @CachePut( key="'selectAll'")//key
    public List<Notice> updateNotice(String id){
        noticeMapper.updateNotice(id);
        return selectAll();
    }



    @CachePut( key="'selectAll'")//key
    public List<Notice> saveNotice(Notice entity) {

         super.save(entity);
         return selectAll();
    }

    @CacheEvict(value = "notice",allEntries = true)
    public boolean deleteById(String id){
        int i = noticeMapper.deleteById(id);
        if (i>0)
        return true;
        return false;
    }

}
