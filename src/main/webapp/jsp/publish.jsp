<%--
  Created by IntelliJ IDEA.
  User: hanqing
  Date: 2025/11/21
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.mvc1.model.User" %>
<html>
<head>
    <title>发布讨论 - 在线问答平台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <header>
        <h1>发布新讨论</h1>
        <div class="header-actions">
            <a href="${pageContext.request.contextPath}/list" class="btn">← 返回列表</a>
        </div>
    </header>

    <%
        // 验证登录状态（未登录跳转登录页）
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
    %>

    <div class="publish-form">
        <%-- 错误提示 --%>
        <% if (request.getAttribute("error") != null) { %>
        <div class="error"><%=request.getAttribute("error")%></div>
        <% } %>

        <form action="${pageContext.request.contextPath}/publish" method="post">
            <div class="form-item">
                <label>讨论标题：</label>
                <input type="text" name="title" placeholder="请输入讨论标题（必填）"
                       style="width: 80%; padding: 10px;" required>
            </div>
            <div class="form-item" style="margin-top: 20px;">
                <label>讨论内容：</label>
                <textarea name="content" placeholder="请输入讨论内容（支持多行，必填）"
                          style="width: 80%; height: 200px; padding: 10px; resize: vertical;" required></textarea>
            </div>
            <div class="form-item" style="margin-top: 20px;">
                <button type="submit" class="btn">发布讨论</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
