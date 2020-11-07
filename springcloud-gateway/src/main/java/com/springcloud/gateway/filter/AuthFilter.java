package com.springcloud.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.google.common.net.HttpHeaders;
import common.ErrorCode;
import common.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.google.common.net.MediaType.*;

/**
 * @author: liushuai
 * @date: 2020/10/18
 * @description：
 */
public class AuthFilter implements Ordered, GlobalFilter {
    Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        //指定的url放行
        String path = request.getURI().getPath();
        if ("/login/login".equals(path) || "/login/verifyCode".equals(path)) {
            return chain.filter(exchange);
        }
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (StringUtils.isBlank(token)) {
            logger.error("token不能为空");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return getVoidMono(exchange.getResponse(), ErrorCode.TOKEN_IS_NULL);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> getVoidMono(ServerHttpResponse serverHttpResponse, ErrorCode errorCode) {
        serverHttpResponse.getHeaders().add(HttpHeaders.CONTENT_TYPE, JSON_UTF_8.toString());
        DataBuffer dataBuffer = serverHttpResponse.bufferFactory().wrap(JSON.toJSONString(new Result<>(errorCode)).getBytes());
        return serverHttpResponse.writeWith(Flux.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}