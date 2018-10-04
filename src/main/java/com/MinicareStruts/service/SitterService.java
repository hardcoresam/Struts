package com.MinicareStruts.service;

import com.MinicareStruts.dao.JobApplicationDAO;
import com.MinicareStruts.dao.JobDAO;
import com.MinicareStruts.dao.SitterDAO;
import com.MinicareStruts.model.Job;
import com.MinicareStruts.model.JobApplication;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.model.Sitter;

import java.util.List;
import java.util.Set;

public class SitterService {

    public Sitter fetchMember(int sitterId) {
        SitterDAO sitterDao = new SitterDAO();
        return sitterDao.getSitterById(sitterId);
    }

    public List<Job> listActiveJobs(int sitterId) {
        JobDAO jobDao = new JobDAO();
        List<Job> list = jobDao.listActiveJobs(sitterId);
        return list;
    }

    public void applyJob(int jobId, double expectedPay, int sitterId) {
        JobApplication jobApplication = new JobApplication();
        jobApplication.setExpectedPay(expectedPay);
        jobApplication.setStatus(JobApplication.Status.ACTIVE);

        JobDAO jobDao = new JobDAO();
        Job job = jobDao.getJobById(jobId);
        jobApplication.setJob(job);

        SitterDAO sitterDao = new SitterDAO();
        Sitter sitter = sitterDao.getSitterById(sitterId);
        jobApplication.setSitter(sitter);

        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        jobApplicationDao.applyJob(jobApplication);
    }

    public List<JobApplication> listApplications(int sitterId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        List<JobApplication> list = jobApplicationDao.listApplications(sitterId);
        return list;
    }

    public boolean deleteApplication(int applicationId, int memberId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        JobApplication jobApplication = jobApplicationDao.getJobApplicationById(applicationId);

        if(jobApplication!=null && jobApplication.getSitter().getMemberId() == memberId) {
            jobApplication.setStatus(JobApplication.Status.INACTIVE);
            jobApplicationDao.deleteApplication(jobApplication);
            return true;
        }
        return false;
    }

    public void closeAccount(int sitterId) {
        SitterDAO sitterDao = new SitterDAO();
        Sitter sitter = sitterDao.getSitterById(sitterId);
        Set<JobApplication> set = sitter.getSetOfApplications();

        for(JobApplication jobApplication : set) {
            deleteApplication(jobApplication.getApplicationId(),sitterId);
        }

        sitter.setStatus(Member.Status.INACTIVE);
        sitterDao.deleteSeeker(sitter);
    }
}