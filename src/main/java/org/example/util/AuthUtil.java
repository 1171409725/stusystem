package org.example.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.entity.User;

import java.io.IOException;

/**
 * 权限检查工具类
 */
public class AuthUtil {
    
    /**
     * 检查用户是否登录
     */
    public static User getCurrentUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (User) session.getAttribute("user");
    }
    
    /**
     * 检查是否已登录，未登录则跳转到登录页
     */
    public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = getCurrentUser(request);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return false;
        }
        return true;
    }
    
    /**
     * 检查用户角色权限
     * @param request 请求
     * @param response 响应
     * @param requiredRoles 需要的角色（多个用逗号分隔，如 "teacher,admin"）
     * @return 是否有权限
     */
    public static boolean checkRole(HttpServletRequest request, HttpServletResponse response, String requiredRoles) throws IOException {
        User user = getCurrentUser(request);
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return false;
        }
        
        String userRole = user.getRole();
        String[] roles = requiredRoles.split(",");
        
        for (String role : roles) {
            if (role.trim().equals(userRole)) {
                return true;
            }
        }
        
        // 权限检查：admin > teacher > inspector > student
        if ("admin".equals(userRole) && requiredRoles.contains("teacher")) {
            return true;
        }
        if ("teacher".equals(userRole) && requiredRoles.contains("inspector")) {
            return true;
        }
        if ("inspector".equals(userRole) && requiredRoles.contains("student")) {
            return true;
        }
        if ("admin".equals(userRole) && requiredRoles.contains("inspector")) {
            return true;
        }
        if ("admin".equals(userRole) && requiredRoles.contains("student")) {
            return true;
        }
        if ("teacher".equals(userRole) && requiredRoles.contains("student")) {
            return true;
        }
        
        // 无权限
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "您没有权限访问此功能");
        return false;
    }
    
    /**
     * 检查是否为指定角色
     */
    public static boolean isRole(HttpServletRequest request, String role) {
        User user = getCurrentUser(request);
        return user != null && role.equals(user.getRole());
    }
    
    /**
     * 检查是否有指定角色之一
     */
    public static boolean hasAnyRole(HttpServletRequest request, String... roles) {
        User user = getCurrentUser(request);
        if (user == null) {
            return false;
        }
        String userRole = user.getRole();
        for (String role : roles) {
            if (role.equals(userRole)) {
                return true;
            }
        }
        return false;
    }
}

