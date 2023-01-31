package com.idsmanager.demo.jwt.config;

import com.idsmanager.demo.jwt.infrastructure.JWTHolder;
import com.idsmanager.demo.jwt.infrastructure.JzytConstants;
import com.opensymphony.sitemesh.webapp.SiteMeshFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 2018/3/20
 * <p>
 * MVC 扩展配置
 *
 * @author Shengzhao Li
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Value("${application.host}")
    private String jwtHost;

    /**
     * 扩展拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        // ext...

    }


    /**
     * 解决乱码问题
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        WebMvcConfigurer.super.configureMessageConverters(converters);
        converters.add(new StringHttpMessageConverter(JzytConstants.DEFAULT_CHARSET));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Bean
    public FilterRegistrationBean<SiteMeshFilter> siteMeshFilter() {
        FilterRegistrationBean<SiteMeshFilter> filter = new FilterRegistrationBean<>();
        SiteMeshFilter siteMeshFilter = new SiteMeshFilter();
        filter.setFilter(siteMeshFilter);
        filter.addUrlPatterns("/*");
        return filter;
    }

    @Bean
    public JWTHolder jwtHolder() {
        final JWTHolder jwtHolder = new JWTHolder();
        jwtHolder.setJwtHost(jwtHost);
        return jwtHolder;
    }
}
