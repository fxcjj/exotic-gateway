package com.vic.apigateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 罗利华
 * date: 2021/1/15 10:51
 */
//@Component
public class GatewayFilter implements GlobalFilter, Ordered {

    /**
     * 过滤逻辑
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("进入了 GatewayFilter");
        String accessToken = exchange.getRequest().getQueryParams().getFirst("access_token");
        if(!StringUtils.hasText(accessToken)) {
            // 如果不存在，认证失败
            System.out.println("没有登陆");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        // 表示继续向下执行
        return chain.filter(exchange);
    }

    /**
     * 过滤器的优先级，数值越小，优先级越高
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
