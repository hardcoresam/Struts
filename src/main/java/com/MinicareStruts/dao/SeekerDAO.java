package com.MinicareStruts.dao;

import com.MinicareStruts.filter.HibernateSessionFilter;
import com.MinicareStruts.listener.SessionFactoryListener;
import com.MinicareStruts.model.Seeker;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

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

    /*
    public Seeker getSeekerById(int seekerId) {
        Seeker seeker = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from seeker where SeekerId=?");
            pst.setInt(1,seekerId);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()) {
                seeker = new Seeker();
                seeker.setNoOfChildren(resultSet.getInt(2));
                seeker.setSpouseName(resultSet.getString(3));
            }
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return seeker;
    }
    */
}