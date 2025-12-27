package com.mos.example.common.config.mp;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus 配置
 *
 * @author mos
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 配置拦截器
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        // 乐观锁拦截器
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return interceptor;
    }

    /**
     * 配置自定义SQL注入器<br/>
     * 用于注入自定义的SQL方法，如批量插入等
     * @return
     */
    @Bean
    public EasySqlInjector easySqlInjector() {
        return new EasySqlInjector();
    }
}
