package com.mos.example.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Han
 * @since 2025/5/26
 */
@Getter
@RequiredArgsConstructor
public enum WeekEnum {

    MONDAY("星期一"),
    TUESDAY("星期二"),
    WEDNESDAY("星期三"),
    THURSDAY("星期四"),
    FRIDAY("星期五"),
    SATURDAY("星期六"),
    SUNDAY("星期日"),
    ;

    private final String message;
}
