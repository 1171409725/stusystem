package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.Student;
import org.example.service.DormitoryService;
import org.example.service.StudentService;
import org.example.service.ClassService;
import org.example.service.UserService;
import org.example.entity.User;
import org.example.util.AuthUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 学生管理Servlet
 */
public class StudentServlet extends BaseServlet {

    private StudentService studentService = new StudentService();
    private DormitoryService dormitoryService = new DormitoryService();
    private ClassService classService = new ClassService();
    private UserService userService = new UserService();

    /**
     * 查询所有学生（需要teacher/admin权限）
     */
    public void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        List<Student> students = studentService.findAll();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/WEB-INF/jsp/student/list.jsp").forward(request, response);
    }

    /**
     * 添加学生页面（需要teacher/admin权限）
     */
    public void addPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        request.setAttribute("dormitories", dormitoryService.findAll());
        request.setAttribute("classes", classService.findAll());
        request.getRequestDispatcher("/WEB-INF/jsp/student/add.jsp").forward(request, response);
    }

    /**
     * 添加学生（需要teacher/admin权限）
     */
    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        
        // 先创建用户
        User user = new User();
        user.setUsername(request.getParameter("studentNo"));
        user.setPassword("123456"); // 默认密码
        user.setRole("student");
        user.setName(request.getParameter("name"));
        user.setGender(request.getParameter("gender"));
        user.setPhone(request.getParameter("phone"));
        user.setEmail(request.getParameter("email"));
        user.setStatus("active");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        
        boolean userSuccess = userService.add(user);
        if (!userSuccess) {
            request.setAttribute("error", "添加失败，学号可能已存在");
            request.setAttribute("dormitories", dormitoryService.findAll());
            request.setAttribute("classes", classService.findAll());
            request.getRequestDispatcher("/WEB-INF/jsp/student/add.jsp").forward(request, response);
            return;
        }
        
        // 再创建学生记录
        Student student = new Student();
        student.setUserId(user.getId());
        student.setStudentNo(request.getParameter("studentNo"));
        String classIdStr = request.getParameter("classId");
        if (classIdStr != null && !classIdStr.isEmpty()) {
            student.setClassId(Integer.parseInt(classIdStr));
        }
        String dormitoryIdStr = request.getParameter("dormitoryId");
        if (dormitoryIdStr != null && !dormitoryIdStr.isEmpty()) {
            student.setDormitoryId(Integer.parseInt(dormitoryIdStr));
        }
        student.setCreateTime(new Date());
        student.setUpdateTime(new Date());

        boolean success = studentService.add(student);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/student/list");
        } else {
            // 删除已创建的用户
            userService.delete(user.getId());
            request.setAttribute("error", "添加失败");
            request.setAttribute("dormitories", dormitoryService.findAll());
            request.setAttribute("classes", classService.findAll());
            request.getRequestDispatcher("/WEB-INF/jsp/student/add.jsp").forward(request, response);
        }
    }

    /**
     * 编辑学生页面（需要teacher/admin权限）
     */
    public void editPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        Integer id = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.findById(id);
        request.setAttribute("student", student);
        request.setAttribute("dormitories", dormitoryService.findAll());
        request.setAttribute("classes", classService.findAll());
        request.getRequestDispatcher("/WEB-INF/jsp/student/edit.jsp").forward(request, response);
    }

    /**
     * 更新学生（需要teacher/admin权限）
     */
    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        Integer id = Integer.parseInt(request.getParameter("id"));
        Student existingStudent = studentService.findById(id);
        if (existingStudent == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "学生不存在");
            return;
        }
        
        // 更新用户信息
        User user = userService.findById(existingStudent.getUserId());
        if (user != null) {
            user.setName(request.getParameter("name"));
            user.setGender(request.getParameter("gender"));
            user.setPhone(request.getParameter("phone"));
            user.setEmail(request.getParameter("email"));
            user.setUpdateTime(new Date());
            userService.update(user);
        }
        
        // 更新学生信息
        Student student = new Student();
        student.setId(id);
        student.setStudentNo(request.getParameter("studentNo"));
        String classIdStr = request.getParameter("classId");
        if (classIdStr != null && !classIdStr.isEmpty()) {
            student.setClassId(Integer.parseInt(classIdStr));
        }
        String dormitoryIdStr = request.getParameter("dormitoryId");
        if (dormitoryIdStr != null && !dormitoryIdStr.isEmpty()) {
            student.setDormitoryId(Integer.parseInt(dormitoryIdStr));
        }
        student.setUpdateTime(new Date());

        boolean success = studentService.update(student);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/student/list");
        } else {
            request.setAttribute("error", "更新失败");
            request.setAttribute("student", existingStudent);
            request.setAttribute("dormitories", dormitoryService.findAll());
            request.setAttribute("classes", classService.findAll());
            request.getRequestDispatcher("/WEB-INF/jsp/student/edit.jsp").forward(request, response);
        }
    }

    /**
     * 删除学生（需要teacher/admin权限）
     */
    public void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!AuthUtil.checkRole(request, response, "teacher,admin")) {
            return;
        }
        Integer id = Integer.parseInt(request.getParameter("id"));
        Student student = studentService.findById(id);
        if (student != null) {
            // 删除学生记录（会级联删除用户）
            studentService.delete(id);
        }
        response.sendRedirect(request.getContextPath() + "/student/list");
    }
}

