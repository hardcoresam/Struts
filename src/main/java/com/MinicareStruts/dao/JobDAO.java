package com.MinicareStruts.dao;

import com.MinicareStruts.filter.HibernateSessionFilter;
import com.MinicareStruts.model.Job;
import com.MinicareStruts.model.Seeker;
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
import java.util.Set;

public class JobDAO {

    public void postJob(Job job) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(job);
        transaction.commit();
    }

    public void editJob(Job job) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(job);
        transaction.commit();
    }

    public Set<Job> listJobs(int seekerId) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Seeker seeker = (Seeker)session.get(Seeker.class,seekerId);
        Set<Job> set = seeker.getSetOfJobs();
        return set;
    }

    public void deleteJob(Job job) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(job);
        transaction.commit();
    }

    /*
    public List<Job> listActiveJobs(int sitterId) throws Exception{
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Job where jobId not in (select jobId " +
                "from JobApplication where sitterId=? and status=?) and status=?");
        query.setInteger(0,sitterId);
        query.setString(1,"ACTIVE");
        query.setString(2,"ACTIVE");
        List<Job> list = query.list();
        transaction.commit();
        return list;
    }
    */

    public List<Job> listActiveJobs(int sitterId) {
        List<Job> list = new ArrayList<Job>();
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("select Title, PayPerHour, StartDate, EndDate, StartTime, EndTime, JobId " +
                    "from job where JobId not in (select JobId from jobapplication where SitterId=? and Status=?) and Status=?");
            pst.setInt(1,sitterId);
            pst.setString(2,"ACTIVE");
            pst.setString(3,"ACTIVE");
            ResultSet res = pst.executeQuery();

            while(res.next()) {
                Job job = new Job();
                job.setTitle(res.getString(1));
                job.setPayPerHour(res.getDouble(2));
                job.setStartDate(res.getDate(3));
                job.setEndDate(res.getDate(4));
                job.setStartTime(res.getTime(5));
                job.setEndTime(res.getTime(6));
                job.setJobId(res.getInt(7));

                list.add(job);
            }
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return list;
    }


    public Job getJobById(int jobId) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Job job = (Job)session.get(Job.class,jobId);
        transaction.commit();
        return job;
    }
}