package com.idsmanager.demo.jwt.commons.web;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Shengzhao Li
 */
public abstract class WebUtils {


    public static final String ENCODING = "UTF-8";


    private static final ThreadLocal<String> ipThreadLocal = new ThreadLocal<>();

    //private
    private WebUtils() {
    }


    public static void setIp(String ip) {
        ipThreadLocal.set(ip);
    }

    public static String getIp() {
        return ipThreadLocal.get();
    }


    /**
     * Retrieve client ip address
     *
     * @param request HttpServletRequest
     * @return IP
     */
    public static String retrieveClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (isUnAvailableIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isUnAvailableIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isUnAvailableIp(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static boolean isUnAvailableIp(String ip) {
        return (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip));
    }

    /**
     * 过滤掉参数中的特殊字符或转义, 防止XSS攻击
     *
     * @param param 参数
     * @return 过滤后的字符串
     */
    public static String paramFilter(String param) {
        if (StringUtils.isEmpty(param)) {
            return param;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < param.length(); i++) {
            final char char1 = param.charAt(i);
            switch (char1) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                case '\'':
                    result.append("&#39;");
                    break;
                case '%':
                    result.append("&#37;");
                    break;
                case ';':
                    result.append("&#59;");
                    break;
                case '(':
                    result.append("&#40;");
                    break;
                case ')':
                    result.append("&#41;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '+':
                    result.append("&#43;");
                    break;
                default:
                    result.append(char1);
            }
        }
        return result.toString();
    }
}