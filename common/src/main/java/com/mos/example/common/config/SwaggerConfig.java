package com.mos.example.common.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger 配置
 * @author Han
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(
                new Info().title("基础手脚架")
                    .contact(new Contact().name("mos").email("xxx@xx.com").url("https://www.baidu.com/"))
                    .description("用于快速构建项目")
                    .version("v1.0")
            )
            .externalDocs(new ExternalDocumentation().description("可以跳转的外部地址").url("https://www.baidu.com/"));
    }
}
