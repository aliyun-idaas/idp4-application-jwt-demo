package com.idsmanager.main;


import com.idsmanager.commons.web.context.BeanProvider;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @author Shengzhao Li
 */
@ContextConfiguration(locations = {"classpath:/spring/*.xml"}, initializers = {TestApplicationContextInitializer.class})
public abstract class ContextTest extends AbstractTestNGSpringContextTests {


    protected void initBeanProvider() {
        BeanProvider.initialize(this.applicationContext);
    }


}