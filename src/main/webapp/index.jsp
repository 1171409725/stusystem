<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>宿舍卫生管理系统</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: "Microsoft YaHei", Arial, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }
        .header {
            text-align: center;
            color: white;
            margin-bottom: 30px;
        }
        .header h1 {
            font-size: 2.5em;
            margin-bottom: 10px;
        }
        .user-info {
            background: rgba(255,255,255,0.2);
            padding: 10px 20px;
            border-radius: 20px;
            display: inline-block;
            margin-bottom: 20px;
        }
        .container {
            background: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 10px 40px rgba(0,0,0,0.2);
            max-width: 1200px;
            margin: 0 auto;
        }
        .menu {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-top: 30px;
        }
        .menu-item {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            padding: 30px;
            border-radius: 10px;
            text-align: center;
            text-decoration: none;
            color: white;
            transition: transform 0.3s, box-shadow 0.3s;
            cursor: pointer;
        }
        .menu-item:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 20px rgba(0,0,0,0.3);
        }
        .menu-item h2 {
            margin-bottom: 10px;
            font-size: 1.5em;
        }
        .menu-item p {
            font-size: 0.9em;
            opacity: 0.9;
        }
        .logout-btn {
            display: inline-block;
            padding: 8px 20px;
            background: rgba(255,255,255,0.3);
            color: white;
            text-decoration: none;
            border-radius: 20px;
            margin-left: 10px;
        }
        .logout-btn:hover {
            background: rgba(255,255,255,0.5);
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>🏠 宿舍卫生管理系统</h1>
        <div class="user-info">
            <c:if test="${not empty sessionScope.user}">
                欢迎，${sessionScope.user.name}（
                <c:choose>
                    <c:when test="${sessionScope.user.role == 'student'}">学生</c:when>
                    <c:when test="${sessionScope.user.role == 'inspector'}">卫生检查员</c:when>
                    <c:when test="${sessionScope.user.role == 'teacher'}">教师</c:when>
                    <c:when test="${sessionScope.user.role == 'admin'}">系统管理员</c:when>
                </c:choose>
                ）
                <a href="${pageContext.request.contextPath}/logout" class="logout-btn">退出</a>
            </c:if>
        </div>
    </div>
    <div class="container">
        <c:if test="${empty sessionScope.user}">
            <script>
                window.location.href = "${pageContext.request.contextPath}/login.jsp";
            </script>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <c:set var="userRole" value="${sessionScope.user.role}"/>
            
            <div class="menu">
                <!-- 所有用户都可以访问 -->
                <a href="${pageContext.request.contextPath}/profile/view" class="menu-item">
                    <h2>👤 个人信息</h2>
                    <p>查看和修改个人信息</p>
                </a>
                <a href="${pageContext.request.contextPath}/healthcheck/list" class="menu-item">
                    <h2>🧹 卫生检查</h2>
                    <p>查看卫生检查记录</p>
                </a>
                
                <!-- 学生可以访问 -->
                <c:if test="${userRole == 'student'}">
                    <a href="${pageContext.request.contextPath}/permission/myList" class="menu-item">
                        <h2>📝 我的申请</h2>
                        <p>查看和提交权限申请</p>
                    </a>
                </c:if>
                
                <!-- 检查员及以上可以访问 -->
                <c:if test="${userRole == 'inspector' || userRole == 'teacher' || userRole == 'admin'}">
                    <a href="${pageContext.request.contextPath}/permission/myList" class="menu-item">
                        <h2>📝 我的申请</h2>
                        <p>查看权限申请</p>
                    </a>
                </c:if>
                
                <!-- 教师及以上可以访问 -->
                <c:if test="${userRole == 'teacher' || userRole == 'admin'}">
                    <a href="${pageContext.request.contextPath}/dormitory/list" class="menu-item">
                        <h2>🏢 宿舍管理</h2>
                        <p>查看和管理宿舍信息</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/student/list" class="menu-item">
                        <h2>👨‍🎓 学生管理</h2>
                        <p>查看和管理学生信息</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/class/list" class="menu-item">
                        <h2>📚 班级管理</h2>
                        <p>查看和管理班级信息</p>
                    </a>
                    <a href="${pageContext.request.contextPath}/permission/pendingList" class="menu-item">
                        <h2>✅ 审核申请</h2>
                        <p>审核权限申请</p>
                    </a>
                </c:if>
                
                <!-- 管理员可以访问 -->
                <c:if test="${userRole == 'admin'}">
                    <a href="${pageContext.request.contextPath}/user/list" class="menu-item">
                        <h2>👥 用户管理</h2>
                        <p>查看和管理用户信息</p>
                    </a>
                </c:if>
            </div>
        </c:if>
    </div>
</body>
</html>
