package com.MinicareStruts.dao;

import com.MinicareStruts.listener.SessionFactoryListener;
import com.MinicareStruts.model.Conversation;
import com.MinicareStruts.model.Message;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.naming.NamingException;

import java.util.ArrayList;
import java.util.List;

public class ConversationDAO {

    /*
    public int getConversationId(Conversation conversation) throws Exception{
        int conversationId = -1;
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT conversationId FROM Conversation where seekerId=? and sitterId=?");
        query.setInteger(0,conversation.getSeekerId());
        query.setInteger(1,conversation.getSitterId());
        Integer id = (Integer)query.uniqueResult();
        if(id!=null) {
            conversationId = id;
        }
        else {
            conversationId = (int)session.save(conversation);
        }
        transaction.commit();
        return conversationId;
    }
    */

    public int getConversationId(Conversation conversation) {
        int conversationId = -1;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("select ConversationId from conversation where SeekerId=? and SitterId=?");
            pst.setInt(1,conversation.getSeekerId());
            pst.setInt(2,conversation.getSitterId());
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()) {
                conversationId = resultSet.getInt(1);
            }
            else {
                PreparedStatement pst1 = con.prepareStatement("insert into conversation (SeekerId, SitterId) values (?,?)",Statement.RETURN_GENERATED_KEYS);
                pst1.setInt(1,conversation.getSeekerId());
                pst1.setInt(2,conversation.getSitterId());
                pst1.executeUpdate();

                ResultSet rs = pst1.getGeneratedKeys();
                if (rs.next()) {
                    conversationId = rs.getInt(1);
                }
            }
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return conversationId;
    }

    /*
    public List<Message> getMessages(int conversationId) throws Exception{
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Message where conversationId=?");
        query.setInteger(0, conversationId);
        List<Message> list = query.list();
        transaction.commit();
        return list;
    }
    */

    public List<Message> getMessages(int conversationId) {

        List<Message> list = new ArrayList<Message>();
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();

            //Check this query

            PreparedStatement pst = con.prepareStatement("select message.Content, message.ConversationId, message.Time, member.FirstName " +
                    "from message inner join member on message.SenderId=member.MemberId where ConversationId=?");
            pst.setInt(1,conversationId);
            ResultSet resultSet = pst.executeQuery();
            while(resultSet.next()) {
                Message message = new Message();
                message.setContent(resultSet.getString(1));
                message.setConversationId(resultSet.getInt(2));
                message.setTime(resultSet.getTime(3));
                message.setFirstName(resultSet.getString(4));

                list.add(message);
            }
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return list;
    }

    /*
    public void storeMessage(Message message) throws Exception{
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(message);
        transaction.commit();
    }
    */


    public boolean storeMessage(Message message) {
        boolean status = false;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();

            PreparedStatement pst = con.prepareStatement("insert into message (ConversationId, Content, Time, SenderId) values (?,?,?,?)");
            pst.setInt(1,message.getConversationId());
            pst.setString(2,message.getContent());
            pst.setTime(3,message.getTime());
            pst.setInt(4,message.getSenderId());
            if(pst.executeUpdate()>0) {
                status = true;
            }
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return status;
    }
}
