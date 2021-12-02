package com.idsmanager.main.web.controller;

import com.idsmanager.dingdang.jwt.DingdangUserRetriever;
import com.idsmanager.main.domain.security.IdsUserDetails;
import com.idsmanager.main.domain.security.User;
import com.idsmanager.main.service.SSOConfigService;
import com.idsmanager.main.service.UserService;
import com.idsmanager.main.service.dto.sso.SSOConfigDto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

/**
 * @author feng
 * @date 2016/4/5
 */
@Controller
public class SSOController {

    private static final Logger LOG = LoggerFactory.getLogger(SSOController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private SSOConfigService ssoConfigService;

    /**
     * id_token是IDaaS请求时带来的，参数名为 "id_token" ， 支持使用GET/POST两种方式放入;
     * PublicKey是在IDaaS里注册应用时生成的，可在应用详情页中JWT PublicKey查看;
     * target_url是IDaaS里注册应用时设置的target_url，此示例代码是通过id_token和PublicKey解析用户信息并完成单点登录。
     */
    @RequestMapping(value = "/public/sso/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String ssoUrl(@RequestParam String id_token, @PathVariable("id") String id, String target_url, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        //1.接收方法，GET和POST均支持
        //2.<解析令牌>为解析id_token并验证用户信息
        SSOConfigDto config = ssoConfigService.findSSOConfigById(id);
        if (null == config) {
            model.addAttribute("error", "system config publicKey do not EXIST");
            return "error";
        }
        return checkUsername(id_token, target_url, model, request, config.getPublicKey());
    }


    private String checkUsername(String id_token, String target_url, Model model, HttpServletRequest request, String publickey) throws UnsupportedEncodingException {
        //1.使用公钥解析id_token，使用publicKey解密上一步获取的id_token令牌
        DingdangUserRetriever retriever = new DingdangUserRetriever(id_token, publickey);
        DingdangUserRetriever.User user = null;
        try {
            //2.获取用户信息
            user = retriever.retrieve();
        } catch (Exception e) {
            LOG.warn("Retrieve Username error", e);
            model.addAttribute("error", "Retrieve Username error: " + e.getMessage());
            return "error";
        }

        //3.如果publicKey不正确或者id_token过期，获取到的用户信息可能为null
        if (null == user) {
            model.addAttribute("error", "wrong request,not found Username from id_token or id_token has expired");
            return "error";
        }

        //4.获取到用户信息，检测用户名是否存在自己的业务系统中，isExistedUsername方法为示例实现
        if (userService.isExistedUsername(user.getUsername())) {

            //5.如果存在,登录成功，返回登录成功后的界面
            User sysUser = userService.updateLoginTimes(user.getUsername());
            HttpSession session = request.getSession();
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, saveSecurity(sysUser));

            //6.如果注册应用时添加了target_url，那么返回此自定义url页面
            if (StringUtils.isNotEmpty(target_url)) {
                return "redirect:" + target_url;
            }

            //7.否则返回系统默认操作页面
            return "redirect:../../index";
        } else {
            //8.如果不存在,返回登录失败页面,提示用户不存在
            model.addAttribute("error", "username { " + user.getUsername() + " } not exist");
            return "error";
        }
    }

    private SecurityContextImpl saveSecurity(User user) {
        IdsUserDetails userDetails = new IdsUserDetails(user);
        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        SecurityContextImpl securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(auth);
        return securityContext;
    }


}
