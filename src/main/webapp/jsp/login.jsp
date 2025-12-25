<%--
  Created by IntelliJ IDEA.
  User: hanqing
  Date: 2025/11/20
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录 - 在线问答平台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<!-- 外层容器：全屏居中 -->
<div class="login-page">
    <div class="login-container">
        <h1>在线问答平台</h1>
        <div class="login-box">
            <h2>用户登录</h2>
            <%-- 错误提示（验证码/账号密码错误） --%>
            <% if (request.getAttribute("error") != null) { %>
            <div class="error"><%=request.getAttribute("error")%></div>
            <% } %>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-item">
                    <label>用户名：</label>
                    <input type="text" name="username" placeholder="请输入用户名" required>
                </div>
                <div class="form-item">
                    <label>密码：</label>
                    <input type="password" name="password" placeholder="请输入密码" required>
                </div>
                <div class="form-item">
                    <label>验证码：</label>
                    <input type="text" name="captcha" placeholder="请输入验证码" required style="width: 100px;">
                    <%-- 验证码图片（点击刷新） --%>
                    <img src="${pageContext.request.contextPath}/captcha"
                         onclick="this.src='${pageContext.request.contextPath}/captcha?'+Math.random()"
                         alt="验证码" class="captcha-img">
                </div>
                <button type="submit" class="login-btn">登录</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
