package com.idsmanager.demo.jwt.service.business;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * 2018/3/20
 *
 * @author Shengzhao Li
 */
public class DefaultUserInitializerTest {


    @Test
    public void encodePassword() {

        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        final String rawPassword = "Jzyt@2018";
        final String encode = passwordEncoder.encode(rawPassword);

        assertNotNull(encode);
//        System.out.println(rawPassword + "  ---> " + IdsBase64Utils.base64Encode(encode));


        final PasswordEncoder passwordEncoder1 = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        final String encode1 = passwordEncoder1.encode(rawPassword);

        assertNotNull(encode1);


    }

}