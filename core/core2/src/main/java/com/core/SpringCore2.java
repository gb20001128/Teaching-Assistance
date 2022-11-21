package com.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/18 21:25
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.core.mapper") //扫描mapper接口所在的包
@EnableCaching //开启缓存
public class SpringCore2 {

    public static void main(String[] args) {
        SpringApplication.run(SpringCore2.class,args);
    }
}
