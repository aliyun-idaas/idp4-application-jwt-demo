package com.idsmanager.demo.jwt.commons.web.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationObjectSupport;

@Component
public class SpringAppCtxHolder extends WebApplicationObjectSupport implements EnvironmentAware {

    private static ApplicationContext context;
    private static Environment environment;

    @Override
    protected void initApplicationContext(ApplicationContext context) {
        SpringAppCtxHolder.context = context;
        BeanProvider.initialize(context);
    }

    @Override
    public void setEnvironment(Environment environment) {
        SpringAppCtxHolder.environment = environment;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static void setContext(ApplicationContext context) {
        SpringAppCtxHolder.context = context;
    }

    public static Environment getAppEnvironment() {
        return environment;
    }

    public static void setAppEnvironment(Environment environment) {
        SpringAppCtxHolder.environment = environment;
    }
}