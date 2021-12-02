package com.idsmanager.commons.utils.httpclient;

import org.apache.http.client.methods.RequestBuilder;

/**
 * 15-3-27
 *
 * @author Shengzhao Li
 */
public class HttpClientDeleteExecutor extends HttpClientExecutor {

    public HttpClientDeleteExecutor(String url) {
        super(url);
    }


    @Override
    protected RequestBuilder createRequestBuilder() {
        return RequestBuilder.delete();
    }
}
