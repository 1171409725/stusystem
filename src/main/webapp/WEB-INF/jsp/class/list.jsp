<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>班级管理</title>
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
        <h2>班级列表</h2>
        <a href="${pageContext.request.contextPath}/class/addPage" class="btn">添加班级</a>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>班级编号</th>
                    <th>班级名称</th>
                    <th>专业</th>
                    <th>年级</th>
                    <th>描述</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="clazz" items="${classes}">
                    <tr>
                        <td>${clazz.id}</td>
                        <td>${clazz.classNo}</td>
                        <td>${clazz.className}</td>
                        <td>${clazz.major}</td>
                        <td>${clazz.grade}</td>
                        <td>${clazz.description}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/class/editPage?id=${clazz.id}" class="btn-edit">编辑</a>
                            <a href="${pageContext.request.contextPath}/class/delete?id=${clazz.id}" 
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
