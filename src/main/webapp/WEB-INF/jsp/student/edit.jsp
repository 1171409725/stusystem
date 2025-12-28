<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>编辑学生</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: "Microsoft YaHei", Arial, sans-serif; padding: 20px; background: #f5f5f5; }
        .container { max-width: 600px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; }
        h2 { margin-bottom: 20px; color: #333; }
        .form-group { margin-bottom: 20px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #555; }
        input, select { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 5px; font-size: 14px; }
        .btn { padding: 10px 20px; background: #667eea; color: white; border: none; border-radius: 5px; cursor: pointer; margin-right: 10px; }
        .btn:hover { background: #5568d3; }
        .btn-cancel { background: #6c757d; }
        .btn-cancel:hover { background: #5a6268; }
        .error { color: #dc3545; margin-top: 10px; }
    </style>
</head>
<body>
    <div class="container">
        <jsp:include page="/common/header.jsp"/>
        <h2>编辑学生</h2>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/student/update" method="post">
            <input type="hidden" name="id" value="${student.id}">
            <div class="form-group">
                <label>学号：</label>
                <input type="text" name="studentNo" value="${student.studentNo}" required>
            </div>
            <div class="form-group">
                <label>姓名：</label>
                <input type="text" name="name" value="${student.user.name}" required>
            </div>
            <div class="form-group">
                <label>性别：</label>
                <select name="gender" required>
                    <option value="男" ${student.user.gender == '男' ? 'selected' : ''}>男</option>
                    <option value="女" ${student.user.gender == '女' ? 'selected' : ''}>女</option>
                </select>
            </div>
            <div class="form-group">
                <label>电话：</label>
                <input type="text" name="phone" value="${student.user.phone}">
            </div>
            <div class="form-group">
                <label>邮箱：</label>
                <input type="email" name="email" value="${student.user.email}">
            </div>
            <div class="form-group">
                <label>班级：</label>
                <select name="classId">
                    <option value="">--请选择--</option>
                    <c:forEach var="clazz" items="${classes}">
                        <option value="${clazz.id}" ${student.classId == clazz.id ? 'selected' : ''}>_x005f
                            ${clazz.className}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>宿舍：</label>
                <select name="dormitoryId">
                    <option value="">--请选择--</option>
                    <c:forEach var="dormitory" items="${dormitories}">
                        <option value="${dormitory.id}" ${student.dormitoryId == dormitory.id ? 'selected' : ''}>_x005f
                            ${dormitory.building}-${dormitory.roomNumber}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <button type="submit" class="btn">提交</button>
                <a href="${pageContext.request.contextPath}/student/list" class="btn btn-cancel">取消</a>
            </div>
        </form>
        <jsp:include page="/common/footer.jsp"/>
    </div>
</body>
</html>
