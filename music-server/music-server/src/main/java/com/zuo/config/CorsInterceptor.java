package com.zuo.config;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //给访问的ip和端口放行,request.getHeader("Origin")获取访问的地址和端口
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        //允许POST, GET, OPTIONS, DELETE访问
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        //预检结果缓存时间,也就是上面说到的缓存啦
        response.setHeader("Access-Control-Max-Age", "3600");
        //允许的请求头字段
        response.setHeader("Access-Control-Allow-Headers", "x_requested_with,x-requested-with,Authorization,Content-Type,token");
        //是否允许后续请求携带认证信息（cookies）,该值只能是true,否则不返回
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return true;
    }
}
