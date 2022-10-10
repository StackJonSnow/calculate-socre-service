package com.snow.cs.interceptor;

import com.snow.cs.enums.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JonSnow
 * @description 异常拦截
 * @date 2022/10/10
 */
@Slf4j
@ControllerAdvice(basePackages = "com.snow")
public class ExceptionInterceptor {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object defaultErrorHandler(Exception e, HandlerMethod handlerMethod, HttpServletRequest request) {

        String requestUri = request.getRequestURI();
        log.error("There is exception happening; url: {}", requestUri, e);

        return ResponseEnum.cast(e).process(e);
    }
}
