package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.entity.User;
import org.example.entity.Student;
import org.example.entity.HealthCheck;
import org.example.service.UserService;
import org.example.service.StudentService;
import org.example.service.HealthCheckService;
import org.example.util.AuthUtil;

import java.io.IOException;
import java.util.List;

/**
 * 个人信息管理Servlet
 */
public class ProfileServlet extends BaseServlet {

    private UserService userService = new UserService();
    private StudentService studentService = new StudentService();
    private HealthCheckService healthCheckService = new HealthCheckService();

    /**
     * 默认方法，重定向到view
     */
    public void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        view(request, response);
    }

    /**
     * 查看个人信息
     */
    public void view(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkLogin(request, response)) {
            return;
        }
        
        User user = AuthUtil.getCurrentUser(request);
        User currentUser = userService.findById(user.getId());
        request.setAttribute("user", currentUser);
        
        // 如果是学生，查询学生信息和宿舍检查记录
        if ("student".equals(user.getRole())) {
            Student student = studentService.findByUserId(user.getId());
            request.setAttribute("student", student);
            
            if (student != null && student.getDormitoryId() != null) {
                List<HealthCheck> healthChecks = healthCheckService.findByDormitoryId(student.getDormitoryId());
                request.setAttribute("healthChecks", healthChecks);
            }
        }
        
        request.getRequestDispatcher("/WEB-INF/jsp/profile/view.jsp").forward(request, response);
    }

    /**
     * 修改个人信息页面
     */
    public void editPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkLogin(request, response)) {
            return;
        }
        
        User user = AuthUtil.getCurrentUser(request);
        User currentUser = userService.findById(user.getId());
        request.setAttribute("user", currentUser);
        request.getRequestDispatcher("/WEB-INF/jsp/profile/edit.jsp").forward(request, response);
    }

    /**
     * 更新个人信息（只能修改自己的信息）
     */
    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkLogin(request, response)) {
            return;
        }
        
        User currentUser = AuthUtil.getCurrentUser(request);
        Integer id = Integer.parseInt(request.getParameter("id"));
        
        // 只能修改自己的信息
        if (!id.equals(currentUser.getId())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "您只能修改自己的信息");
            return;
        }
        
        User user = new User();
        user.setId(id);
        user.setPhone(request.getParameter("phone"));
        user.setEmail(request.getParameter("email"));
        user.setUpdateTime(new java.util.Date());
        
        // 保持其他字段不变
        User existingUser = userService.findById(id);
        user.setUsername(existingUser.getUsername());
        user.setPassword(existingUser.getPassword());
        user.setRole(existingUser.getRole());
        user.setName(existingUser.getName());
        user.setGender(existingUser.getGender());
        user.setStatus(existingUser.getStatus());
        
        boolean success = userService.update(user);
        if (success) {
            // 更新session中的用户信息
            HttpSession session = request.getSession();
            session.setAttribute("user", userService.findById(id));
            response.sendRedirect(request.getContextPath() + "/profile/view");
        } else {
            request.setAttribute("error", "更新失败");
            request.setAttribute("user", existingUser);
            request.getRequestDispatcher("/WEB-INF/jsp/profile/edit.jsp").forward(request, response);
        }
    }

    /**
     * 修改密码页面
     */
    public void changePasswordPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkLogin(request, response)) {
            return;
        }
        request.getRequestDispatcher("/WEB-INF/jsp/profile/changePassword.jsp").forward(request, response);
    }

    /**
     * 修改密码
     */
    public void changePassword(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkLogin(request, response)) {
            return;
        }
        
        User currentUser = AuthUtil.getCurrentUser(request);
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        
        // 验证旧密码
        User user = userService.findById(currentUser.getId());
        if (!oldPassword.equals(user.getPassword())) {
            request.setAttribute("error", "原密码错误");
            request.getRequestDispatcher("/WEB-INF/jsp/profile/changePassword.jsp").forward(request, response);
            return;
        }
        
        // 验证新密码
        if (newPassword == null || newPassword.isEmpty()) {
            request.setAttribute("error", "新密码不能为空");
            request.getRequestDispatcher("/WEB-INF/jsp/profile/changePassword.jsp").forward(request, response);
            return;
        }
        
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "两次输入的密码不一致");
            request.getRequestDispatcher("/WEB-INF/jsp/profile/changePassword.jsp").forward(request, response);
            return;
        }
        
        // 更新密码
        boolean success = userService.updatePassword(currentUser.getId(), newPassword);
        if (success) {
            request.setAttribute("success", "密码修改成功");
            request.getRequestDispatcher("/WEB-INF/jsp/profile/changePassword.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "密码修改失败");
            request.getRequestDispatcher("/WEB-INF/jsp/profile/changePassword.jsp").forward(request, response);
        }
    }
}

