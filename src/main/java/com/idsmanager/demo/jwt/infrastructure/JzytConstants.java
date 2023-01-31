package com.idsmanager.demo.jwt.infrastructure;


import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 2017/1/4
 *
 * @author Shengzhao Li
 */

public interface JzytConstants {
    String ENCODING = "UTF-8";
    Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    /**
     * 项目当前的主版本号, 与pom.xml中的<version></version> 对应
     */
    String VERSION = "1.2.0";


    String DEFAULT_COPYRIGHT = "2014-2019 IDsManager.com";

}