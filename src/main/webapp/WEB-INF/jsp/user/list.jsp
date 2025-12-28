<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户管理</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: "Microsoft YaHei", Arial, sans-serif; padding: 20px; background: #f5f5f5; }
        .container { max-width: 1200px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; }
        h2 { margin-bottom: 20px; color: #333; }
        .btn { display: inline-block; padding: 10px 20px; background: #667eea; color: white; text-decoration: none; border-radius: 5px; margin-bottom: 20px; }
        .btn:hover { background: #5568d3; }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
        th { background: #f8f9fa; font-weight: bold; }
        tr:hover { background: #f5f5f5; }
        .btn-edit, .btn-delete { padding: 5px 15px; text-decoration: none; border-radius: 3px; margin-right: 5px; }
        .btn-edit { background: #28a745; color: white; }
        .btn-delete { background: #dc3545; color: white; }
        .btn-edit:hover { background: #218838; }
        .btn-delete:hover { background: #c82333; }
        .role-badge { padding: 3px 8px; border-radius: 3px; font-size: 12px; }
        .role-student { background: #17a2b8; color: white; }
        .role-inspector { background: #ffc107; color: #333; }
        .role-teacher { background: #28a745; color: white; }
        .role-admin { background: #dc3545; color: white; }
        .status-active { color: #28a745; }
        .status-inactive { color: #dc3545; }
    </style>
</head>
<body>
    <div class="container">
        <jsp:include page="/common/header.jsp"/>
        <h2>用户列表</h2>
        <a href="${pageContext.request.contextPath}/user/addPage" class="btn">添加用户</a>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>用户名</th>
                    <th>姓名</th>
                    <th>角色</th>
                    <th>性别</th>
                    <th>电话</th>
                    <th>邮箱</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.name}</td>
                        <td>
                            <span class="role-badge role-${user.role}">
                                <c:choose>
                                    <c:when test="${user.role == 'student'}">学生</c:when>
                                    <c:when test="${user.role == 'inspector'}">检查员</c:when>
                                    <c:when test="${user.role == 'teacher'}">教师</c:when>
                                    <c:when test="${user.role == 'admin'}">管理员</c:when>
                                </c:choose>
                            </span>
                        </td>
                        <td>${user.gender}</td>
                        <td>${user.phone}</td>
                        <td>${user.email}</td>
                        <td class="status-${user.status}">${user.status == 'active' ? '启用' : '禁用'}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/user/editPage?id=${user.id}" class="btn-edit">编辑</a>
                            <c:if test="${sessionScope.user.role == 'admin'}">
                                <a href="${pageContext.request.contextPath}/user/delete?id=${user.id}" 
                                   class="btn-delete" onclick="return confirm('确定要删除吗？')">删除</a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <jsp:include page="/common/footer.jsp"/>
    </div>
</body>
</html>
