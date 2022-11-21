package com.gateway.filter;
//配置全局过滤器(自定义gateway过滤器)
/*
    拓展:
      过滤器的作用:过滤器就是在请求的过程中，对请求和响应做一些手脚

      分类:局部过滤器（作用在某一个路由上）  全局过滤器（作用在全部路由上）

      在Gateway中，Filter的生命周期只有两个: Pre 、Post

           PRE: 这种过滤器在请求被路由之前调用,我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等

           POST: 这种过滤器在路由到微服务以后执行,这种过滤器可用来为响应添加标准的HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。
*/

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter,Ordered {


    // ServerWebExchange: 服务网络交换器,
    //                    存放着重要的请求-响应属性、请求实例和响应实例等等,我觉得有点像HttpServerRequest和HttpServerResponse
    //GatewayFilterChain: 过滤器链
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("*********come in MyLogGateWayFilter: "+new Date());

        //获取名为uname的请求参数
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");

        //如果uname是null或者是个空串
        if(false){
            log.info("*****用户名为Null 非法用户,(┬＿┬)");

            //给人家一个回应,设置状态码是406(NOT_ACCEPTABLE,也就是不能访问)
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);

            //响应完成,给人家回应
            return exchange.getResponse().setComplete();
        }

        //正常的话,就转到下一过滤器,没有过滤器,就完成任务啦
        return chain.filter(exchange);
    }


    //配置过滤器顺序的,不用管(返回值越小,优先级越高)
    @Override
    public int getOrder() {
        return 0;
    }
}
