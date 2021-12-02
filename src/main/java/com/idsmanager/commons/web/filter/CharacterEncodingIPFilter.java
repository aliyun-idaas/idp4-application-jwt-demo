
package com.idsmanager.commons.web.filter;


import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.idsmanager.commons.web.WebUtils.retrieveClientIp;
import static com.idsmanager.commons.web.WebUtils.setIp;

/**
 * 2015/12/8
 *
 * @author Shengzhao Li
 */
public class CharacterEncodingIPFilter extends CharacterEncodingFilter {


    public CharacterEncodingIPFilter() {
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        recordIP(request);
        super.doFilterInternal(request, response, filterChain);
    }

    private void recordIP(HttpServletRequest request) {
        final String clientIp = retrieveClientIp(request);
        setIp(clientIp);
    }
}
