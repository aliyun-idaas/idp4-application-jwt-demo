package com.idsmanager.demo.jwt.config;

import com.idsmanager.demo.jwt.domain.security.SecurityUtils;
import com.idsmanager.demo.jwt.domain.security.SpringSecurityHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 2018/3/20
 * <p>
 * <p>
 * Security 配置
 *
 * @author Shengzhao Li
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userService;
    @Autowired
    private AuthenticationSuccessHandler successHandler;


    @Bean
    public SecurityUtils securityUtils() {
        SecurityUtils securityUtils = new SecurityUtils();
        securityUtils.setSecurityHolder(new SpringSecurityHolder());
        return securityUtils;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());

        return authenticationProvider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略,不需要 安全
        web.ignoring().antMatchers("/jwt/**", "/static/**", "/public/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
        http
                .authorizeRequests()
                //Ignore swagger URLS
                .antMatchers("/oauth/**").hasAnyRole("EMM_SERVER", "TOKEN_AGENT_USER", "OAUTH_RP")
                .antMatchers("/login*").permitAll()
                .antMatchers("/system*").hasAnyRole("SYSTEM_ACCOUNT")
                .antMatchers("/user*").hasAnyRole("USER_ACCOUNT")
                .antMatchers("/**").fullyAuthenticated()
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(successHandler)
                .failureUrl("/login?authentication_error=1")
//                .failureHandler(authenticationFailureHandler)
//                .usernameParameter("user2")
//                .passwordParameter("pWwd_")
                .loginProcessingUrl("/signin")
//                .permitAll()
                .and()
                .logout()
                .logoutUrl("/signout")
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
                .and()
                .anonymous()
                .and().csrf().disable();
        http.authenticationProvider(authenticationProvider());
    }


}
