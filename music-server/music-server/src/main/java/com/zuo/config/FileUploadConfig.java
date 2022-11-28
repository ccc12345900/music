package com.zuo.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.servlet.MultipartConfigElement;

/**
 * 文件上传配置类
 *
 * @Author mashanghaoyun
 * @Date 2020/11/11 01:34
 * @Version 1.0
 **/
@Configuration
public class FileUploadConfig {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个数据大小
        factory.setMaxFileSize(DataSize.of(1024, DataUnit.MEGABYTES)); // KB,MB
        // 总上传数据大小
        factory.setMaxRequestSize(DataSize.of(1024, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

}

