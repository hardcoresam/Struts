package com.MinicareStruts.dao;

import com.MinicareStruts.filter.HibernateSessionFilter;
import com.MinicareStruts.model.Sitter;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SitterDAO {

    public Sitter getSitterById(int sitterId) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Sitter sitter = (Sitter)session.get(Sitter.class,sitterId);
        transaction.commit();
        return sitter;
    }

    public void deleteSeeker(Sitter sitter) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(sitter);
        transaction.commit();
    }
}
