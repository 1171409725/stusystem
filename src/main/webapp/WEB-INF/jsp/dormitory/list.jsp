<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>宿舍管理</title>
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
        <h2>宿舍列表</h2>
        <a href="${pageContext.request.contextPath}/dormitory/addPage" class="btn">添加宿舍</a>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>楼栋</th>
                    <th>房间号</th>
                    <th>容纳人数</th>
                    <th>当前人数</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dormitory" items="${dormitories}">
                    <tr>
                        <td>${dormitory.id}</td>
                        <td>${dormitory.building}</td>
                        <td>${dormitory.roomNumber}</td>
                        <td>${dormitory.capacity}</td>
                        <td>${dormitory.currentCount}</td>
                        <td>${dormitory.status}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/dormitory/editPage?id=${dormitory.id}" class="btn-edit">编辑</a>
                            <a href="${pageContext.request.contextPath}/dormitory/delete?id=${dormitory.id}" 
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
