package com.idsmanager.main.web.context;

import com.idsmanager.main.domain.security.IdsUserDetails;
import com.idsmanager.main.domain.security.User;
import com.idsmanager.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by feng on 2016/4/6.
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    public LoginSuccessHandler(){

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        IdsUserDetails userDetails = (IdsUserDetails)authentication.getPrincipal();
        User user = userDetails.user();
        if(user!=null){
            userService.updateLoginTimes(user.getUsername());
        }
        final String contextPath = request.getContextPath();
        response.sendRedirect(contextPath+"/index");
    }
}
