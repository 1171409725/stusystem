package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Class;
import org.example.service.ClassService;
import org.example.util.AuthUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 班级管理Servlet
 */
public class ClassServlet extends BaseServlet {

    private ClassService classService = new ClassService();

    /**
     * 查询所有班级（需要teacher/admin权限）
     */
    public void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        
        List<Class> classes = classService.findAll();
        request.setAttribute("classes", classes);
        request.getRequestDispatcher("/WEB-INF/jsp/class/list.jsp").forward(request, response);
    }

    /**
     * 添加班级页面（需要teacher/admin权限）
     */
    public void addPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        request.getRequestDispatcher("/WEB-INF/jsp/class/add.jsp").forward(request, response);
    }

    /**
     * 添加班级（需要teacher/admin权限）
     */
    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        
        Class clazz = new Class();
        clazz.setClassNo(request.getParameter("classNo"));
        clazz.setClassName(request.getParameter("className"));
        clazz.setMajor(request.getParameter("major"));
        clazz.setGrade(request.getParameter("grade"));
        clazz.setDescription(request.getParameter("description"));
        clazz.setCreateTime(new Date());
        clazz.setUpdateTime(new Date());

        boolean success = classService.add(clazz);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/class/list");
        } else {
            request.setAttribute("error", "添加失败，班级编号可能已存在");
            request.getRequestDispatcher("/WEB-INF/jsp/class/add.jsp").forward(request, response);
        }
    }

    /**
     * 编辑班级页面（需要teacher/admin权限）
     */
    public void editPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        
        Integer id = Integer.parseInt(request.getParameter("id"));
        Class clazz = classService.findById(id);
        request.setAttribute("clazz", clazz);
        request.getRequestDispatcher("/WEB-INF/jsp/class/edit.jsp").forward(request, response);
    }

    /**
     * 更新班级（需要teacher/admin权限）
     */
    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        
        Class clazz = new Class();
        clazz.setId(Integer.parseInt(request.getParameter("id")));
        clazz.setClassNo(request.getParameter("classNo"));
        clazz.setClassName(request.getParameter("className"));
        clazz.setMajor(request.getParameter("major"));
        clazz.setGrade(request.getParameter("grade"));
        clazz.setDescription(request.getParameter("description"));
        clazz.setUpdateTime(new Date());

        boolean success = classService.update(clazz);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/class/list");
        } else {
            request.setAttribute("error", "更新失败");
            request.setAttribute("clazz", clazz);
            request.getRequestDispatcher("/WEB-INF/jsp/class/edit.jsp").forward(request, response);
        }
    }

    /**
     * 删除班级（需要teacher/admin权限）
     */
    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        
        Integer id = Integer.parseInt(request.getParameter("id"));
        classService.delete(id);
        response.sendRedirect(request.getContextPath() + "/class/list");
    }
}

