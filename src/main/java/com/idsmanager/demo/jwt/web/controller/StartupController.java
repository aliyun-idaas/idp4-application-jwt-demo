package com.idsmanager.demo.jwt.web.controller;

import com.idsmanager.demo.jwt.commons.web.WebUtils;
import com.idsmanager.demo.jwt.domain.security.SecurityUtils;
import com.idsmanager.demo.jwt.infrastructure.JzytConstants;
import com.idsmanager.demo.jwt.service.LoginPageConfigService;
import com.idsmanager.demo.jwt.service.SystemConfigService;
import com.idsmanager.demo.jwt.service.UserService;
import com.idsmanager.demo.jwt.service.dto.loginpage.LoginPageConfigDto;
import com.idsmanager.demo.jwt.service.dto.loginpage.LoginPageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.idsmanager.demo.jwt.infrastructure.JWTHolder.COPYRIGHT;

/**
 * 2016/1/19
 *
 * @author Shengzhao Li
 */
@Controller
public class StartupController {

    private static final Logger LOG = LoggerFactory.getLogger(StartupController.class);

    @Autowired
    private SystemConfigService configService;

    @Autowired
    private LoginPageConfigService loginPageConfigService;


    @Autowired
    private UserService userService;


    @RequestMapping(value = {"/index", "/"})
    public String dashboard(Model model, HttpServletRequest request) {
        LoginPageConfigDto loginPageConfig = loginPageConfigService.findLoginPageConfig();
        HttpSession session = request.getSession();
        session.setAttribute("title", loginPageConfig.getTitle());
        session.setAttribute(COPYRIGHT, loginPageConfig.getCopyright());

        model.addAttribute("userProfile", userService.getUserByUsername(SecurityUtils.username()));
        return "index";
    }


    @RequestMapping("login")
    public String login(Model model) {
        LoginPageDto loginPageDto = loginPageConfigService.loadLoginConfig();
        model.addAttribute("loginPageConfig", loginPageDto);
        model.addAttribute("systemConfig", loginPageDto.getSystemConfig());
        model.addAttribute("jwtVersion", JzytConstants.VERSION);
        return "login";
    }


    /**
     * When the firstly initial system, create a super-admin account
     */
    @RequestMapping(value = "/public/initial_user", method = RequestMethod.GET)
    @ResponseBody
    public String initialUser() {
        LOG.info("Call 'initialUser' from IP: {}", WebUtils.getIp());
        return userService.initialDefaultUser();
    }


}
