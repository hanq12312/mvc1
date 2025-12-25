<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.mvc1.model.Message" %>
<%@ page import="com.example.mvc1.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<head>
    <title>讨论列表 - 在线问答平台</title>
    <!-- 关键：确保CSS路径正确 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<!-- 核心容器：必须包含这个div，CSS才能生效 -->
<div class="container">
    <header>
        <h1>讨论列表</h1>
        <div class="header-actions">
            <%
                User loginUser = (User) session.getAttribute("loginUser");
                if (loginUser != null) {
            %>
            <a href="${pageContext.request.contextPath}/publish" class="btn">发布讨论</a>
            <% } else { %>
            <a href="${pageContext.request.contextPath}/login" class="btn">请登录</a>
            <% } %>
        </div>
    </header>

    <div class="message-list">
        <%
            List<Message> mainMessages = (List<Message>) request.getAttribute("mainMessages");
            if (mainMessages != null && !mainMessages.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                for (Message msg : mainMessages) {
        %>
        <div class="message-item">
            <h3>
                <a href="${pageContext.request.contextPath}/detail?id=<%=msg.getId()%>">
                    <%=msg.getTitle()%>
                </a>
            </h3>
            <div class="message-meta">
                发布者：<%=msg.getSender().getUsername()%> |
                时间：<%=sdf.format(msg.getDatetime())%>
            </div>
            <div class="message-content">
                <%=msg.getContent().replace("\n", "<br>")%>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <p class="empty-tip">暂无讨论内容，快来发布第一个讨论吧~</p>
        <%
            }
        %>
    </div>
</div>
</body>
</html>