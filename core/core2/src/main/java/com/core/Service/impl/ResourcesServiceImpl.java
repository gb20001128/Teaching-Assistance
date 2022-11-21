package com.core.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.core.Service.ResourcesService;
import com.core.mapper.ResourcesMapper;
import common.domain.Resources;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/22 12:21
 */

@Service
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements ResourcesService {


}
