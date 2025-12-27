package com.mos.example.server.controller;

import com.mos.example.common.base.RPage;
import com.mos.example.common.dto.UserChangePasswordDto;
import com.mos.example.common.dto.UserDto;
import com.mos.example.common.dto.query.UserQueryDto;
import com.mos.example.common.vo.UserVo;
import com.mos.example.core.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户接口
 *
 * @author ly
 */
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Tag(name = "002.用户管理")
public class UserController {

    private final UserService userService;

    @PostMapping("insert")
    @Operation(summary = "新增用户")
    public Integer insert(@RequestBody @Valid UserDto userDto) {
        return userService.insert(userDto);
    }

    @PostMapping("changePassword")
    @Operation(summary = "修改登陆密码")
    public void changePassword(@RequestBody @Valid UserChangePasswordDto userChangePasswordDto) {
        userService.changePassword(userChangePasswordDto);
    }

    @PostMapping("getPage")
    @Operation(summary = "获取用户分页")
    public RPage<UserVo> getPage(@RequestBody UserQueryDto userQueryDto) {
        return userService.getPage(userQueryDto);
    }

    @GetMapping("getById/{id}")
    @Operation(summary = "获取用户信息")
    public UserVo getById(@PathVariable("id") Integer id) {
        return userService.getById(id);
    }
}
