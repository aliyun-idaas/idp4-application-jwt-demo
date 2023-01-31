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
package com.idsmanager.demo.jwt.web.validation;

import com.idsmanager.demo.jwt.service.SSOConfigService;
import com.idsmanager.demo.jwt.service.dto.sso.SSOConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SSOConfigDtoValidator implements Validator {

    @Autowired
    private SSOConfigService configService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SSOConfigDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "publicKey", null, "publicKey不能为空");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "name不能为空");

        SSOConfigDto formDto = (SSOConfigDto) target;
        if(null != formDto.getOldName() && formDto.getName().equals(formDto.getOldName())){
        }else if(configService.findSSOConfigByName(formDto.getName())){
            errors.rejectValue("name", null, "名称已存在");
        }
    }
}
