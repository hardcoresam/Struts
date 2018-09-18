package com.MinicareStruts.service;

import com.MinicareStruts.dao.*;
import com.MinicareStruts.model.*;

import javax.naming.NamingException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Set;

public class SeekerService {
    public void postJob(String title, double payPerHour, int seekerId, Time startTime, Time endTime, Date startDate,
                        Date endDate) {

        Job job = new Job(title, payPerHour, seekerId, startTime, endTime, startDate, endDate);

        SeekerService seekerService = new SeekerService();
        Seeker seeker = seekerService.fetchMember(seekerId);
        job.setSeeker(seeker);

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

    public Set<Job> listJobs(int seekerId) {
        JobDAO jobDao = new JobDAO();
        Set<Job> list = jobDao.listJobs(seekerId);
        return list;
    }

    public Seeker fetchMember(int seekerId) {
        SeekerDAO seekerDao = new SeekerDAO();
        Seeker seeker = seekerDao.getSeekerById(seekerId);
        return seeker;
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

    public Job getJobById(int jobId) {
        JobDAO jobDao = new JobDAO();
        return jobDao.getJobById(jobId);
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

    public List<Message> getMessages(int conversationId) {
        ConversationDAO conversationDao = new ConversationDAO();
        return conversationDao.getMessages(conversationId);
    }

    public void storeMessage(int conversationId, String content, int senderId, Conversation conversation, Member member) {
        Message message = new Message();
        long currentTime = System.currentTimeMillis();
        Time time = new Time(currentTime);
        message.setTime(time);
        message.setConversationId(conversationId);
        message.setContent(content);
        message.setSenderId(senderId);
        message.setConversation(conversation);
        message.setMember(member);

        ConversationDAO conversationDao = new ConversationDAO();
        conversationDao.storeMessage(message);
    }
}
