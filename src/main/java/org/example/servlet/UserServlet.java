package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.User;
import org.example.service.UserService;
import org.example.util.AuthUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 用户管理Servlet
 */
public class UserServlet extends BaseServlet {

    private UserService userService = new UserService();

    /**
     * 查询所有用户（需要teacher/admin权限）
     */
    public void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/jsp/user/list.jsp").forward(request, response);
    }

    /**
     * 添加用户页面（需要teacher/admin权限）
     */
    public void addPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        request.getRequestDispatcher("/WEB-INF/jsp/user/add.jsp").forward(request, response);
    }

    /**
     * 添加用户（需要teacher/admin权限）
     */
    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password")); // 明码
        user.setRole(request.getParameter("role"));
        user.setName(request.getParameter("name"));
        user.setGender(request.getParameter("gender"));
        user.setPhone(request.getParameter("phone"));
        user.setEmail(request.getParameter("email"));
        user.setStatus("active");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        boolean success = userService.add(user);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/user/list");
        } else {
            request.setAttribute("error", "添加失败，用户名可能已存在");
            request.getRequestDispatcher("/WEB-INF/jsp/user/add.jsp").forward(request, response);
        }
    }

    /**
     * 编辑用户页面（需要teacher/admin权限）
     */
    public void editPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        
        Integer id = Integer.parseInt(request.getParameter("id"));
        User user = userService.findById(id);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(request, response);
    }

    /**
     * 更新用户（需要teacher/admin权限）
     */
    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        
        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        user.setUsername(request.getParameter("username"));
        user.setRole(request.getParameter("role"));
        user.setName(request.getParameter("name"));
        user.setGender(request.getParameter("gender"));
        user.setPhone(request.getParameter("phone"));
        user.setEmail(request.getParameter("email"));
        user.setStatus(request.getParameter("status"));
        user.setUpdateTime(new Date());

        boolean success = userService.update(user);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/user/list");
        } else {
            request.setAttribute("error", "更新失败");
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/jsp/user/edit.jsp").forward(request, response);
        }
    }

    /**
     * 删除用户（需要admin权限）
     */
    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "admin")) {
            return;
        }
        
        Integer id = Integer.parseInt(request.getParameter("id"));
        userService.delete(id);
        response.sendRedirect(request.getContextPath() + "/user/list");
    }
}

