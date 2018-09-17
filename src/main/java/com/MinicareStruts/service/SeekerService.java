package com.MinicareStruts.service;

import com.MinicareStruts.dao.*;
import com.MinicareStruts.dto.ListApplicationDTO;
import com.MinicareStruts.form.PostJobForm;
import com.MinicareStruts.model.Job;
import com.MinicareStruts.model.JobApplication;
import com.MinicareStruts.model.Message;
import com.MinicareStruts.model.Seeker;

import javax.naming.NamingException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;

public class SeekerService {
    public void postJob(String title, double payPerHour, int seekerId, Time startTime, Time endTime, Date startDate,
                        Date endDate) {

        Job job = new Job(title, payPerHour, seekerId, startTime, endTime, startDate, endDate);
        JobDAO jobDao = new JobDAO();
        jobDao.postJob(job);
    }

    public void alterJob(int jobId, String title, double payPerHour, Time startTime, Time endTime, Date startDate,
                         Date endDate) {

        Job job = new Job(title, payPerHour, startTime, endTime, startDate, endDate);
        job.setJobId(jobId);

        JobDAO jobDao = new JobDAO();
        jobDao.editJob(job);
    }

    public List<Job> listJobs(int seekerId) {
        JobDAO jobDao = new JobDAO();
        List<Job> list = jobDao.listJobs(seekerId);
        return list;
    }

    public Seeker fetchMember(int seekerId) {
        SeekerDAO seekerDao = new SeekerDAO();
        Seeker seeker = seekerDao.getSeekerById(seekerId);
        return seeker;
    }

    public boolean deleteJob(int jobId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        if(jobApplicationDao.deleteApplicationsById(jobId)) {
            JobDAO jobDao = new JobDAO();
            return jobDao.deleteJob(jobId);
        }
        else {
            return false;
        }
    }

    public Job getJobById(int jobId) {
        JobDAO jobDao = new JobDAO();
        return jobDao.getJobById(jobId);
    }

    public boolean closeAccount(int seekerId) {
        //also ask pranav about updating 2 tables in one operation - like the one above.
        //Check whether we can make the Closing account(both sitter and seeker) operations in any other efficient way.

        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        JobDAO jobDao = new JobDAO();
        List<Job> list = jobDao.listJobs(seekerId);
        for(Job job : list) {
            jobApplicationDao.deleteApplicationsById(job.getJobId());
            jobDao.deleteJob(job.getJobId());
        }

        MemberDAO memberDao = new MemberDAO();
        return memberDao.closeAccount(seekerId);
    }

    public List<ListApplicationDTO> listApplications(int jobId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        List<ListApplicationDTO> list = jobApplicationDao.listApplicationsForSeeker(jobId);
        return list;
    }

    public List<Message> getMessages(int conversationId) {
        ConversationDAO conversationDao = new ConversationDAO();
        return conversationDao.getMessages(conversationId);
    }

    public void storeMessage(int conversationId, String content, int senderId) {
        Message message = new Message();
        long currentTime = System.currentTimeMillis();
        Time time = new Time(currentTime);
        message.setTime(time);
        message.setConversationId(conversationId);
        message.setContent(content);
        message.setSenderId(senderId);

        ConversationDAO conversationDao = new ConversationDAO();
        conversationDao.storeMessage(message);
    }
}
