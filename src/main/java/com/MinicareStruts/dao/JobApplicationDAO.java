package com.MinicareStruts.dao;

import com.MinicareStruts.dto.JobApplicationDTO;
import com.MinicareStruts.dto.ListApplicationDTO;
import com.MinicareStruts.filter.HibernateSessionFilter;
import com.MinicareStruts.model.JobApplication;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDAO {

    public JobApplication getJobApplicationById(int applicationId) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        JobApplication jobApplication = (JobApplication)session.get(JobApplication.class,applicationId);
        transaction.commit();
        return jobApplication;
    }

    public void applyJob(JobApplication application) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(application);
        transaction.commit();
    }

    public List<JobApplication> listApplications(int sitterId) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM JobApplication where status=? and sitter.memberId=?");
        query.setString(0,"ACTIVE");
        query.setInteger(1,sitterId);
        List<JobApplication> list = query.list();
        transaction.commit();
        return list;
    }

    public void deleteApplication(JobApplication jobApplication) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(jobApplication);
        transaction.commit();
    }

    /*
    public void deleteApplicationsById(int jobId) throws Exception{
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE JobApplication set status=? where jobId=?");
        query.setString(0,"INACTIVE");
        query.setInteger(1,jobId);
        query.executeUpdate();
        transaction.commit();
    }
    */

        public boolean deleteApplicationsById(int jobId) {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("update jobapplication set Status = ? where JobId = ?");
            pst.setString(1,"INACTIVE");
            pst.setInt(2,jobId);
            pst.executeUpdate();
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return true;
    }

    /*
    public void deleteApplicationsBySitterId(int sitterId) throws Exception{
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE JobApplication set status=? where sitterId=?");
        query.setString(0,"INACTIVE");
        query.setInteger(1,sitterId);
        query.executeUpdate();
        transaction.commit();
    }
    */

    public boolean deleteApplicationsBySitterId(int sitterId) {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("update jobapplication set Status=? where SitterId=?");
            pst.setString(1, "INACTIVE");
            pst.setInt(2, sitterId);
            pst.executeUpdate();
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return true;
    }

    public List<JobApplication> listApplicationsForSeeker(int jobId) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM JobApplication where job.jobId=? and status=?");
        query.setInteger(0,jobId);
        query.setString(1,"ACTIVE");
        List<JobApplication> list = query.list();
        transaction.commit();
        return list;
    }
}
