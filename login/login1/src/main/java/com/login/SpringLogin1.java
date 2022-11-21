package com.login;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/18 19:23
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.login.mapper") //扫描mapper接口所在的包

public class SpringLogin1 {

    public static void main(String[] args) {
        SpringApplication.run(SpringLogin1.class,args);
    }
}
