package com.idsmanager.commons.utils.httpclient;

import org.apache.http.client.methods.RequestBuilder;

/**
 * 15-3-27
 *
 * @author Shengzhao Li
 */
public class HttpClientPostExecutor extends HttpClientExecutor {

    public HttpClientPostExecutor(String url) {
        super(url);
    }


    @Override
    protected RequestBuilder createRequestBuilder() {
        return RequestBuilder.post();
    }
}
