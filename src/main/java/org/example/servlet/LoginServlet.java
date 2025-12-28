package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.entity.User;
import org.example.entity.Student;
import org.example.service.UserService;
import org.example.service.StudentService;

import java.io.IOException;

/**
 * 登录Servlet
 */
public class LoginServlet extends HttpServlet {
    
    private UserService userService = new UserService();
    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 如果已登录，跳转到首页
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "用户名和密码不能为空");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        
        // 查询用户
        User user = userService.findByUsername(username);
        if (user == null) {
            request.setAttribute("error", "用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        
        // 验证密码（明码）
        if (!password.equals(user.getPassword())) {
            request.setAttribute("error", "用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        
        // 检查状态
        if (!"active".equals(user.getStatus())) {
            request.setAttribute("error", "账户已被禁用");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        
        // 登录成功，保存用户信息到Session
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        
        // 如果是学生，查询学生信息
        if ("student".equals(user.getRole())) {
            Student student = studentService.findByUserId(user.getId());
            if (student != null) {
                session.setAttribute("student", student);
            }
        }
        
        // 根据角色跳转到不同页面
        String redirectPath = request.getContextPath() + "/index.jsp";
        response.sendRedirect(redirectPath);
    }
}

