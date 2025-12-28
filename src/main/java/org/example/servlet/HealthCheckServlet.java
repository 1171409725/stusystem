package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.HealthCheck;
import org.example.entity.User;
import org.example.entity.Student;
import org.example.service.DormitoryService;
import org.example.service.HealthCheckService;
import org.example.service.StudentService;
import org.example.util.AuthUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 卫生检查Servlet
 */
public class HealthCheckServlet extends BaseServlet {

    private HealthCheckService healthCheckService = new HealthCheckService();
    private DormitoryService dormitoryService = new DormitoryService();
    private StudentService studentService = new StudentService();

    /**
     * 查询检查记录列表
     * 学生只能查看自己宿舍的，其他角色可以查看所有。
     */
    public void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkLogin(request, response)) {
            return;
        }

        User user = AuthUtil.getCurrentUser(request);
        List<HealthCheck> healthChecks;

        if ("student".equals(user.getRole())) {
            Student student = studentService.findByUserId(user.getId());
            if (student != null && student.getDormitoryId() != null) {
                healthChecks = healthCheckService.findByDormitoryId(student.getDormitoryId());
            } else {
                healthChecks = List.of(); // Return an empty list if student has no dorm
            }
        } else {
            healthChecks = healthCheckService.findAll();
        }

        request.setAttribute("healthChecks", healthChecks);
        request.getRequestDispatcher("/WEB-INF/jsp/healthcheck/list.jsp").forward(request, response);
    }

    /**
     * 显示添加检查记录页面
     */
    public void addPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "inspector,teacher,admin")) {
            return;
        }
        request.setAttribute("dormitories", dormitoryService.findAll());
        request.getRequestDispatcher("/WEB-INF/jsp/healthcheck/add.jsp").forward(request, response);
    }

    /**
     * 添加检查记录
     */
    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "inspector,teacher,admin")) {
            return;
        }

        User currentUser = AuthUtil.getCurrentUser(request);
        HealthCheck healthCheck = new HealthCheck();
        healthCheck.setDormitoryId(Integer.parseInt(request.getParameter("dormitoryId")));
        healthCheck.setCheckDate(request.getParameter("checkDate"));
        healthCheck.setScore(new BigDecimal(request.getParameter("score")));
        healthCheck.setComment(request.getParameter("comment"));
        healthCheck.setCheckerId(currentUser.getId());
        healthCheck.setCheckerName(currentUser.getName());
        healthCheck.setCreateTime(new Date());
        healthCheck.setUpdateTime(new Date());

        boolean success = healthCheckService.add(healthCheck);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/healthcheck/list");
        } else {
            request.setAttribute("error", "添加失败");
            request.setAttribute("dormitories", dormitoryService.findAll());
            request.getRequestDispatcher("/WEB-INF/jsp/healthcheck/add.jsp").forward(request, response);
        }
    }

    /**
     * 显示编辑检查记录页面
     */
    public void editPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "inspector,teacher,admin")) {
            return;
        }
        Integer id = Integer.parseInt(request.getParameter("id"));
        HealthCheck healthCheck = healthCheckService.findById(id);
        request.setAttribute("healthCheck", healthCheck);
        request.setAttribute("dormitories", dormitoryService.findAll());
        request.getRequestDispatcher("/WEB-INF/jsp/healthcheck/edit.jsp").forward(request, response);
    }

    /**
     * 更新检查记录
     */
    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "inspector,teacher,admin")) {
            return;
        }

        User currentUser = AuthUtil.getCurrentUser(request);
        HealthCheck healthCheck = new HealthCheck();
        healthCheck.setId(Integer.parseInt(request.getParameter("id")));
        healthCheck.setDormitoryId(Integer.parseInt(request.getParameter("dormitoryId")));
        healthCheck.setCheckDate(request.getParameter("checkDate"));
        healthCheck.setScore(new BigDecimal(request.getParameter("score")));
        healthCheck.setComment(request.getParameter("comment"));
        healthCheck.setCheckerId(currentUser.getId());
        healthCheck.setCheckerName(currentUser.getName());
        healthCheck.setUpdateTime(new Date());

        boolean success = healthCheckService.update(healthCheck);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/healthcheck/list");
        } else {
            request.setAttribute("error", "更新失败");
            request.setAttribute("healthCheck", healthCheck);
            request.setAttribute("dormitories", dormitoryService.findAll());
            request.getRequestDispatcher("/WEB-INF/jsp/healthcheck/edit.jsp").forward(request, response);
        }
    }

    /**
     * 删除检查记录
     */
    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "inspector,teacher,admin")) {
            return;
        }
        Integer id = Integer.parseInt(request.getParameter("id"));
        healthCheckService.delete(id);
        response.sendRedirect(request.getContextPath() + "/healthcheck/list");
    }
}
