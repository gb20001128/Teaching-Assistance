package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description
 * @Author gb
 * @Data 2022/7/18 18:42
 */

@SpringBootApplication
@EnableDiscoveryClient          //开始服务发现
public class SpringGateway {


    public static void main(String[] args) {
        SpringApplication.run(SpringGateway.class,args);
    }
}
