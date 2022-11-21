package com.login.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.login.myhandler.BlockHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/21 10:51
 */

@Controller
public class CourseController {


    @RequestMapping("/course")
    @SentinelResource(value = "test20",
            blockHandlerClass = BlockHandler.class,
            blockHandler = "blockHandler1",
            fallback = "handlerFallback")
    public String course(){
        return "course";
    }
}
