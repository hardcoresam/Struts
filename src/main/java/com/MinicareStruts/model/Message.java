package com.MinicareStruts.model;
import java.sql.Time;

public class Message {
    private int messageId;
    private String content;
    private Time time;

    private Conversation conversation;
    private Member member;

    public Message() {}

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Conversation getConversation() { return conversation; }

    public void setConversation(Conversation conversation) { this.conversation = conversation; }

    public Member getMember() { return member; }

    public void setMember(Member member) { this.member = member; }
}
