package com.idsmanager.commons.utils.httpclient;

import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Ext. CloseableHttpResponse actions. Add more conversation methods
 *
 * @author Shengzhao Li
 */
public class IDSHttpResponse implements Serializable {

    private static final long serialVersionUID = -7068099925689176917L;
    protected CloseableHttpResponse httpResponse;

    public IDSHttpResponse(CloseableHttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    public CloseableHttpResponse httpResponse() {
        return httpResponse;
    }

    /**
     * Convert response as string
     */
    public String responseAsString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            httpResponse.getEntity().writeTo(baos);
            return new String(baos.toByteArray(), "UTF-8");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Check response status is 200 or not
     */
    public boolean isResponse200() {
        return httpResponse.getStatusLine().getStatusCode() == 200;
    }

    /**
     * Check response status is 302 or not
     */
    public boolean isResponse302() {
        return httpResponse.getStatusLine().getStatusCode() == 302;
    }

}