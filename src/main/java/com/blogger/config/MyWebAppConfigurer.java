package com.blogger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 解决跨域问题springboot所需配置
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    //解决跨域
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration conf = new CorsConfiguration();
        conf.addAllowedHeader("*");
        conf.addAllowedMethod("*");
        conf.addAllowedOrigin("*");
        conf.setAllowCredentials(true);
        conf.setMaxAge(3600L);
        conf.addExposedHeader("set-cookie");
        conf.addExposedHeader("access-control-allow-headers");
        conf.addExposedHeader("access-control-allow-methods");
        conf.addExposedHeader("access-control-allow-origin");
        conf.addExposedHeader("access-control-max-age");
        conf.addExposedHeader("X-Frame-Options");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", conf); // 4 对接口配置跨域设置
        return new CorsFilter(source);
    }
}
