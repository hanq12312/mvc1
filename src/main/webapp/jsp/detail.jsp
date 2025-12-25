<%--
  Created by IntelliJ IDEA.
  User: hanqing
  Date: 2025/11/20
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.mvc1.model.Message" %>
<%@ page import="com.example.mvc1.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html>
<head>
    <title>讨论详情 - 在线问答平台</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <header>
        <h1>讨论详情</h1>
        <a href="${pageContext.request.contextPath}/list" class="btn">← 返回列表</a>
    </header>

    <%
        // 从请求域获取主讨论和回复列表
        Message mainMessage = (Message) request.getAttribute("mainMessage");
        List<Message> replies = (List<Message>) request.getAttribute("replies");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        if (mainMessage == null) {
    %>
    <div class="error">该讨论不存在或已删除！</div>
    <% } else { %>
    <!-- 主讨论内容 -->
    <div class="main-message">
        <h2><%=mainMessage.getTitle()%></h2>
        <div class="message-meta">
            发布者：<%=mainMessage.getSender().getUsername()%> |
            时间：<%=sdf.format(mainMessage.getDatetime())%>
        </div>
        <div class="message-content">
            <%=mainMessage.getContent().replace("\n", "<br>")%>
        </div>
    </div>

    <!-- 回复列表 -->
    <h3>回复列表（<%=replies.size()%> 条）</h3>
    <div class="replies">
        <% if (replies != null && !replies.isEmpty()) {
            for (Message reply : replies) {
        %>
        <div class="reply-item">
            <div class="reply-meta">
                回复者：<%=reply.getSender().getUsername()%> |
                时间：<%=sdf.format(reply.getDatetime())%>
            </div>
            <div class="reply-content">
                <%=reply.getContent().replace("\n", "<br>")%>
            </div>
        </div>
        <%
            } // for 循环结束
        } else { %>
        <p class="no-reply">暂无回复，快来抢沙发~</p>
        <% } // if-else 结束 %>
    </div>

    <!-- 回复表单：仅登录用户可见 -->
    <%
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
    %>
    <div class="reply-form" style="margin-top: 30px; padding-top: 20px; border-top: 1px solid #eee;">
        <h3>发表回复</h3>
        <form action="${pageContext.request.contextPath}/reply" method="post">
            <!-- 隐藏域：传递主讨论ID（关键，关联回复对应的主讨论） -->
            <input type="hidden" name="mainMessageId" value="<%=mainMessage.getId()%>">
            <div class="form-item">
                        <textarea
                                name="replyContent"
                                placeholder="请输入回复内容..."
                                style="width: 100%; height: 120px; padding: 10px; resize: vertical; border: 1px solid #ddd; border-radius: 4px;"
                                required
                        ></textarea>
            </div>
            <button type="submit" class="btn" style="margin-top: 10px;">提交回复</button>
        </form>
    </div>
    <% } %>

    <% } // 主讨论是否存在的判断结束 %>
</div>
</body>
</html>
