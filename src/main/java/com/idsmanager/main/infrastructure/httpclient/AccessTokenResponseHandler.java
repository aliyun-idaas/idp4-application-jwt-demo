package com.idsmanager.main.infrastructure.httpclient;


import com.idsmanager.commons.utils.httpclient.IDSHttpResponse;
import com.idsmanager.main.service.dto.AccessTokenDto;

/**
 * 15-5-18
 *
 * @author Shengzhao Li
 */
public class AccessTokenResponseHandler extends AbstractResponseHandler<AccessTokenDto> {


    private AccessTokenDto accessTokenDto;

    public AccessTokenResponseHandler() {
    }

    @Override
    public void handleResponse(IDSHttpResponse response) {
        if (response.isResponse200()) {
            this.accessTokenDto = responseToDto(response, new AccessTokenDto());
        } else {
            this.accessTokenDto = responseToErrorDto(response, new AccessTokenDto());
        }
    }


    public AccessTokenDto getAccessTokenDto() {
        return accessTokenDto;
    }
}
