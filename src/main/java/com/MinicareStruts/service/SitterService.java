package com.MinicareStruts.service;

import com.MinicareStruts.dao.JobApplicationDAO;
import com.MinicareStruts.dao.JobDAO;
import com.MinicareStruts.dao.MemberDAO;
import com.MinicareStruts.dao.SitterDAO;
import com.MinicareStruts.dto.JobApplicationDTO;
import com.MinicareStruts.form.ApplyJobForm;
import com.MinicareStruts.model.Job;
import com.MinicareStruts.model.JobApplication;
import com.MinicareStruts.model.Sitter;

import java.util.List;

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

        JobApplication jobApplication = new JobApplication(jobId, expectedPay, sitterId);

        JobDAO jobDao = new JobDAO();
        Job job = jobDao.getJobById(jobId);
        jobApplication.setJob(job);

        SitterDAO sitterDao = new SitterDAO();
        Sitter sitter = sitterDao.getSitterById(sitterId);
        jobApplication.setSitter(sitter);

        jobApplication.setStatus(JobApplication.Status.ACTIVE);

        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        jobApplicationDao.applyJob(jobApplication);
    }

    public List<JobApplication> listApplications(int sitterId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        List<JobApplication> list = jobApplicationDao.listApplications(sitterId);
        return list;
    }

    public void deleteApplication(int applicationId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        JobApplication jobApplication = jobApplicationDao.getJobApplicationById(applicationId);
        jobApplication.setStatus(JobApplication.Status.INACTIVE);

        jobApplicationDao.deleteApplication(jobApplication);
    }

    public boolean closeAccount(int sitterId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        jobApplicationDao.deleteApplicationsBySitterId(sitterId);

        MemberDAO memberDao = new MemberDAO();
        return memberDao.closeAccount(sitterId);
    }
}
