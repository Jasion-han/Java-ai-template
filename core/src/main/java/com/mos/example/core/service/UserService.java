package com.mos.example.core.service;

import com.mos.example.common.base.RPage;
import com.mos.example.common.dto.UserChangePasswordDTO;
import com.mos.example.common.dto.UserDTO;
import com.mos.example.common.dto.query.UserQueryDTO;
import com.mos.example.common.vo.UserVo;

/**
 * 用户服务
 * @author Han
 */
public interface UserService {

    /**
     * 新增用户
     *
     * @param userDTO 用户 DTO
     * @return 插入成功的行数
     */
    Integer insert(UserDTO userDTO);

    /**
     * 修改密码
     *
     * @param userChangePasswordDTO 用户修改密码 DTO
     */
    void changePassword(UserChangePasswordDTO userChangePasswordDTO);

    /**
     * 获取用户分页
     * @param userQueryDTO 用户查询 DTO
     * @return 用户分页信息
     */
    RPage<UserVo> getPage(UserQueryDTO userQueryDTO);

    /**
     * 获取用户信息
     * @param userId 用户 ID
     * @return 用户信息
     */
    UserVo getById(Integer userId);
}
