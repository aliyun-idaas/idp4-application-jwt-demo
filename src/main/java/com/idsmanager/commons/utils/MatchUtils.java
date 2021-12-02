package com.idsmanager.commons.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author Shengzhao Li
 */
public abstract class MatchUtils {


    /**
     * BigDecimal regex.
     * 大于0的小数或整数
     */
    public static final String BIG_DECIMAL_REGEX = "^(\\d+)||(\\d+\\.\\d+)$";

    /**
     * Positive Number regex.
     * 正整数或0
     */
    public static final String POSITIVE_NUMBER_REGEX = "^\\d+$";


    /**
     * Email regex.
     */
    public static final String EMAIL_REGEX = ".+@.+\\.[a-z]+";

    //Date pattern,  demo:  2013-09-11
    public static final String DATE_PATTERN = "^[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}$";


    private MatchUtils() {
    }

    public static boolean isBigDecimal(String text) {
        return StringUtils.isNotEmpty(text) && text.matches(BIG_DECIMAL_REGEX);
    }


    public static boolean isEmail(String email) {
        return StringUtils.isNotEmpty(email) && email.matches(EMAIL_REGEX);
    }

    public static boolean isPositiveNumber(String numberText) {
        return StringUtils.isNotEmpty(numberText) && numberText.matches(POSITIVE_NUMBER_REGEX);
    }


    public static boolean isDate(String dateAsText) {
        return StringUtils.isNotEmpty(dateAsText) && dateAsText.matches(DATE_PATTERN);
    }

}