<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>审核申请</title>
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
        .btn-approve { background: #28a745; }
        .btn-reject { background: #dc3545; }
        .btn-cancel { background: #6c757d; }
        .info { background: #f8f9fa; padding: 15px; border-radius: 5px; margin-bottom: 20px; }
    </style>
</head>
<body>
    <div class="container">
        <jsp:include page="/common/header.jsp"/>
        <h2>审核申请</h2>
        <div class="info">
            <p><strong>申请人：</strong>${application.applicantName}</p>
            <p><strong>申请类型：</strong>
                <c:choose>
                    <c:when test="${application.applicationType == 'inspector'}">卫生检查员</c:when>
                </c:choose>
            </p>
            <p><strong>申请理由：</strong>${application.reason}</p>
            <p><strong>申请时间：</strong>${application.applyTime}</p>
        </div>
        <form action="${pageContext.request.contextPath}/permission/review" method="post">
            <input type="hidden" name="id" value="${application.id}">
            <div class="form-group">
                <label>审核意见：</label>
                <textarea name="reviewComment" placeholder="请输入审核意见..."></textarea>
            </div>
            <div class="form-group">
                <button type="submit" name="status" value="approved" class="btn btn-approve">通过</button>
                <button type="submit" name="status" value="rejected" class="btn btn-reject">拒绝</button>
                <a href="${pageContext.request.contextPath}/permission/pendingList" class="btn btn-cancel">取消</a>
            </div>
        </form>
        <jsp:include page="/common/footer.jsp"/>
    </div>
</body>
</html>

