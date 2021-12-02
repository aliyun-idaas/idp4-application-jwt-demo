package com.idsmanager.commons.web.context;


import com.idsmanager.main.infrastructure.JWTHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * 15-6-22
 *
 * @author Shengzhao Li
 */
public class ExtContextLoaderListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        final ServletContext servletContext = event.getServletContext();

        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        BeanProvider.initialize(applicationContext);
        contextAttributes(servletContext);
    }


    private void contextAttributes(ServletContext servletContext) {

        servletContext.setAttribute("jwtVersion", JWTHolder.VERSION);
    }
}
