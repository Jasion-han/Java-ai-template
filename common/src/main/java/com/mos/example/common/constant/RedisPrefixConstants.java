package com.mos.example.common.constant;

/**
 * Redis前缀
 * @author mos
 */
public final class RedisPrefixConstants {

    private static final String BASE = "BASE:";

    /**
     * 图形验证码
     */
    public static final String PIC_CAPTCHA = BASE + "PIC:CAPTCHA:";

    /**
     * 短信验证码
     */
    public static final String SMS_CAPTCHA = BASE + "SMS:CAPTCHA:";

}
