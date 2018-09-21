package com.MinicareStruts.filter;

import com.MinicareStruts.listener.SessionFactoryListener;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateSessionFilter implements Filter {
    private static Logger logger = Logger.getLogger(HibernateSessionFilter.class.getName());
    private static Session session = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        try {
            session = SessionFactoryListener.getSessionFactory().openSession();
            session.beginTransaction();
        }
        catch(HibernateException he) {
            logger.log(Level.SEVERE,"Cannot create a Session"+he);
            he.printStackTrace();
        }

        try {
            filterChain.doFilter(request, response);
        }
        catch(Exception he) {
            logger.log(Level.SEVERE,"Exception occurred in the Filter Chain"+he);
            session.getTransaction().rollback();
        }

        try {
            if(!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
            session.close();
        }
        catch(HibernateException he) {
            logger.log(Level.SEVERE,"Cannot commit or close the Session"+he);
        }
    }

    @Override
    public void destroy() {}

    public static Session getSession()
    {
        return session;
    }
}