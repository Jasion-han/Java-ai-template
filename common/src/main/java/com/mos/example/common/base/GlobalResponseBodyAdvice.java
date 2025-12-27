package com.mos.example.common.base;

import com.mos.example.common.utils.JsonUtil;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一返回结果<br/>
 * 指定 basePackages 避免影响 swagger 文档<br/>
 * 指定@Hidden注解让文档不扫描当前类
 *
 * @author Han
 */
@Hidden
@RestControllerAdvice(basePackages = {"com.mos.base.server.controller"})
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        if (body instanceof Result) {
            return body;
        }

        if (body instanceof String) {
            return JsonUtil.toJSONString(Result.success(body));
        }

        return Result.success(body);
    }
}
