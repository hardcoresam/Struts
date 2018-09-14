package com.MinicareStruts.model;

import java.util.Set;

public class Sitter extends Member {
    private double experience;
    private double expectedPay;
    private Set<JobApplication> setOfApplications;

    public Sitter() {}

    public Sitter(String firstName, String lastName, String email, String phoneNumber, String address,
                  String password, double experience, double expectedPay) {
        super(firstName, lastName, email, phoneNumber, address, password, MemberType.SITTER);
        this.experience = experience;
        this.expectedPay = expectedPay;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public double getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(double expectedPay) {
        this.expectedPay = expectedPay;
    }

    public Set<JobApplication> getSetOfApplications() { return setOfApplications; }

    public void setSetOfApplications(Set<JobApplication> setOfApplications) { this.setOfApplications = setOfApplications; }
}
