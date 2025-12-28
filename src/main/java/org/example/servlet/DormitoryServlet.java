package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Dormitory;
import org.example.service.DormitoryService;
import org.example.util.AuthUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 宿舍管理Servlet
 */
public class DormitoryServlet extends BaseServlet {

    private DormitoryService dormitoryService = new DormitoryService();

    /**
     * 查询所有宿舍（需要teacher/admin权限）
     */
    public void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        List<Dormitory> dormitories = dormitoryService.findAll();
        request.setAttribute("dormitories", dormitories);
        request.getRequestDispatcher("/WEB-INF/jsp/dormitory/list.jsp").forward(request, response);
    }

    /**
     * 添加宿舍页面（需要teacher/admin权限）
     */
    public void addPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        request.getRequestDispatcher("/WEB-INF/jsp/dormitory/add.jsp").forward(request, response);
    }

    /**
     * 添加宿舍（需要teacher/admin权限）
     */
    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        Dormitory dormitory = new Dormitory();
        dormitory.setBuilding(request.getParameter("building"));
        dormitory.setRoomNumber(request.getParameter("roomNumber"));
        dormitory.setCapacity(Integer.parseInt(request.getParameter("capacity")));
        dormitory.setCurrentCount(0);
        dormitory.setStatus(request.getParameter("status"));
        dormitory.setCreateTime(new Date());
        dormitory.setUpdateTime(new Date());

        boolean success = dormitoryService.add(dormitory);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/dormitory/list");
        } else {
            request.setAttribute("error", "添加失败");
            request.getRequestDispatcher("/WEB-INF/jsp/dormitory/add.jsp").forward(request, response);
        }
    }

    /**
     * 编辑宿舍页面（需要teacher/admin权限）
     */
    public void editPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        Integer id = Integer.parseInt(request.getParameter("id"));
        Dormitory dormitory = dormitoryService.findById(id);
        request.setAttribute("dormitory", dormitory);
        request.getRequestDispatcher("/WEB-INF/jsp/dormitory/edit.jsp").forward(request, response);
    }

    /**
     * 更新宿舍（需要teacher/admin权限）
     */
    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        Dormitory dormitory = new Dormitory();
        dormitory.setId(Integer.parseInt(request.getParameter("id")));
        dormitory.setBuilding(request.getParameter("building"));
        dormitory.setRoomNumber(request.getParameter("roomNumber"));
        dormitory.setCapacity(Integer.parseInt(request.getParameter("capacity")));
        dormitory.setCurrentCount(Integer.parseInt(request.getParameter("currentCount")));
        dormitory.setStatus(request.getParameter("status"));
        dormitory.setUpdateTime(new Date());

        boolean success = dormitoryService.update(dormitory);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/dormitory/list");
        } else {
            request.setAttribute("error", "更新失败");
            request.setAttribute("dormitory", dormitory);
            request.getRequestDispatcher("/WEB-INF/jsp/dormitory/edit.jsp").forward(request, response);
        }
    }

    /**
     * 删除宿舍（需要teacher/admin权限）
     */
    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        Integer id = Integer.parseInt(request.getParameter("id"));
        dormitoryService.delete(id);
        response.sendRedirect(request.getContextPath() + "/dormitory/list");
    }
}

