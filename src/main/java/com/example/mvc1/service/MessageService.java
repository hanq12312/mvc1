package com.example.mvc1.service;

import com.example.mvc1.model.Message;
import com.example.mvc1.model.User;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MessageService {
    // 模拟数据库：存储所有消息（主讨论+回复）
    private static final Map<String, Message> messageMap = new ConcurrentHashMap<>();
    private static int idGenerator = 1; // 自增ID

    // 初始化测试数据
    static {
        UserService userService = new UserService();
        User admin = userService.getUserById("1");
        User zhangsan = userService.getUserById("2");

        // 主讨论1
        Message main1 = new Message(
                String.valueOf(idGenerator++),
                "Java Web如何实现MVC架构？",
                new Date(),
                "刚接触Java Web，想知道MVC的具体实现步骤，比如Servlet、JSP、JavaBean怎么配合？\n求大神指导～",
                null,
                admin
        );
        messageMap.put(main1.getId(), main1);

        // 回复主讨论1
        Message reply1 = new Message(
                String.valueOf(idGenerator++),
                null,
                new Date(),
                "简单说：Servlet当Controller，接收请求；JavaBean当Model，存数据和业务；JSP当View，展示页面。\nController调用Model处理后，把数据传给View。",
                main1.getId(),
                zhangsan
        );
        messageMap.put(reply1.getId(), reply1);

        // 主讨论2
        Message main2 = new Message(
                String.valueOf(idGenerator++),
                "Servlet中如何处理POST请求的中文乱码？",
                new Date(),
                "表单用POST提交，中文到了Servlet里变成乱码，试过设置request.setCharacterEncoding(\"UTF-8\")，好像没用？",
                null,
                zhangsan
        );
        messageMap.put(main2.getId(), main2);
    }

    // 获取所有主讨论（非回复）
    public List<Message> getAllMainMessages() {
        List<Message> mainList = new ArrayList<>();
        for (Message msg : messageMap.values()) {
            if (msg.getReplyId() == null) {
                mainList.add(msg);
            }
        }
        return mainList;
    }

    // 根据ID获取消息
    public Message getMessageById(String id) {
        return messageMap.get(id);
    }

    // 根据主讨论ID获取所有回复
    public List<Message> getRepliesByMainId(String mainId) {
        List<Message> replies = new ArrayList<>();
        for (Message msg : messageMap.values()) {
            if (mainId.equals(msg.getReplyId())) {
                replies.add(msg);
            }
        }
        return replies;
    }
    // 添加新讨论/回复
    public void addMessage(Message message) {
        String id = String.valueOf(idGenerator++); // 使用自增ID
        message.setId(id);
        messageMap.put(id, message);
    }
}

