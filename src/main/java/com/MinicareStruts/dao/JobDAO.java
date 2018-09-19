package com.MinicareStruts.dao;

import com.MinicareStruts.filter.HibernateSessionFilter;
import com.MinicareStruts.model.Job;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class JobDAO {

    public Job getJobById(int jobId) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Job job = (Job)session.get(Job.class,jobId);
        transaction.commit();
        return job;
    }

    public void postJob(Job job) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(job);
        transaction.commit();
    }

    public void editJob(Job job) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(job);
        transaction.commit();
    }

    public List<Job> listJobs(int seekerId) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Job where seeker.memberId=? and status=?");
        query.setInteger(0,seekerId);
        query.setString(1,"ACTIVE");
        List<Job> list = query.list();
        transaction.commit();
        return list;
    }

    public void deleteJob(Job job) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        session.update(job);
        transaction.commit();
    }

    public List<Job> listActiveJobs(int sitterId) {
        Session session = HibernateSessionFilter.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM Job where jobId not in (select job.jobId " +
                "from JobApplication where sitter.memberId=? and status=?) and status=?");
        query.setInteger(0,sitterId);
        query.setString(1,"ACTIVE");
        query.setString(2,"ACTIVE");
        List<Job> list = query.list();
        transaction.commit();
        return list;
    }
}