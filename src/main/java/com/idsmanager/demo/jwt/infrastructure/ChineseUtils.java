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
package com.idsmanager.demo.jwt.infrastructure;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理与中文相关的工具类
 * 如中文匹配
 */
public abstract class ChineseUtils {


    /**
     * 中文 表达式
     */
    public static final String CHINESE_REGEX = "[\u4e00-\u9fa5]";

    /**
     * 全为中文 表达式
     */
    public static final String CHINESE_ALL_REGEX = CHINESE_REGEX + "{0,}";


    private ChineseUtils() {
    }


    /**
     * 判断是否全为中文
     *
     * @param text 输入文本
     * @return true or false
     */
    public static boolean isChinese(String text) {
        return StringUtils.isNotEmpty(text) && text.matches(CHINESE_ALL_REGEX);
    }


    /**
     * 判断是否 包含中文
     *
     * @param text 输入文本
     * @return true or false
     */
    public static boolean isIncludeChinese(String text) {
        if (StringUtils.isEmpty(text)) {
            return false;
        }
        final Pattern pattern = Pattern.compile(CHINESE_REGEX);
        final Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

}
