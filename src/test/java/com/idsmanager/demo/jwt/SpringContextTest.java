package com.idsmanager.demo.jwt;


import com.idsmanager.demo.jwt.commons.web.context.BeanProvider;
import com.idsmanager.demo.jwt.domain.security.SecurityUtils;
import com.idsmanager.demo.jwt.domain.security.SpringSecurityHolder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.BeforeTransaction;


/**
 * @author Shengzhao Li
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public abstract class SpringContextTest extends AbstractTransactionalJUnit4SpringContextTests {


    @BeforeTransaction
    public void before() throws Exception {
        BeanProvider.initialize(applicationContext);
        SecurityUtils securityUtils = new SecurityUtils();
        securityUtils.setSecurityHolder(new SpringSecurityHolder() {
            @Override
            public UserDetails userDetails() {
                return null;
            }
        });
    }
}