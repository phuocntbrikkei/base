package com.example.ss9.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Cấu hình để hiển thị ảnh trực tiếp từ thư mục C:\Users\PhuocNTB\Documents\img
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:///C:/Users/PhuocNTB/Documents/img/", "file:C:/Users/PhuocNTB/Documents/img/");
    }
}
