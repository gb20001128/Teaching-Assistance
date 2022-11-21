package com.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {

/*
       配置路由可以有两种方式:
         1.在配置文件中(yaml)配置
         2.编码配置(当前的配置)
           下面的意思是:配置了一个id为path_rote_name的路由规则
                      当访问地址 http://localhost:9527/guonei 时会自动转发到地址 http://news.baidu.com/guonei
*/

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("path_rote_name",
                r -> r.path("/guonei")
                        .uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}
