package com.idsmanager.demo.jwt.web.context;

import com.idsmanager.demo.jwt.domain.SystemConfig;
import com.idsmanager.demo.jwt.domain.security.IdsUserDetails;
import com.idsmanager.demo.jwt.domain.security.User;
import com.idsmanager.demo.jwt.service.SystemConfigService;
import com.idsmanager.demo.jwt.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by feng on 2016/4/6.
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
    @Autowired
    private SystemConfigService systemConfigService;

    public LoginSuccessHandler() {

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        IdsUserDetails userDetails = (IdsUserDetails) authentication.getPrincipal();
        User user = userDetails.user();
        if (user != null) {
            userService.updateLoginTimes(user.getUsername());
        }
        SystemConfig systemConfig = systemConfigService.findSystemConfig();
        if (systemConfig != null && StringUtils.isNotBlank(systemConfig.getSpLogoutUrl())) {
            request.getSession().removeAttribute("logoutUrl");
            request.getSession().setAttribute("logoutUrl", systemConfig.getSpLogoutUrl());
        } else {
            request.getSession().removeAttribute("logoutUrl");
        }

        final String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/index");
    }
}
