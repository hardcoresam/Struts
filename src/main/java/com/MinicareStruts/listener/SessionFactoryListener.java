package com.MinicareStruts.listener;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionFactoryListener implements ServletContextListener {

    private static Logger logger = Logger.getLogger(SessionFactoryListener.class.getName());
    private static Configuration configuration = null;
    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            configuration = new Configuration().configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        catch(Exception he) {
            logger.log(Level.SEVERE,"Cannot create a Session Factory"+he);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        try {
            sessionFactory.close();
        }
        catch(HibernateException he) {
            logger.log(Level.SEVERE,"Cannot close the Session Factory"+he);
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}