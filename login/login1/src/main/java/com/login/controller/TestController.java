package com.login.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.login.myhandler.BlockHandler;
import com.login.service.TeacherService;
import common.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/19 12:21
 */

@Controller
public class TestController {


    @Value("${server.port}")
    private String serverPort;


    @Autowired
    TeacherService teacherService;



    @ResponseBody
    @GetMapping("/test")
    public String test(){
        return serverPort;
    }

    @GetMapping("/test1")
    public String test1(){
        return "test";
    }


    @ResponseBody
    @GetMapping("/test3")
    @SentinelResource(value = "test3",
            blockHandlerClass = BlockHandler.class,
            blockHandler = "blockHandler1",
            fallback = "handlerFallback")
    public String customerBlockHandler() {
        //int q=1/0;
        return "serial003";
    }



    @ResponseBody
    //fallback方法(发生异常的兜底方法)
    public String handlerFallback( Throwable e) {

        return "兜底Java异常handlerFallback,exception内容  "+e.getMessage();
    }




    @ResponseBody
    @GetMapping("/test4")
    public String test4(){

        return serverPort;
    }





}
