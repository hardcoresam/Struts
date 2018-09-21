package com.MinicareStruts.dao;

import com.MinicareStruts.filter.HibernateSessionFilter;
import com.MinicareStruts.model.Seeker;
import org.hibernate.Session;

public class SeekerDAO {
    public Seeker getSeekerById(int seekerId) {
        Session session = HibernateSessionFilter.getSession();
        Seeker seeker = (Seeker)session.get(Seeker.class,seekerId);
        return seeker;
    }

    public void deleteSeeker(Seeker seeker) {
        Session session = HibernateSessionFilter.getSession();
        session.update(seeker);
    }
}