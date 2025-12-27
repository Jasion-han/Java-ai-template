package com.mos.example.core.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.mos.example.common.domain.User;
import com.mos.example.core.mapper.UserMapper;
import com.mos.example.core.service.DsTService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 多数据源事务示例
 * 本类仅用于演示用法，无实际意义
 *
 * @author Han
 */
@Service
@RequiredArgsConstructor
public class DsTServiceImpl implements DsTService {

    private final UserMapper userMapper;

    @Override
    @DS("master")
    public Integer insert2A() {
        String now = "A" + LocalDateTime.now();
        User user = new User();
        user.setNumber(now);
        user.setNickname(now);
        userMapper.insert(user);
        return user.getUserId();
    }

    @Override
    @DS("test")
    public Integer insert2B() {
        String now = "B" + LocalDateTime.now();
        User user = new User();
        user.setNumber(now);
        user.setNickname(now);
        userMapper.insert(user);
        return user.getUserId();
    }
}
