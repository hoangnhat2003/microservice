package com.microservice.cloudgateway.config;

import com.microservice.cloudgateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/api/v1/auth/**").filters(f -> f.filter(filter)).uri("lb://AUTH-SERVER"))
                .route("departments", r -> r.path("/api/v1/departments/**").filters(f -> f.filter(filter)).uri("lb://DEPARTMENT-SERVICE"))
                .route("users", r -> r.path("/api/v1/users/**").filters(f -> f.filter(filter)).uri("lb://CORE-SERVICE"))
                .route("tasks", r -> r.path("/api/v1/tasks/**").filters(f -> f.filter(filter)).uri("lb://CORE-SERVICE"))
                .route("projects", r -> r.path("/api/v1/projects/**").filters(f -> f.filter(filter)).uri("lb://CORE-SERVICE"))
                .route("teams", r -> r.path("/api/v1/teams/**").filters(f -> f.filter(filter)).uri("lb://CORE-SERVICE"))
                .build();
    }

}