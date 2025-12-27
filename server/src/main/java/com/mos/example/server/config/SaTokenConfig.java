package com.mos.example.server.config;

import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.mos.example.common.base.HttpStatusEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SaToken 配置
 *
 * @author Han
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .addExclude("/favicon.ico", "/index.html",
                        // 放行认证相关
                        "/auth/**", "/dst/**",
                        // 放行 druid 控制台
                        "/druid/**",
                        // 放行 knife4j 文档
                        "/doc.html", "/webjars/**", "/swagger-resources/**", "/v3/api-docs/**")
                .setAuth(obj -> {
                    if (!StpUtil.isLogin()) {
                        SaRouter.back(SaResult.error()
                                .setCode(HttpStatusEnum.UNAUTHORIZED.getCode())
                                .setMsg(HttpStatusEnum.UNAUTHORIZED.getMessage()));
                    }
                });
    }
}
