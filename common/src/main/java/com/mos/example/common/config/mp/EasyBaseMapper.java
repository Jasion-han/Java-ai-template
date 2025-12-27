package com.mos.example.common.config.mp;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

/**
 * BaseMapper 扩展接口
 *
 * @author Han
 */
public interface EasyBaseMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入
     */
    Integer insertBatchSomeColumn(Collection<T> entityList);
}
