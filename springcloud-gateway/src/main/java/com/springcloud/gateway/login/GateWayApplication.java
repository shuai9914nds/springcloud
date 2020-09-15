package com.springcloud.gateway.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                //basic proxy
//                .route(r -> r.path("/userinfo/**")
//                        .uri("https://www.sougou.com")
//                ).build();
//    }


//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                //basic proxy
//                .route("userinfo-server",r -> r.path("/product/**")
//                        .uri("lb://userinfo-server")
//                ).build();
//    }
}