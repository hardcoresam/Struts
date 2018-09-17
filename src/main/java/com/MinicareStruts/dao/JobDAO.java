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

    /*
    public void postJob(Job job) throws Exception{
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(job);
        transaction.commit();
    }
    */

    public void postJob(Job job) {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into job (Title, PayPerHour, SeekerId, StartDate, EndDate, StartTime, EndTime) values (?,?,?,?,?,?,?)");
            pst.setString(1,job.getTitle());
            pst.setDouble(2,job.getPayPerHour());
            pst.setInt(3,job.getSeekerId());
            pst.setDate(4,job.getStartDate());
            pst.setDate(5,job.getEndDate());
            pst.setTime(6,job.getStartTime());
            pst.setTime(7,job.getEndTime());
            pst.executeUpdate();
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
    }

    /*
    public void editJob(Job job) throws Exception {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(job);
        transaction.commit();
    }
    */

    public boolean editJob(Job job) {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("update job set Title=?, PayPerHour=?, StartDate=?, EndDate=?, StartTime=?, " +
                    "EndTime=? where JobId=?");
            pst.setString(1,job.getTitle());
            pst.setDouble(2,job.getPayPerHour());
            pst.setDate(3,job.getStartDate());
            pst.setDate(4,job.getEndDate());
            pst.setTime(5,job.getStartTime());
            pst.setTime(6,job.getEndTime());
            pst.setInt(7,job.getJobId());
            pst.executeUpdate();
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return true;
    }

    /*
    //Ask if this is fine or not?
    public Set<Job> listJobs(int seekerId) throws Exception{
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Seeker seeker = (Seeker)session.get(Seeker.class,seekerId);
        Set<Job> set = seeker.getSetOfJobs();
        return set;
    }
    */

    public List<Job> listJobs(int seekerId) {
        List<Job> list = new ArrayList<Job>();
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("select Title, Status, StartDate, EndDate, StartTime, EndTime, JobId from job where SeekerId = ? and Status = ?");
            pst.setInt(1,seekerId);
            pst.setString(2,"ACTIVE");
            ResultSet res = pst.executeQuery();
            while(res.next()) {
                Job job = new Job();
                job.setTitle(res.getString(1));
                job.setStatus(Job.Status.valueOf(res.getString(2).toUpperCase()));
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

    /*
    //Ask Whether i should update the table like this or directly using an HQL update query?
    public void deleteJob(int jobId) throws Exception{
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Job job = (Job)session.get(Job.class,jobId);
        job.setStatus(Job.Status.INACTIVE);
        session.saveOrUpdate(job);
        transaction.commit();
    }
    */

    public boolean deleteJob(int jobId) {
        boolean status = false;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("update job set Status = ? where JobId = ?");
            pst.setString(1,"INACTIVE");
            pst.setInt(2,jobId);
            if(pst.executeUpdate()>0) {
                status = true;
            }
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return status;
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

    /*
    public Job getJobById(int jobId) throws Exception{
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Job job = (Job)session.get(Job.class,jobId);
        transaction.commit();
        return job;
    }
    */

    public Job getJobById(int jobId) {
        Job job = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/care");
            Connection con = ds.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from job where JobId=?");
            pst.setInt(1,jobId);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()) {
                job = new Job();
                job.setJobId(resultSet.getInt(1));
                job.setTitle(resultSet.getString(2));
                job.setPayPerHour(resultSet.getDouble(3));
                job.setStartDate(resultSet.getDate(6));
                job.setEndDate(resultSet.getDate(7));
                job.setStartTime(resultSet.getTime(8));
                job.setEndTime(resultSet.getTime(9));
            }
        }
        catch (SQLException|NamingException e) {
            System.out.println(e);
        }
        return job;
    }
}