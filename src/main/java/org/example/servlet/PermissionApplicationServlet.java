package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.PermissionApplication;
import org.example.entity.User;
import org.example.service.PermissionApplicationService;
import org.example.util.AuthUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 权限申请管理Servlet
 */
public class PermissionApplicationServlet extends BaseServlet {

    private PermissionApplicationService applicationService = new PermissionApplicationService();

    /**
     * 查看我的申请列表（学生）
     */
    public void myList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkLogin(request, response)) {
            return;
        }
        
        User user = AuthUtil.getCurrentUser(request);
        List<PermissionApplication> applications = applicationService.findByUserId(user.getId());
        request.setAttribute("applications", applications);
        request.getRequestDispatcher("/WEB-INF/jsp/permission/myList.jsp").forward(request, response);
    }

    /**
     * 提交申请页面（学生）
     */
    public void addPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkLogin(request, response)) {
            return;
        }
        
        User user = AuthUtil.getCurrentUser(request);
        if (!"student".equals(user.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "只有学生可以提交申请");
            return;
        }
        
        request.getRequestDispatcher("/WEB-INF/jsp/permission/add.jsp").forward(request, response);
    }

    /**
     * 提交申请（学生）
     */
    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkLogin(request, response)) {
            return;
        }
        
        User user = AuthUtil.getCurrentUser(request);
        if (!"student".equals(user.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "只有学生可以提交申请");
            return;
        }
        
        PermissionApplication application = new PermissionApplication();
        application.setUserId(user.getId());
        application.setApplicantName(user.getName());
        application.setApplicationType("inspector");
        application.setReason(request.getParameter("reason"));
        application.setStatus("pending");
        application.setApplyTime(new Date());

        boolean success = applicationService.add(application);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/permission/myList");
        } else {
            request.setAttribute("error", "提交失败");
            request.getRequestDispatcher("/WEB-INF/jsp/permission/add.jsp").forward(request, response);
        }
    }

    /**
     * 待审核申请列表（teacher/admin）
     */
    public void pendingList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        
        List<PermissionApplication> applications = applicationService.findByStatus("pending");
        request.setAttribute("applications", applications);
        request.getRequestDispatcher("/WEB-INF/jsp/permission/pendingList.jsp").forward(request, response);
    }

    /**
     * 审核申请页面（teacher/admin）
     */
    public void reviewPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        
        Integer id = Integer.parseInt(request.getParameter("id"));
        PermissionApplication application = applicationService.findById(id);
        request.setAttribute("application", application);
        request.getRequestDispatcher("/WEB-INF/jsp/permission/review.jsp").forward(request, response);
    }

    /**
     * 审核申请（通过/拒绝）（teacher/admin）
     */
    public void review(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        
        Integer id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status"); // approved 或 rejected
        String reviewComment = request.getParameter("reviewComment");
        
        User reviewer = AuthUtil.getCurrentUser(request);
        
        boolean success = applicationService.review(id, reviewer.getId(), reviewer.getName(), 
                                                    status, reviewComment);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/permission/pendingList");
        } else {
            request.setAttribute("error", "审核失败");
            request.getRequestDispatcher("/permission/reviewPage?id=" + id).forward(request, response);
        }
    }

    /**
     * 查看所有申请（teacher/admin）
     */
    public void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        
        List<PermissionApplication> applications = applicationService.findAll();
        request.setAttribute("applications", applications);
        request.getRequestDispatcher("/WEB-INF/jsp/permission/list.jsp").forward(request, response);
    }
}

