package com.MinicareStruts.model;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

public class Job {
    private int jobId;
    private String title;
    private double payPerHour;
    private Time startTime;
    private Time endTime;
    private Date startDate;
    private Date endDate;
    public enum Status {ACTIVE, INACTIVE;}
    private Status status;

    private Seeker seeker;
    private Set<JobApplication> setOfApplications;

    public Job() {}

    public Job(String title, double payPerHour, Time startTime, Time endTime, Date startDate, Date endDate) {
        this.title = title;
        this.payPerHour = payPerHour;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = Status.ACTIVE;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Time getStartTime() { return startTime; }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPayPerHour() { return payPerHour; }

    public void setPayPerHour(double payPerHour) { this.payPerHour = payPerHour; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    public Seeker getSeeker() { return seeker; }

    public void setSeeker(Seeker seeker) { this.seeker = seeker; }

    public Set<JobApplication> getSetOfApplications() { return setOfApplications; }

    public void setSetOfApplications(Set<JobApplication> setOfApplications) { this.setOfApplications = setOfApplications; }
}
