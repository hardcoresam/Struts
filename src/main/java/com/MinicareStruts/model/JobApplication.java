package com.MinicareStruts.model;

public class JobApplication {
    private int applicationId;
    private double expectedPay;

    public enum Status{ACTIVE,INACTIVE};
    private Status status;

    private Job job;
    private Sitter sitter;

    public JobApplication() {}

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
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

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
}