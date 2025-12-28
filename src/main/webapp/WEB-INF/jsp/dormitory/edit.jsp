<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>编辑宿舍</title>
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
        <h2>编辑宿舍</h2>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/dormitory/update" method="post">
            <input type="hidden" name="id" value="${dormitory.id}">
            <div class="form-group">
                <label>楼栋号：</label>
                <input type="text" name="building" value="${dormitory.building}" required>
            </div>
            <div class="form-group">
                <label>房间号：</label>
                <input type="text" name="roomNumber" value="${dormitory.roomNumber}" required>
            </div>
            <div class="form-group">
                <label>容纳人数：</label>
                <input type="number" name="capacity" value="${dormitory.capacity}" min="1" required>
            </div>
            <div class="form-group">
                <label>当前人数：</label>
                <input type="number" name="currentCount" value="${dormitory.currentCount}" min="0" required>
            </div>
            <div class="form-group">
                <label>状态：</label>
                <select name="status" required>
                    <option value="正常" ${dormitory.status == '正常' ? 'selected' : ''}>正常</option>
                    <option value="维修中" ${dormitory.status == '维修中' ? 'selected' : ''}>维修中</option>
                    <option value="已停用" ${dormitory.status == '已停用' ? 'selected' : ''}>已停用</option>
                </select>
            </div>
            <div class="form-group">
                <button type="submit" class="btn">提交</button>
                <a href="${pageContext.request.contextPath}/dormitory/list" class="btn btn-cancel">取消</a>
            </div>
        </form>
        <jsp:include page="/common/footer.jsp"/>
    </div>
</body>
</html>
