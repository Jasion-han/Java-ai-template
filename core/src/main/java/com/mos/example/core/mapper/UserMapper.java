package com.mos.example.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mos.example.common.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ly
 * @since 2025/5/16
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
