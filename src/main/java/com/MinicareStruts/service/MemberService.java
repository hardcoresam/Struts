package com.MinicareStruts.service;

import com.MinicareStruts.dao.ConversationDAO;
import com.MinicareStruts.dao.MemberDAO;
import com.MinicareStruts.form.AlterProfileForm;
import com.MinicareStruts.form.LoginForm;
import com.MinicareStruts.form.RegistrationForm;
import com.MinicareStruts.model.Conversation;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.model.Seeker;
import com.MinicareStruts.model.Sitter;

public class MemberService {

    //For Hibernate.
    //i used Wrapper classes here for Some of the fields below bcoz - See RegistrationAction problem.
    public Member registerUser(String firstName, String lastName, String email, String phoneNumber, String address,
                                Integer noOfChildren, String spouseName, Double experience, Double expectedPay, String password,
                                String type) {
        MemberDAO memberDao = new MemberDAO();
        if(type.equalsIgnoreCase("seeker")) {
            Seeker seeker = new Seeker(firstName, lastName, email, phoneNumber, address, password, noOfChildren, spouseName);
            seeker.setMemberId(memberDao.registerUser(seeker));
            return seeker;
        }
        else {
            Sitter sitter = new Sitter(firstName, lastName, email, phoneNumber, address, password, experience, expectedPay);
            sitter.setMemberId(memberDao.registerUser(sitter));
            return sitter;
        }
    }

    /*
    public Member registerUser(RegistrationForm form) {
        int memberId = -1;
        Seeker seeker = null;
        Sitter sitter = null;
        if(form.getType().equals("seeker")) {

            seeker = new Seeker(form.getFirstName(), form.getLastName(), form.getEmail(), form.getPhoneNumber(),
                    form.getAddress(), form.getPassword(), Integer.parseInt(form.getNoOfChildren()), form.getSpouseName());

            MemberDAO memberDao = new MemberDAO();
            memberId = memberDao.registerUser(seeker);
            seeker.setMemberId(memberId);
            return seeker;
        }
        else {
            sitter = new Sitter(form.getFirstName(), form.getLastName(), form.getEmail(),
                    form.getPhoneNumber(), form.getAddress(), form.getPassword(), Double.parseDouble(form.getExperience()),
                    Double.parseDouble(form.getExpectedPay()));

            MemberDAO memberDao = new MemberDAO();
            memberId = memberDao.registerUser(sitter);
            sitter.setMemberId(memberId);
            return sitter;
        }
    }
    */

    public boolean isEmailRegistered(String email) {
        MemberDAO memberDao = new MemberDAO();
        return memberDao.isEmailRegistered(email);
    }

    //For Hibernate
    public Member fetchMember(String email, String password) {
        MemberDAO memberDao = new MemberDAO();

        Member member = memberDao.fetchMember(email, password);
        return member;
    }

    /*
    public Member fetchMember(LoginForm loginForm) {
        MemberDAO memberDao = new MemberDAO();
        Member member = memberDao.fetchMember(loginForm);
        return member;
    }
    */

    public Member alterProfile(String firstName, String lastName, String phoneNumber, String address,
                               Integer noOfChildren, String spouseName, Double experience, Double expectedPay, String type) {
        Member member = null;
        MemberDAO memberDao = new MemberDAO();
        if(type.equalsIgnoreCase("seeker")) {
            Seeker seeker = new Seeker(firstName, lastName, phoneNumber, address, noOfChildren, spouseName);
            memberDao.alterUser(seeker);
        }
        else {
            Sitter sitter = new Sitter(firstName, lastName, phoneNumber, address, experience, expectedPay);
            memberDao.alterUser(sitter);
        }
        return member;
    }

    /*
    public Member alterProfile(AlterProfileForm alterForm) {
        Member member = null;
        if(alterForm.getType().equalsIgnoreCase("seeker")) {
            Seeker seeker = new Seeker();
            seeker.setMemberId(alterForm.getMemberId());
            seeker.setFirstName(alterForm.getFirstName());
            seeker.setLastName(alterForm.getLastName());
            seeker.setPhoneNumber(alterForm.getPhoneNumber());
            seeker.setAddress(alterForm.getAddress());
            seeker.setNoOfChildren(Integer.parseInt(alterForm.getNoOfChildren()));
            seeker.setSpouseName(alterForm.getSpouseName());
            seeker.setType(Member.MemberType.SEEKER);

            MemberDAO memberDao = new MemberDAO();
            member = memberDao.alterUser(seeker);
        }
        else {
            Sitter sitter = new Sitter();
            sitter.setMemberId(alterForm.getMemberId());
            sitter.setFirstName(alterForm.getFirstName());
            sitter.setLastName(alterForm.getLastName());
            sitter.setPhoneNumber(alterForm.getPhoneNumber());
            sitter.setAddress(alterForm.getAddress());
            sitter.setExperience(Double.parseDouble(alterForm.getExperience()));
            sitter.setExpectedPay(Double.parseDouble(alterForm.getExpectedPay()));
            sitter.setType(Member.MemberType.SITTER);

            MemberDAO memberDao = new MemberDAO();
            member = memberDao.alterUser(sitter);
        }
        return member;
    }
    */

    public int getConversationId(int seekerId, int sitterId) {
        Conversation conversation = new Conversation(seekerId,sitterId);
        ConversationDAO conversationDao = new ConversationDAO();
        return conversationDao.getConversationId(conversation);
    }
}
