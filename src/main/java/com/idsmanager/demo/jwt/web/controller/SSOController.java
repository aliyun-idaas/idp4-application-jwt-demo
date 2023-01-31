package com.idsmanager.demo.jwt.web.controller;

import com.idsmanager.demo.jwt.domain.security.IdsUserDetails;
import com.idsmanager.demo.jwt.domain.security.User;
import com.idsmanager.demo.jwt.service.SSOConfigService;
import com.idsmanager.demo.jwt.service.UserService;
import com.idsmanager.demo.jwt.service.dto.sso.SSOConfigDto;
import org.apache.commons.lang.StringUtils;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
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
    public String ssoUrl(@RequestParam String id_token, @PathVariable("id") String id, String target_url, Model model, HttpServletRequest request) {
        //1.接收方法，GET和POST均支持
        //2.<解析令牌>为解析id_token并验证用户信息
        SSOConfigDto config = ssoConfigService.findSSOConfigById(id);
        if (null == config) {
            model.addAttribute("error", "system config publicKey do not EXIST");
            return "error";
        }
        try {
            return checkAndGetUsername(id_token, target_url, model, request, config.getPublicKey());
        } catch (Exception e) {
            LOG.warn("id_token verifySignature failed", e);
            model.addAttribute("error", "wrong request,not found Username from id_token or id_token has expired");
            return "error";
        }
    }


    private String checkAndGetUsername(String id_token, String target_url, Model model, HttpServletRequest request, String publickey) throws Exception {

        //1. 初始化
        JsonWebSignature jws = new JsonWebSignature();
        jws.setCompactSerialization(id_token);
        jws.setKey(JsonWebKey.Factory.newJwk(publickey).getKey());
        //2. 校验id_token是否合法
        final boolean verifySignature = jws.verifySignature();
        if (!verifySignature) {
            LOG.warn("id_token verifySignature failed");
            //校验失败，报错，返回
            model.addAttribute("error", "Retrieve Username error: id_token verifySignature failed");
            return "error";
        }
        //3. 获取jwt中的payload信息，json格式，这里可以自由转换为需要的实体类
        final String payload = jws.getPayload();

        //4. 校验id_token是否过期
        JwtClaims claims = JwtClaims.parse(payload);
        NumericDate expirationTime = claims.getExpirationTime();
        if (expirationTime != null && expirationTime.isBefore(NumericDate.now())) {
            LOG.warn("id_token expired");
            //校验失败，报错，返回
            model.addAttribute("error", "Retrieve Username error: id_token expired");
            return "error";
        }
        String username = claims.getSubject();

        //4.获取到用户信息，检测用户名是否存在自己的业务系统中，isExistedUsername方法为示例实现
        if (userService.isExistedUsername(username)) {
            //5.如果存在,登录成功，返回登录成功后的界面
            User sysUser = userService.updateLoginTimes(username);
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
            model.addAttribute("error", "username { " + username + " } not exist");
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
