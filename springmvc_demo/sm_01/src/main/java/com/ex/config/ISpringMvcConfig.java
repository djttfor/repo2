package com.ex.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan("com.ex.controller")
public class ISpringMvcConfig implements WebMvcConfigurer {
//    @Bean
//    public FastJsonConfig buildFastJsonConfig(){
//        return new FastJsonConfig();
//    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter f = new FastJsonHttpMessageConverter();
        converters.add(f);
    }
}
