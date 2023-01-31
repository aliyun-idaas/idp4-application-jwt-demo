package com.idsmanager.demo.jwt;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 2018/3/20
 *
 * @author Shengzhao Li
 */
public class BootServletInitializer extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BootWebApplication.class);
    }
}
