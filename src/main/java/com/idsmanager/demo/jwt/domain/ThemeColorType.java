package com.idsmanager.demo.jwt.domain;

import java.util.Arrays;
import java.util.List;

/**
 * @author Guilty_Crown
 */
public enum ThemeColorType {
    BLUE("蓝色", "blue"),
    NAVYBLUE("海军蓝", "navy"),
    GREEN("绿色", "green"),
    DEEPBLUE("深蓝", "#337ab7"),
    ORANGE("橘黄色", "orange"),
    WINRED("酒红", "#8D121D"),
    LIGHTGREEN("浅绿色", "lightgreen");

    private final String label;
    private final String value;

    ThemeColorType(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public List<ThemeColorType> getAllColorTypes() {
        return Arrays.asList(values());
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }
}
