package com.mos.example.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mos.example.common.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Han
 * @since 2025/12/27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
