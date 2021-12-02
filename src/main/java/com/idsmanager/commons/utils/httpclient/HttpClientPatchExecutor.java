package com.idsmanager.commons.utils.httpclient;

import org.apache.http.client.methods.RequestBuilder;

/**
 * 15-3-27
 *
 * @author Shengzhao Li
 */
public class HttpClientPatchExecutor extends HttpClientExecutor {

    public HttpClientPatchExecutor(String url) {
        super(url);
    }


    @Override
    protected RequestBuilder createRequestBuilder() {
        return RequestBuilder.patch();
    }
}
