<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>我的权限申请</title>
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
        .status-pending { color: #ffc107; font-weight: bold; }
        .status-approved { color: #28a745; font-weight: bold; }
        .status-rejected { color: #dc3545; font-weight: bold; }
    </style>
</head>
<body>
    <div class="container">
        <jsp:include page="/common/header.jsp"/>
        <h2>我的权限申请</h2>
        <a href="${pageContext.request.contextPath}/permission/addPage" class="btn">提交申请</a>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>申请类型</th>
                    <th>申请理由</th>
                    <th>状态</th>
                    <th>审核人</th>
                    <th>审核意见</th>
                    <th>申请时间</th>
                    <th>审核时间</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="app" items="${applications}">
                    <tr>
                        <td>${app.id}</td>
                        <td>
                            <c:choose>
                                <c:when test="${app.applicationType == 'inspector'}">卫生检查员</c:when>
                            </c:choose>
                        </td>
                        <td>${app.reason}</td>
                        <td class="status-${app.status}">
                            <c:choose>
                                <c:when test="${app.status == 'pending'}">待审核</c:when>
                                <c:when test="${app.status == 'approved'}">已通过</c:when>
                                <c:when test="${app.status == 'rejected'}">已拒绝</c:when>
                            </c:choose>
                        </td>
                        <td>${app.reviewerName != null ? app.reviewerName : '-'}</td>
                        <td>${app.reviewComment != null ? app.reviewComment : '-'}</td>
                        <td>${app.applyTime}</td>
                        <td>${app.reviewTime != null ? app.reviewTime : '-'}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <jsp:include page="/common/footer.jsp"/>
    </div>
</body>
</html>

