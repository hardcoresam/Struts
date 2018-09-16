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

    public void applyJob(int jobId, float expectedPay, int sitterId) {

        JobApplication jobApplication = new JobApplication(jobId, expectedPay, sitterId);

        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        jobApplicationDao.applyJob(jobApplication);
    }

    public List<JobApplicationDTO> listApplications(int sitterId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        List<JobApplicationDTO> list = jobApplicationDao.listApplications(sitterId);
        return list;
    }

    public void deleteApplication(int applicationId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        jobApplicationDao.deleteApplication(applicationId);
    }

    public boolean closeAccount(int sitterId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        jobApplicationDao.deleteApplicationsBySitterId(sitterId);

        MemberDAO memberDao = new MemberDAO();
        return memberDao.closeAccount(sitterId);
    }
}
