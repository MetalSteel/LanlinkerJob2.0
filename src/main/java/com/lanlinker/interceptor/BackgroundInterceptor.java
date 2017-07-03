package com.lanlinker.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台登录拦截器
 */
public class BackgroundInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 判断是否是后台登录页
        String requestURI = httpServletRequest.getRequestURI();
        // 判断是否是登录页面,如果是登录页返回True,否则继续执行判断
        if("/background/login".equals(requestURI)){
            return true;
        }
        // 判断是否是登录请求
        if("/background/loginSystem".equals(requestURI)){
            return true;
        }
        // 判断是否用户已经登录
        if(httpServletRequest.getSession().getAttribute("admin")!=null){
            return true;
        }else {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/background/login");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
