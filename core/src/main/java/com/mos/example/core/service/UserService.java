package com.mos.example.core.service;

import com.mos.example.common.base.RPage;
import com.mos.example.common.dto.UserChangePasswordDto;
import com.mos.example.common.dto.UserDto;
import com.mos.example.common.dto.query.UserQueryDto;
import com.mos.example.common.vo.UserVo;

/**
 * 用户服务
 * @author ly
 */
public interface UserService {

    /**
     * 新增用户
     *
     * @return
     */
    Integer insert(UserDto userDto);

    /**
     * 修改密码
     *
     * @param userChangePasswordDto
     */
    void changePassword(UserChangePasswordDto userChangePasswordDto);

    /**
     * 获取用户分页
     * @param userQueryDto
     * @return
     */
    RPage<UserVo> getPage(UserQueryDto userQueryDto);

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    UserVo getById(Integer userId);
}
