package me.dong.config;

import com.mysema.commons.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 이미지 업로드/다운로드 URI와 서버 파일 경로를 매핑
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String storagePath = env.getProperty("storage.path");
        String storageUri = env.getProperty("storage.uri");
        Assert.hasText(storagePath, "storage.path(application.properties) is empty...");
        Assert.hasText(storageUri, "storage.uri(application.properties) is empty...");
        registry.addResourceHandler(storageUri + "/**").addResourceLocations("file:" + storagePath + "/");
    }
}
