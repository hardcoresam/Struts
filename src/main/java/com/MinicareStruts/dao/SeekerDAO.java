package com.MinicareStruts.dao;

import com.MinicareStruts.filter.HibernateSessionFilter;
import com.MinicareStruts.model.Seeker;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SeekerDAO {

    public Seeker getSeekerById(int seekerId) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Seeker seeker = (Seeker)session.get(Seeker.class,seekerId);
        transaction.commit();
        return seeker;
    }

    public void deleteSeeker(Seeker seeker) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(seeker);
        transaction.commit();
    }
}