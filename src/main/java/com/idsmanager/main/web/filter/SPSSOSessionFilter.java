/*
 * Copyright (c) 2016 BeiJing JZYT Technology Co. Ltd
 * www.idsmanager.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * BeiJing JZYT Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with BeiJing JZYT Technology Co. Ltd.
 */
package com.idsmanager.main.web.filter;

import com.idsmanager.main.domain.security.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 2018/1/24
 *
 * @author Shaofei Du.
 */
public class SPSSOSessionFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(SPSSOSessionFilter.class);
    /***
     * ??????????????????????????????SSO
     */
    private String checkSSO;
    /**
     * ?????????????????????SP??????SSO?????????
     */
    private String checkSSOUrl;
    /**
     * ?????????????????????SP????????????????????????
     */
    private String spLoginUrl;
    /**
     * ?????????????????????URL,?????????,??????:/login,/static???????????????
     */
    private String excludesUrl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        checkSSO = filterConfig.getInitParameter("checkSSO");
        checkSSOUrl = filterConfig.getInitParameter("checkSSOUrl");
        spLoginUrl = filterConfig.getInitParameter("spLoginUrl");
        excludesUrl = filterConfig.getInitParameter("excludesUrl");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        /**
         * ???????????????,???????????????
         */
        String username = SecurityUtils.username();
        /**
         * ???????????????URL,???????????????,??????????????????????????????CHECK SSO
         */
        if (StringUtils.isNotBlank(excludesUrl)){
            String[] exclude = excludesUrl.split(",");
            for (String url : exclude) {
                if (servletRequest.getRequestURI().contains(url)){
                    LOG.warn("exclude must url " + url);
                    chain.doFilter(request,response);
                    return;
                }
            }
        }

        /**
         * ????????????????????? CHECK SSO
         */
        if (checkSSO.equals("true")){
            /**
             * ??????????????????????????????????????????SP????????????
             */
            if (StringUtils.isBlank(username)){
                LOG.warn("not username redirect sp login url" + spLoginUrl);
                servletResponse.sendRedirect(spLoginUrl);
                return;
            }

            /**
             * 1.????????????????????????????????????????????????
             * 2.?????????????????????????????????????????????????????????
             */
            HttpClientUtils utils = new HttpClientUtils();
            HttpClient client = utils.warpClient();
            HttpGet httpGet = new HttpGet(checkSSOUrl + username);
            HttpResponse res = client.execute(httpGet);
            HttpEntity httpEntity = res.getEntity();
            InputStream ins = httpEntity.getContent();
            // ??????entity???????????????
            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String applicationUsername = br.readLine();
            if (StringUtils.isNotBlank(applicationUsername)){
                if (!StringUtils.equalsIgnoreCase(username,applicationUsername)){
                    LOG.warn("IDP return username {0} not equals current username {1}",applicationUsername,username);
                    servletResponse.sendRedirect(spLoginUrl);
                    return;
                }
            }else if (StringUtils.isBlank(applicationUsername)){
                LOG.warn("IDP return username is empty redirect sp login url " + spLoginUrl);
                servletResponse.sendRedirect(spLoginUrl);
                return;
            }

        }

        chain.doFilter(request, response);
    }

    //----------------------------------------------UTILS?????????-----------------------------------------------------------------

    public class HttpClientUtils {

        public  synchronized HttpClient warpClient(){
            HttpClient client = new DefaultHttpClient();
            try {

                KeyStore trustStore = KeyStore.getInstance(KeyStore
                        .getDefaultType());
                trustStore.load(null, null);
                SSLSocketFactory sf = new SSLSocketFactoryNew(trustStore);
                sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  //???????????????????????????

                HttpParams params = new BasicHttpParams();

                HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
                HttpProtocolParams.setContentCharset(params,
                        HTTP.DEFAULT_CONTENT_CHARSET);
                HttpProtocolParams.setUseExpectContinue(params, true);

                // ??????????????????????????????
                ConnManagerParams.setTimeout(params, 10000);
                // ??????????????????
                HttpConnectionParams.setConnectionTimeout(params, 10000);
                // ??????socket??????
                HttpConnectionParams.setSoTimeout(params, 10000);

                // ??????http https??????
                SchemeRegistry schReg = new SchemeRegistry();

                schReg.register(new Scheme("https", sf, 443));

                ClientConnectionManager conm = client.getConnectionManager();

                conm.getSchemeRegistry().register(new Scheme("https", sf, 443));


                client= new DefaultHttpClient(conm, params);
            } catch (Exception e) {
                e.printStackTrace();
                return new DefaultHttpClient();
            }



            return client;
        }

    }


    public class SSLSocketFactoryNew extends SSLSocketFactory {

        SSLContext sslContext = SSLContext.getInstance("TLS");


        public SSLSocketFactoryNew(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(truststore);

            TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            sslContext.init(null, new TrustManager[] { tm }, null);
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
            return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
        }

        @Override
        public Socket createSocket() throws IOException {
            return sslContext.getSocketFactory().createSocket();
        }
    }


    @Override
    public void destroy() {

    }
}