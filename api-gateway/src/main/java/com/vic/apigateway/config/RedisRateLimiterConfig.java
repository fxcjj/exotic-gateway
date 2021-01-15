package com.vic.apigateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * 限流策略配置类
 * 两种策略一种是根据请求参数中的username进行限流，另一种是根据访问IP进行限流；
 * @author 罗利华
 * date: 2021/1/14 18:50
 */
@Configuration
public class RedisRateLimiterConfig {

    /**
     * 基于请求路径的限流
     * @return
     */
    @Bean
    public KeyResolver pathKeyResolver(){
        //lambda表达式
        return exchange -> Mono.just(
                exchange.getRequest().getPath().toString()
        );
//      普通的java写法
//        return new KeyResolver() {
//            @Override
//            public Mono<String> resolve(ServerWebExchange exchange) {
//                return Mono.just(exchange.getRequest().getPath().toString());
//            }
//        };
    }

    /**
     * 基于用户限流
     * @return
     */
    @Bean
    @Primary
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getQueryParams().getFirst("username"));
    }

    /**
     * 基于请求IP限流
     * 注意：同一办公楼，可能出口IP一样
     * @return
     */
    @Bean
    public KeyResolver ipKeyResolver() {
          return exchange -> Mono.just(
                  Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("X-Forwarded-For"))
                          .orElse(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress())
          );
//        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
    }

}
