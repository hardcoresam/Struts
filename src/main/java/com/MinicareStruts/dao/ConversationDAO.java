package com.MinicareStruts.dao;

import com.MinicareStruts.filter.HibernateSessionFilter;
import com.MinicareStruts.model.Conversation;
import com.MinicareStruts.model.Member;
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

    public List<Conversation> getUserNames(int memberId, String type) {
        Session session = HibernateSessionFilter.getSession();
        Query query = session.createQuery("from Conversation where "+type+".memberId=? and "+type+".status=?");
        query.setInteger(0, memberId);
        query.setString(1,"ACTIVE");
        List<Conversation> list = query.list();
        return list;
    }

    public List<Member> getNewConversation(int memberId, String type) {
        Session session = HibernateSessionFilter.getSession();
        Query query;
        if(type.equalsIgnoreCase("seeker"))
            query = session.createQuery("FROM Member where memberId not in (select sitter.memberId from Conversation where seeker.memberId=?) and memberId!=? and status=? and type='sitter'");
        else
            query = session.createQuery("FROM Member where memberId not in (select seeker.memberId from Conversation where sitter.memberId=?) and memberId!=? and status=? and type='seeker'");
        query.setInteger(0, memberId);
        query.setInteger(1, memberId);
        query.setString(2,"ACTIVE");
        List<Member> list = query.list();
        return list;
    }
}