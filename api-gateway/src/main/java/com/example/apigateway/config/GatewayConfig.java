package com.example.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {

      @Autowired
      private AuthenticationFilter filter;


      @Bean
      public RouteLocator routes(RouteLocatorBuilder builder){
          return builder.routes()
                  .route("user-service",r->r.path("/users/**")
                          .filters(f->f.filter(filter))
                          .uri("lb://user-service"))
                  .route("auth-service",r->r.path("/auth/**")
                          .filters(f->f.filter(filter))
                          .uri("lb://auth-service"))
                  .route("ecars",r->r.path("/cars/**")
                          .filters(f->f.filter(filter))
                          .uri("lb://ecars"))
                  .route("eclient",r->r.path("/main/**")
                          .filters(f->f.filter(filter))
                          .uri("lb://eclient"))
                  .build();
      }
}
