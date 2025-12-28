<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>提交权限申请</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: "Microsoft YaHei", Arial, sans-serif; padding: 20px; background: #f5f5f5; }
        .container { max-width: 600px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; }
        h2 { margin-bottom: 20px; color: #333; }
        .form-group { margin-bottom: 20px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; color: #555; }
        textarea { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 5px; font-size: 14px; height: 150px; resize: vertical; }
        .btn { padding: 10px 20px; background: #667eea; color: white; border: none; border-radius: 5px; cursor: pointer; margin-right: 10px; }
        .btn:hover { background: #5568d3; }
        .btn-cancel { background: #6c757d; }
        .btn-cancel:hover { background: #5a6268; }
        .error { color: #dc3545; margin-top: 10px; }
        .info { background: #e7f3ff; padding: 15px; border-radius: 5px; margin-bottom: 20px; }
    </style>
</head>
<body>
    <div class="container">
        <jsp:include page="/common/header.jsp"/>
        <h2>提交权限申请</h2>
        <div class="info">
            <p><strong>申请类型：</strong>卫生检查员权限</p>
            <p>申请通过后，您将获得卫生检查员权限，可以添加、修改和删除卫生检查记录。</p>
        </div>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <form action="${pageContext.request.contextPath}/permission/add" method="post">
            <div class="form-group">
                <label>申请理由：</label>
                <textarea name="reason" required placeholder="请说明您申请卫生检查员权限的理由..."></textarea>
            </div>
            <div class="form-group">
                <button type="submit" class="btn">提交申请</button>
                <a href="${pageContext.request.contextPath}/permission/myList" class="btn btn-cancel">取消</a>
            </div>
        </form>
        <jsp:include page="/common/footer.jsp"/>
    </div>
</body>
</html>

