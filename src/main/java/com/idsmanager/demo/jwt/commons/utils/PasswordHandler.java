package com.idsmanager.demo.jwt.commons.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 2015/7/15
 *
 * @author Shengzhao Li
 */
public abstract class PasswordHandler {


    public static final int SHA_STRENGTH = 256;


    //private, singleton
    private PasswordHandler() {
    }

    /**
     * Return a random password from {@link java.util.UUID},
     * the length is 8.
     *
     * @return Random password
     */
    public static String randomPassword() {
        String uuid = UUIDGenerator.generate();
        return uuid.substring(0, 8);
    }


    /**
     * SHA encrypt with salt
     *
     * @param originalPassword Password
     * @return Encrypted password
     */
    public static String encryptPassword(String originalPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(originalPassword);
    }

}
