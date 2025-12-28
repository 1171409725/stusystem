package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 基础Servlet，实现方法分发
 */
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取请求的方法名
        String methodName = request.getParameter("action");
        if (methodName == null || methodName.isEmpty()) {
            // 从URI中提取方法名，如 /profile/view -> view, /dormitory/list -> list
            String uri = request.getRequestURI();
            String contextPath = request.getContextPath();
            String path = uri.substring(contextPath.length());
            
            // 去掉开头的 / 和查询参数
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            if (path.contains("?")) {
                path = path.substring(0, path.indexOf("?"));
            }
            
            // 分割路径，提取最后一个路径段作为方法名
            String[] parts = path.split("/");
            if (parts.length >= 2 && !parts[parts.length - 1].isEmpty()) {
                // 例如: profile/view -> view, dormitory/list -> list
                methodName = parts[parts.length - 1];
            } else if (parts.length == 1 && !parts[0].isEmpty()) {
                // 例如: profile -> 默认调用list
                methodName = "list";
            } else {
                methodName = "list"; // 默认方法
            }
            
            // 调试信息（生产环境可以删除）
            System.out.println("BaseServlet - URI: " + uri);
            System.out.println("BaseServlet - Path: " + path);
            System.out.println("BaseServlet - Method: " + methodName);
        }

        try {
            // 获取当前类的Class对象
            Class<? extends BaseServlet> clazz = this.getClass();
            // 获取方法对象
            Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 调用方法
            method.invoke(this, request, response);
        } catch (NoSuchMethodException e) {
            // 如果找不到方法，尝试调用list方法
            try {
                Class<? extends BaseServlet> clazz = this.getClass();
                Method method = clazz.getMethod("list", HttpServletRequest.class, HttpServletResponse.class);
                method.invoke(this, request, response);
            } catch (NoSuchMethodException ex) {
                // 如果连list方法都没有，返回404
                if (!response.isCommitted()) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "方法不存在: " + methodName);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                if (!response.isCommitted()) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "方法调用失败: " + methodName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (!response.isCommitted()) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "方法调用失败: " + methodName + ": " + e.getMessage());
            }
        }
    }
}

