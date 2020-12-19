//package com.soe.langya.filter;
//
//import com.soe.langya.pojo.Role;
//import com.soe.langya.pojo.User;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.List;
//
//@WebFilter(urlPatterns = "/*", filterName = "rest1AuthFilter")
//public class LoginFilter  implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("LoginFilter:doFilter");
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//        String requestURI = httpServletRequest.getRequestURI();
//
//        HttpSession session = httpServletRequest.getSession();
//        User user = (User) ((HttpServletRequest) request).getSession().getAttribute("account");
//        System.out.println("test " + user);
//        if (user != null) {
//            System.out.println("进入1");
//            List<Role> roles = user.getRoles();
//                System.out.println("filter end ==========================");
//                chain.doFilter(request,response);
//        } else if (requestURI.endsWith("login") || requestURI.endsWith("reg")) {
//            System.out.println("进如2");
//            System.out.println("filter end ==========================");
//            chain.doFilter(request,response);
//        }
//
//    }
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
