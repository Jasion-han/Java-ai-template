package com.mos.example.server.controller;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.mos.example.core.service.DsTService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 多数据源事务示例接口
 * 本类仅用于演示用法，无实际意义
 *
 * @author Han
 */
@RestController
@RequestMapping("dst")
@RequiredArgsConstructor
@Tag(name = "000.多数据源事务示例")
public class DsTController {

    private final DsTService dsTService;

    @GetMapping("start")
    @Operation(summary = "入口")
    @DSTransactional
    public void start() {
        dsTService.insert2A();
        // 单源失败回滚测试
        // System.out.println(2 / 0);
        dsTService.insert2B();
        // 多源失败回滚测试
        System.out.println(2 / 0);
    }
}
