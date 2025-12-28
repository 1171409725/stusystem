<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>编辑检查记录</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: "Microsoft YaHei", Arial, sans-serif; padding: 20px; background: #f5f5f5; }
        .container { max-width: 600px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; }
        h2 { margin-bottom: 20px; color: #333; }
        .form-group { margin-bottom: 20px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #555; }
        input, select, textarea { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 5px; font-size: 14px; }
        textarea { height: 100px; resize: vertical; }
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
        <h2>编辑检查记录</h2>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/healthcheck/update" method="post">
            <input type="hidden" name="id" value="${healthCheck.id}">
            <div class="form-group">
                <label>宿舍：</label>
                <select name="dormitoryId" required>
                    <option value="">--请选择--</option>
                    <c:forEach var="dormitory" items="${dormitories}">
                        <option value="${dormitory.id}" ${healthCheck.dormitoryId == dormitory.id ? 'selected' : ''}>
                            ${dormitory.building}-${dormitory.roomNumber}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>检查日期：</label>
                <input type="date" name="checkDate" value="${healthCheck.checkDate}" required>
            </div>
            <div class="form-group">
                <label>评分（0-100）：</label>
                <input type="number" name="score" value="${healthCheck.score}" min="0" max="100" step="0.1" required>
            </div>
            <div class="form-group">
                <label>检查意见：</label>
                <textarea name="comment">${healthCheck.comment}</textarea>
            </div>
            <div class="form-group">
                <button type="submit" class="btn">提交</button>
                <a href="${pageContext.request.contextPath}/healthcheck/list" class="btn btn-cancel">取消</a>
            </div>
        </form>
        <jsp:include page="/common/footer.jsp"/>
    </div>
</body>
</html>
