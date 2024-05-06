package com.example.stock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class MyCorsFilter {

    @Bean
    public CorsFilter corsFilter() {
        // 1.创建 CORS 配置对象
        CorsConfiguration config = new CorsConfiguration();
        // 支持域，格式是：域名+端口（http://localhost:8081），
        config.addAllowedOriginPattern("*");
        // 是否发送 Cookie
        config.setAllowCredentials(true);

        // 支持请求方式:POST,GET,HEAD等，*代表全部支持
        config.addAllowedMethod("*");

        // 2.添加地址映射
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        // /** 表示当前项目的所有请求地址，都支持跨域访问。
        corsConfigurationSource.registerCorsConfiguration("/**", config);
        // 3.返回 CorsFilter 对象
        return new CorsFilter(corsConfigurationSource);
    }
}
