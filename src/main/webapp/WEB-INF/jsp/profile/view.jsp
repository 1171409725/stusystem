<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人信息</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: "Microsoft YaHei", Arial, sans-serif; padding: 20px; background: #f5f5f5; }
        .container { max-width: 1000px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; }
        h2 { margin-bottom: 20px; color: #333; }
        .btn { display: inline-block; padding: 10px 20px; background: #667eea; color: white; text-decoration: none; border-radius: 5px; margin-bottom: 20px; margin-right: 10px; }
        .btn:hover { background: #5568d3; }
        .info-section { margin-bottom: 30px; }
        .info-item { padding: 10px 0; border-bottom: 1px solid #eee; }
        .info-label { font-weight: bold; color: #555; display: inline-block; width: 120px; }
        .info-value { color: #333; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
        th { background: #f8f9fa; font-weight: bold; }
        .grade-excellent { color: #28a745; font-weight: bold; }
        .grade-good { color: #17a2b8; font-weight: bold; }
        .grade-pass { color: #ffc107; font-weight: bold; }
        .grade-fail { color: #dc3545; font-weight: bold; }
    </style>
</head>
<body>
    <div class="container">
        <jsp:include page="/common/header.jsp"/>
        <h2>个人信息</h2>
        <a href="${pageContext.request.contextPath}/profile/editPage" class="btn">修改个人信息</a>
        <a href="${pageContext.request.contextPath}/profile/changePasswordPage" class="btn">修改密码</a>
        
        <div class="info-section">
            <h3>基本信息</h3>
            <div class="info-item">
                <span class="info-label">用户名：</span>
                <span class="info-value">${user.username}</span>
            </div>
            <div class="info-item">
                <span class="info-label">姓名：</span>
                <span class="info-value">${user.name}</span>
            </div>
            <div class="info-item">
                <span class="info-label">角色：</span>
                <span class="info-value">
                    <c:choose>
                        <c:when test="${user.role == 'student'}">学生</c:when>
                        <c:when test="${user.role == 'inspector'}">卫生检查员</c:when>
                        <c:when test="${user.role == 'teacher'}">教师</c:when>
                        <c:when test="${user.role == 'admin'}">系统管理员</c:when>
                    </c:choose>
                </span>
            </div>
            <div class="info-item">
                <span class="info-label">性别：</span>
                <span class="info-value">${user.gender}</span>
            </div>
            <div class="info-item">
                <span class="info-label">电话：</span>
                <span class="info-value">${user.phone != null ? user.phone : '-'}</span>
            </div>
            <div class="info-item">
                <span class="info-label">邮箱：</span>
                <span class="info-value">${user.email != null ? user.email : '-'}</span>
            </div>
        </div>
        
        <c:if test="${not empty student}">
            <div class="info-section">
                <h3>学生信息</h3>
                <div class="info-item">
                    <span class="info-label">学号：</span>
                    <span class="info-value">${student.studentNo}</span>
                </div>
                <c:if test="${not empty student.clazz}">
                    <div class="info-item">
                        <span class="info-label">班级：</span>
                        <span class="info-value">${student.clazz.className}</span>
                    </div>
                </c:if>
                <c:if test="${not empty student.dormitory}">
                    <div class="info-item">
                        <span class="info-label">宿舍：</span>
                        <span class="info-value">${student.dormitory.building}-${student.dormitory.roomNumber}</span>
                    </div>
                </c:if>
            </div>
        </c:if>
        
        <c:if test="${not empty healthChecks}">
            <div class="info-section">
                <h3>我的宿舍卫生检查记录</h3>
                <table>
                    <thead>
                        <tr>
                            <th>检查日期</th>
                            <th>评分</th>
                            <th>等级</th>
                            <th>检查人员</th>
                            <th>检查意见</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="check" items="${healthChecks}">
                            <tr>
                                <td>${check.checkDate}</td>
                                <td>${check.score}</td>
                                <td class="grade-${check.grade == '优秀' ? 'excellent' : check.grade == '良好' ? 'good' : check.grade == '合格' ? 'pass' : 'fail'}">
                                    ${check.grade}
                                </td>
                                <td>${check.checkerName != null ? check.checkerName : '-'}</td>
                                <td>${check.comment != null ? check.comment : '-'}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        
        <jsp:include page="/common/footer.jsp"/>
    </div>
</body>
</html>
