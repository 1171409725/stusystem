<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学生管理</title>
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
    </style>
</head>
<body>
    <div class="container">
        <jsp:include page="/common/header.jsp"/>
        <h2>学生列表</h2>
        <a href="${pageContext.request.contextPath}/student/addPage" class="btn">添加学生</a>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>电话</th>
                    <th>班级</th>
                    <th>宿舍</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.id}</td>
                        <td>${student.studentNo}</td>
                        <td>${student.user.name}</td>
                        <td>${student.user.gender}</td>
                        <td>${student.user.phone != null ? student.user.phone : '-'}</td>
                        <td>${student.clazz != null ? student.clazz.className : '-'}</td>
                        <td>
                            <c:choose>
                                <c:when test="${student.dormitory != null}">
                                    ${student.dormitory.building}-${student.dormitory.roomNumber}
                                </c:when>
                                <c:otherwise>-</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="${pageContext.request.contextPath}/student/editPage?id=${student.id}" class="btn-edit">编辑</a>
                            <a href="${pageContext.request.contextPath}/student/delete?id=${student.id}" 
                               class="btn-delete" onclick="return confirm('确定要删除吗？')">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <jsp:include page="/common/footer.jsp"/>
    </div>
</body>
</html>
