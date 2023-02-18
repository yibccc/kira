//package com.kira.filter;
//
//import com.alibaba.druid.support.json.JSONUtils;
//import com.alibaba.fastjson.JSON;
//import com.kira.controller.utils.R;
//import com.sun.deploy.net.HttpResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//
///**
// * @author Kira
// * @create 2023-02-010:04
// */
////登录检查
//@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
//@Slf4j
//public class LoginCheckFilter implements Filter {
//
//    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest)servletRequest;
//        HttpServletResponse response = (HttpServletResponse)servletResponse;
//        //获取URI
//        String requestURI = request.getRequestURI();
//
//        log.info("拦截到请求：{}",requestURI);
//
//
//        String[] urls = new String[]{
//                "/users/login",
//                "/users/logout",
//                "/users/register",
//                "/static/**",
//                "/**/*.js",
//                "/**/*.css",
//                "/**/*.ico",
//                "/**/*.html",
//                "/**/*.png",
//                "/**/*.ttf",
//                "/**/*.woff"
//        };
//
//        //判断是否需要处理
//        boolean cheak = cheak(urls, requestURI);
//
//        //不需要处理则放行
//        if (cheak){
//            log.info("本次请求不需要处理",requestURI);
//            filterChain.doFilter(request,response);
//            return;
//
//        }
//        //判断是否登录（登录则放行）
//        if (request.getSession().getAttribute("user")!=null){
//            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("user"));
//
//            filterChain.doFilter(request,response);
//            return;
//        }
//        log.info("用户未登录");
//        //5、如果未登录则返回未登录结果，通过输出流方式向客户端页面响应数据
//        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
////        filterChain.doFilter(request,response);
//        return;
//    }
//
//    public boolean cheak(String[] urls,String requestURI){
//        for (String url : urls) {
//            boolean match = PATH_MATCHER.match(url, requestURI);
//            if(match){
//                return true;
//            }
//        }
//        return false;
//    }
//}
