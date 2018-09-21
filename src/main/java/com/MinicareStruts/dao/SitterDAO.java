package com.MinicareStruts.dao;

import com.MinicareStruts.filter.HibernateSessionFilter;
import com.MinicareStruts.model.Sitter;
import org.hibernate.Session;

public class SitterDAO {

    public Sitter getSitterById(int sitterId) {
        Session session = HibernateSessionFilter.getSession();
        Sitter sitter = (Sitter)session.get(Sitter.class,sitterId);
        return sitter;
    }

    public void deleteSeeker(Sitter sitter) {
        Session session = HibernateSessionFilter.getSession();
        session.update(sitter);
    }
}
