package com.idsmanager.demo.jwt.web.controller;

import com.idsmanager.demo.jwt.domain.security.IdsUserDetails;
import com.idsmanager.demo.jwt.domain.security.User;
import com.idsmanager.demo.jwt.service.SSOConfigService;
import com.idsmanager.demo.jwt.service.UserService;
import com.idsmanager.demo.jwt.service.dto.sso.SSOConfigDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Jwks;
import io.jsonwebtoken.security.RsaPublicJwk;
import io.jsonwebtoken.security.SignatureException;
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
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

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
        final RsaPublicJwk key = (RsaPublicJwk) Jwks.parser().build().parse(publickey);
        final RSAPublicKey rsaPublicKey = key.toKey();
        //2. 校验id_token是否合法
        Claims claims ;
        try {
            claims= Jwts.parser()
                    .verifyWith(rsaPublicKey)
                    .build()
                    .parseSignedClaims(id_token)
                    .getPayload();
        }catch (SignatureException e){
            LOG.warn("id_token verifySignature failed",e);
            //校验失败，报错，返回
            model.addAttribute("error", "Retrieve Username error: id_token verifySignature failed");
            return "error";
        }

        //3. 校验id_token是否过期
        Date expirationTime = claims.getExpiration();
        if (expirationTime != null && expirationTime.before(new Date())) {
            LOG.warn("id_token expired");
            //校验失败，报错，返回
            model.addAttribute("error", "Retrieve Username error: id_token expired");
            return "error";
        }
        //4. 注意校验id_token是否已经登陆过，防重放攻击
        //业务系统自己实现，需要校验有效期内，是否有相同的id_token已经登录
        final String jti = claims.getId();//获取token唯一标识
        //从自身缓存系统判断jti是否已经登录过
        //if(exit(jti)){
        //    model.addAttribute("error", "id_token verifySignature failed");
        //    return error;
        //}
        //save(jti);

        //5.获取到用户信息，检测用户名是否存在自己的业务系统中，isExistedUsername方法为示例实现
        String username = claims.getSubject();
        if (userService.isExistedUsername(username)) {
            //7.如果存在,登录成功，返回登录成功后的界面
            User sysUser = userService.updateLoginTimes(username);
            HttpSession session = request.getSession();
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, saveSecurity(sysUser));

            //8.如果注册应用时添加了target_url，那么返回此自定义url页面
            if (StringUtils.isNotEmpty(target_url)) {
                return "redirect:" + target_url;
            }
            //9.否则返回系统默认操作页面
            return "redirect:../../index";
        } else {
            //10.如果不存在,返回登录失败页面,提示用户不存在
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
