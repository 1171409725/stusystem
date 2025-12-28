<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>卫生检查记录</title>
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
        .grade-excellent { color: #28a745; font-weight: bold; }
        .grade-good { color: #17a2b8; font-weight: bold; }
        .grade-pass { color: #ffc107; font-weight: bold; }
        .grade-fail { color: #dc3545; font-weight: bold; }
    </style>
</head>
<body>
    <div class="container">
        <jsp:include page="/common/header.jsp"/>
        <h2>卫生检查记录</h2>
        <c:if test="${sessionScope.user.role != 'student'}">
            <a href="${pageContext.request.contextPath}/healthcheck/addPage" class="btn">添加检查记录</a>
        </c:if>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>宿舍</th>
                    <th>检查日期</th>
                    <th>评分</th>
                    <th>等级</th>
                    <th>检查人员</th>
                    <th>检查意见</th>
                    <c:if test="${sessionScope.user.role != 'student'}">
                        <th>操作</th>
                    </c:if>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="check" items="${healthChecks}">
                    <tr>
                        <td>${check.id}</td>
                        <td>
                            <c:if test="${check.dormitory != null}">
                                ${check.dormitory.building}-${check.dormitory.roomNumber}
                            </c:if>
                            <c:if test="${check.dormitory == null}">
                                ${check.dormitoryId}
                            </c:if>
                        </td>
                        <td>${check.checkDate}</td>
                        <td>${check.score}</td>
                        <td class="grade-${check.grade == '优秀' ? 'excellent' : check.grade == '良好' ? 'good' : check.grade == '合格' ? 'pass' : 'fail'}">
                            ${check.grade}
                        </td>
                        <td>${check.checkerName}</td>
                        <td>${check.comment}</td>
                        <c:if test="${sessionScope.user.role != 'student'}">
                            <td>
                                <a href="${pageContext.request.contextPath}/healthcheck/editPage?id=${check.id}" class="btn-edit">编辑</a>
                                <a href="${pageContext.request.contextPath}/healthcheck/delete?id=${check.id}" 
                                   class="btn-delete" onclick="return confirm('确定要删除吗？')">删除</a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <jsp:include page="/common/footer.jsp"/>
    </div>
</body>
</html>
