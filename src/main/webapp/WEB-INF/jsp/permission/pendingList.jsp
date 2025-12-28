<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>待审核申请</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: "Microsoft YaHei", Arial, sans-serif; padding: 20px; background: #f5f5f5; }
        .container { max-width: 1200px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; }
        h2 { margin-bottom: 20px; color: #333; }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
        th { background: #f8f9fa; font-weight: bold; }
        tr:hover { background: #f5f5f5; }
        .btn-review { padding: 5px 15px; background: #667eea; color: white; text-decoration: none; border-radius: 3px; }
        .btn-review:hover { background: #5568d3; }
    </style>
</head>
<body>
    <div class="container">
        <jsp:include page="/common/header.jsp"/>
        <h2>待审核申请</h2>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>申请人</th>
                    <th>申请类型</th>
                    <th>申请理由</th>
                    <th>申请时间</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="app" items="${applications}">
                    <tr>
                        <td>${app.id}</td>
                        <td>${app.applicantName}</td>
                        <td>
                            <c:choose>
                                <c:when test="${app.applicationType == 'inspector'}">卫生检查员</c:when>
                            </c:choose>
                        </td>
                        <td>${app.reason}</td>
                        <td>${app.applyTime}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/permission/reviewPage?id=${app.id}" class="btn-review">审核</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <jsp:include page="/common/footer.jsp"/>
    </div>
</body>
</html>

