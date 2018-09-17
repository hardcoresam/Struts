package com.MinicareStruts.model;

public class JobApplication {
    private int applicationId;
    private int jobId;
    private int sitterId;
    private double expectedPay;

    private Job job;
    private Sitter sitter;

    public JobApplication() {}

    public JobApplication(int jobId, double expectedPay, int sitterId) {
        this.jobId = jobId;
        this.sitterId = sitterId;
        this.expectedPay = expectedPay;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getSitterId() {
        return sitterId;
    }

    public void setSitterId(int sitterId) {
        this.sitterId = sitterId;
    }

    public double getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(double expectedPay) {
        this.expectedPay = expectedPay;
    }

    public Job getJob() { return job; }

    public void setJob(Job job) { this.job = job; }

    public Sitter getSitter() { return sitter; }

    public void setSitter(Sitter sitter) { this.sitter = sitter; }
}