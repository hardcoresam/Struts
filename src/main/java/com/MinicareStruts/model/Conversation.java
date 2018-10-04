package com.MinicareStruts.model;

import java.util.Set;

public class Conversation {
    private int conversationId;

    private Seeker seeker;
    private Sitter sitter;
    private Set<Message> setOfMessages;

    public Conversation() {}

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public Seeker getSeeker() {
        return seeker;
    }

    public void setSeeker(Seeker seeker) {
        this.seeker = seeker;
    }

    public Sitter getSitter() {
        return sitter;
    }

    public void setSitter(Sitter sitter) {
        this.sitter = sitter;
    }

    public Set<Message> getSetOfMessages() { return setOfMessages; }

    public void setSetOfMessages(Set<Message> setOfMessages) { this.setOfMessages = setOfMessages; }
}
