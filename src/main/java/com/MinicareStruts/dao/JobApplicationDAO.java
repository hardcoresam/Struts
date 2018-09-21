package com.MinicareStruts.dao;

import com.MinicareStruts.filter.HibernateSessionFilter;
import com.MinicareStruts.model.JobApplication;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class JobApplicationDAO {

    public JobApplication getJobApplicationById(int applicationId) {
        Session session = HibernateSessionFilter.getSession();
        JobApplication jobApplication = (JobApplication)session.get(JobApplication.class,applicationId);
        return jobApplication;
    }

    public void applyJob(JobApplication application) {
        Session session = HibernateSessionFilter.getSession();
        session.save(application);
    }

    public List<JobApplication> listApplications(int sitterId) {
        Session session = HibernateSessionFilter.getSession();
        Query query = session.createQuery("FROM JobApplication where status=? and sitter.memberId=?");
        query.setString(0,"ACTIVE");
        query.setInteger(1,sitterId);
        List<JobApplication> list = query.list();
        return list;
    }

    public void deleteApplication(JobApplication jobApplication) {
        Session session = HibernateSessionFilter.getSession();
        session.update(jobApplication);
    }

    public List<JobApplication> listApplicationsForSeeker(int jobId) {
        Session session = HibernateSessionFilter.getSession();
        Query query = session.createQuery("FROM JobApplication where job.jobId=? and status=?");
        query.setInteger(0,jobId);
        query.setString(1,"ACTIVE");
        List<JobApplication> list = query.list();
        return list;
    }
}