package com.MinicareStruts.dao;

import com.MinicareStruts.filter.HibernateSessionFilter;
import com.MinicareStruts.model.Conversation;
import com.MinicareStruts.model.Message;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class ConversationDAO {
    public Conversation getConversationById(int conversationId) {
        Session session = HibernateSessionFilter.getSession();
        Conversation conversation = (Conversation)session.get(Conversation.class,conversationId);
        return conversation;
    }

    public int getConversationId(Conversation conversation) {
        int conversationId = -1;
        Session session = HibernateSessionFilter.getSession();
        Query query = session.createQuery("SELECT conversationId FROM Conversation where seeker.memberId=? and sitter.memberId=?");
        query.setInteger(0,conversation.getSeeker().getMemberId());
        query.setInteger(1,conversation.getSitter().getMemberId());

        Integer id = (Integer)query.uniqueResult();
        if(id!=null) {
            conversationId = id;
        }
        else {
            conversationId = (int)session.save(conversation);
        }
        return conversationId;
    }

    public List<Message> getMessages(int conversationId) {
        Session session = HibernateSessionFilter.getSession();
        Query query = session.createQuery("from Message where conversationId=?");
        query.setInteger(0, conversationId);
        List<Message> list = query.list();
        return list;
    }

    public void storeMessage(Message message) {
        Session session = HibernateSessionFilter.getSession();
        session.save(message);
    }
}