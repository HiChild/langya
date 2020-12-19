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
//@WebFilter(urlPatterns = "/check/*", filterName = "rest5Access2Filter")
//public class AccessFilter2 implements Filter {
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        System.out.println("AccessFilter2:doFilter");
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String requestURI = httpServletRequest.getRequestURI();
//        HttpSession session = httpServletRequest.getSession();
//        User user = (User) session.getAttribute("account");
//        if (user != null) {
//            List<Role> roles = user.getRoles();
//            if (roles != null) {
//                for (Role role : roles) {
//                    if (role.getName().equals("审核员")){
//                        chain.doFilter(request,response);
//                    }
//                }
//            }
//        }
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
