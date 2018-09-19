package com.MinicareStruts.service;

import com.MinicareStruts.dao.*;
import com.MinicareStruts.model.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

public class SeekerService {
    //Put all Dao objects as the instance variables in the service Classes - Ask.

    public Job getJobById(int jobId) {
        JobDAO jobDao = new JobDAO();
        return jobDao.getJobById(jobId);
    }

    public Seeker fetchMember(int seekerId) {
        SeekerDAO seekerDao = new SeekerDAO();
        Seeker seeker = seekerDao.getSeekerById(seekerId);
        return seeker;
    }

    public void postJob(String title, double payPerHour, int seekerId, Time startTime, Time endTime, Date startDate,
                        Date endDate) {

        Job job = new Job(title, payPerHour, startTime, endTime, startDate, endDate);
        job.setStatus(Job.Status.ACTIVE);

        SeekerService seekerService = new SeekerService();
        Seeker seeker = seekerService.fetchMember(seekerId);
        job.setSeeker(seeker);

        JobDAO jobDao = new JobDAO();
        jobDao.postJob(job);
    }

    public void alterJob(int jobId, String title, double payPerHour, Time startTime, Time endTime, Date startDate,
                         Date endDate) {
        JobDAO jobDao = new JobDAO();
        Job job = jobDao.getJobById(jobId);

        job.setTitle(title);
        job.setPayPerHour(payPerHour);
        job.setStartTime(startTime);
        job.setEndTime(endTime);
        job.setStartDate(startDate);
        job.setEndDate(endDate);
        job.setStatus(Job.Status.ACTIVE);

        jobDao.editJob(job);
    }

    public List<Job> listJobs(int seekerId) {
        JobDAO jobDao = new JobDAO();
        List<Job> list = jobDao.listJobs(seekerId);
        return list;
    }




    public boolean deleteJob(int jobId, int memberId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        JobDAO jobDao = new JobDAO();
        Job job = jobDao.getJobById(jobId);

        if(job==null) {
            return false;
        }
        if(job.getSeeker().getMemberId()!=memberId) {
            return false;
        }

        Set<JobApplication> set = job.getSetOfApplications();
        for(JobApplication jobApplication : set) {
            jobApplication.setStatus(JobApplication.Status.INACTIVE);
            jobApplicationDao.deleteApplication(jobApplication);
        }
        job.setStatus(Job.Status.INACTIVE);
        jobDao.deleteJob(job);
        return true;
    }

    public void closeAccount(int seekerId) {
        SeekerDAO seekerDao = new SeekerDAO();
        Seeker seeker = seekerDao.getSeekerById(seekerId);
        Set<Job> set = seeker.getSetOfJobs();

        for(Job job : set) {
            deleteJob(job.getJobId(),seekerId);
        }

        seeker.setStatus(Member.Status.INACTIVE);
        seekerDao.deleteSeeker(seeker);
    }

    public List<JobApplication> listApplications(int jobId) {
        JobApplicationDAO jobApplicationDao = new JobApplicationDAO();
        List<JobApplication> list = jobApplicationDao.listApplicationsForSeeker(jobId);
        return list;
    }


    //These Should be inside MemberService class.
    public List<Message> getMessages(int conversationId) {
        ConversationDAO conversationDao = new ConversationDAO();
        return conversationDao.getMessages(conversationId);
    }

    public void storeMessage(String content, Conversation conversation, Member member) {
        Message message = new Message();
        long currentTime = System.currentTimeMillis();
        Time time = new Time(currentTime);
        message.setTime(time);
        message.setContent(content);
        message.setConversation(conversation);
        message.setMember(member);

        ConversationDAO conversationDao = new ConversationDAO();
        conversationDao.storeMessage(message);
    }
}
