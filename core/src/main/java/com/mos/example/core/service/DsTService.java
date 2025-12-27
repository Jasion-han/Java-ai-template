package com.mos.example.core.service;

/**
 * 多数据源事务示例</br>
 * 本类仅用于演示用法，无实际意义
 * @author ly
 */
public interface DsTService {

    /**
     * 插入到数据源A
     *
     * @return
     */
    Integer insert2A();

    /**
     * 插入到数据源B
     *
     * @return
     */
    Integer insert2B();

}
