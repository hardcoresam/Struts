package com.MinicareStruts.service;

import com.MinicareStruts.dao.*;
import com.MinicareStruts.model.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

public class SeekerService {
    //Put all Dao objects as the instance variables in the service Classes - Ask.

    public boolean checkForValidJobId(int jobId, int memberId) {
        JobDAO jobDao = new JobDAO();
        Job job = jobDao.getJobById(jobId);
        if(job!=null && job.getSeeker().getMemberId()==memberId)
            return true;
        else
            return false;
    }

    public Job getJobById(int jobId) {
        JobDAO jobDao = new JobDAO();
        return jobDao.getJobById(jobId);
    }

    public Seeker fetchMember(int seekerId) {
        SeekerDAO seekerDao = new SeekerDAO();
        Seeker seeker = seekerDao.getSeekerById(seekerId);
        return seeker;
    }

    public void postJob(String title, double payPerHour, int seekerId, Time startTime, Time endTime, Date startDate, Date endDate) {
        Job job = new Job(title, payPerHour, startTime, endTime, startDate, endDate);

        SeekerService seekerService = new SeekerService();
        Seeker seeker = seekerService.fetchMember(seekerId);
        job.setSeeker(seeker);

        JobDAO jobDao = new JobDAO();
        jobDao.postJob(job);
    }

    public void alterJob(int jobId, String title, double payPerHour, Time startTime, Time endTime, Date startDate, Date endDate) {
        JobDAO jobDao = new JobDAO();
        Job job = jobDao.getJobById(jobId);

        job.setTitle(title);
        job.setPayPerHour(payPerHour);
        job.setStartTime(startTime);
        job.setEndTime(endTime);
        job.setStartDate(startDate);
        job.setEndDate(endDate);
        jobDao.editJob(job);
    }

    public List<Job> listJobs(int seekerId) {
        JobDAO jobDao = new JobDAO();
        List<Job> list = jobDao.listJobs(seekerId);
        return list;
    }

    public void deleteJob(int jobId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        JobDAO jobDao = new JobDAO();
        Job job = jobDao.getJobById(jobId);

        Set<JobApplication> set = job.getSetOfApplications();
        for(JobApplication jobApplication : set) {
            jobApplication.setStatus(JobApplication.Status.INACTIVE);
            jobApplicationDao.deleteApplication(jobApplication);
        }
        job.setStatus(Job.Status.INACTIVE);
        jobDao.deleteJob(job);
    }

    public void closeAccount(int seekerId) {
        SeekerDAO seekerDao = new SeekerDAO();
        Seeker seeker = seekerDao.getSeekerById(seekerId);
        Set<Job> set = seeker.getSetOfJobs();

        for(Job job : set) {
            deleteJob(job.getJobId());
        }

        seeker.setStatus(Member.Status.INACTIVE);
        seekerDao.deleteSeeker(seeker);
    }

    public List<JobApplication> listApplications(int jobId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        List<JobApplication> list = jobApplicationDao.listApplicationsForSeeker(jobId);
        return list;
    }
}