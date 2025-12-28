package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 权限认证过滤器
 */
public class AuthFilter implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        String path = uri.substring(contextPath.length());
        
        // 排除不需要认证的路径
        if (path.equals("/") || path.equals("/login") || path.equals("/login.jsp") 
            || path.startsWith("/static/") || path.startsWith("/css/") || path.startsWith("/js/")
            || path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".jpg") || path.endsWith(".png")) {
            chain.doFilter(request, response);
            return;
        }
        
        // 检查是否已登录
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        
        if (user == null) {
            // 未登录，跳转到登录页
            resp.sendRedirect(contextPath + "/login.jsp");
            return;
        }
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}

