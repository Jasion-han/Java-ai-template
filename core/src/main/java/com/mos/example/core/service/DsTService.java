package com.mos.example.core.service;

/**
 * 多数据源事务示例
 * 本类仅用于演示用法，无实际意义
 * @author Han
 */
public interface DsTService {

    /**
     * 插入到数据源 A
     *
     * @return 插入成功的行数
     */
    Integer insert2A();

    /**
     * 插入到数据源 B
     *
     * @return 插入成功的行数
     */
    Integer insert2B();

}
