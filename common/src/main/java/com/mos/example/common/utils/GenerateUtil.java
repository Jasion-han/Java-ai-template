package com.mos.example.common.utils;

import cn.hutool.core.util.RandomUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 生成工具
 * @author mos
 */
public class GenerateUtil {

    /**
     * 根据时间生成编号
     * @param prefix - 编号前缀
     * @param suffixLength - 编号后缀长度
     * @return
     */
    public static String generateNumber(String prefix, int suffixLength) {
        String randomString = RandomUtil.randomNumbers(suffixLength);
        return prefix + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS")) + randomString;
    }

    public static void main(String[] args) {
        System.out.println(generateNumber("YH", 3));
    }
}
