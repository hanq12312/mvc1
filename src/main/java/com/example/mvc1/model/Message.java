package com.example.mvc1.model;

import java.util.Date;

public class Message {
    private String id;
    private String title; // 主讨论标题，回复为null
    private Date datetime;
    private String content; // 支持多行文本
    private String replyId; // 回复的主讨论ID，主讨论为null
    private User sender;

    public Message() {} // 空构造方法

    public Message(String id, String title, Date datetime, String content, String replyId, User sender) {
        this.id = id;
        this.title = title;
        this.datetime = datetime;
        this.content = content;
        this.replyId = replyId;
        this.sender = sender;
    }

    // Getter和Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Date getDatetime() { return datetime; }
    public void setDatetime(Date datetime) { this.datetime = datetime; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getReplyId() { return replyId; }
    public void setReplyId(String replyId) { this.replyId = replyId; }
    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }
}
