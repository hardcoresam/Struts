package com.MinicareStruts.dao;

import com.MinicareStruts.filter.HibernateSessionFilter;
import com.MinicareStruts.model.Job;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class JobDAO {
    public Job getJobById(int jobId) {
        Session session = HibernateSessionFilter.getSession();
        Job job = (Job)session.get(Job.class,jobId);
        return job;
    }

    public void postJob(Job job) {
        Session session = HibernateSessionFilter.getSession();
        session.save(job);
    }

    public void editJob(Job job) {
        Session session = HibernateSessionFilter.getSession();
        session.update(job);
    }

    public List<Job> listJobs(int seekerId) {
        Session session = HibernateSessionFilter.getSession();
        Query query = session.createQuery("FROM Job where seeker.memberId=? and status=?");
        query.setInteger(0,seekerId);
        query.setString(1,"ACTIVE");
        List<Job> list = query.list();
        return list;
    }

    public void deleteJob(Job job) {
        Session session = HibernateSessionFilter.getSession();
        session.update(job);
    }

    public List<Job> listActiveJobs(int sitterId) {
        Session session = HibernateSessionFilter.getSession();
        Query query = session.createQuery("FROM Job where jobId not in (select job.jobId " +
                "from JobApplication where sitter.memberId=? and status=?) and status=?");
        query.setInteger(0,sitterId);
        query.setString(1,"ACTIVE");
        query.setString(2,"ACTIVE");
        List<Job> list = query.list();
        return list;
    }
}