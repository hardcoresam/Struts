package com.MinicareStruts.model;

import java.util.Set;

public class Seeker extends Member {
    private int noOfChildren;
    private String spouseName;
    private Set<Job> setOfJobs;

    public Seeker() {}

    public Seeker(String firstName, String lastName, String email, String phoneNumber, String address,
                  String password, int noOfChildren, String spouseName) {
        super(firstName, lastName, email, phoneNumber, address, password, MemberType.SEEKER);
        this.noOfChildren = noOfChildren;
        this.spouseName = spouseName;
    }

    public Seeker(String firstName, String lastName, String phoneNumber, String address, int noOfChildren, String spouseName) {
        super(firstName, lastName, phoneNumber, address, MemberType.SEEKER);
        this.noOfChildren = noOfChildren;
        this.spouseName = spouseName;
    }

    public int getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(int noOfChildren) {
        this.noOfChildren = noOfChildren;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public Set<Job> getSetOfJobs() { return setOfJobs; }

    public void setSetOfJobs(Set<Job> setOfJobs) { this.setOfJobs = setOfJobs; }
}