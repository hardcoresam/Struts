package com.MinicareStruts.service;

import com.MinicareStruts.dao.ConversationDAO;
import com.MinicareStruts.dao.MemberDAO;
import com.MinicareStruts.model.*;
import com.MinicareStruts.util.PasswordUtil;

import java.sql.Time;
import java.util.List;

public class MemberService {

    public Member fetchMember(String email, String password) {
        MemberDAO memberDao = new MemberDAO();

        //Write PasswordUtil.getHashedPassword(password) in the end.
        return memberDao.fetchMember(email, password);
    }

    public Member registerUser(String firstName, String lastName, String email, String phoneNumber, String address,
                                Integer noOfChildren, String spouseName, Double experience, Double expectedPay, String password,
                                String type) {
        MemberDAO memberDao = new MemberDAO();
        if(type.equalsIgnoreCase("seeker")) {

            //Write PasswordUtil.getHashedPassword(password) in the end.
            Seeker seeker = new Seeker(firstName, lastName, email, phoneNumber, address, password,
                    noOfChildren, spouseName);
            seeker.setMemberId(memberDao.registerUser(seeker));
            return seeker;
        }
        else {

            //Write PasswordUtil.getHashedPassword(password) in the end.
            Sitter sitter = new Sitter(firstName, lastName, email, phoneNumber, address, password,
                    experience, expectedPay);
            sitter.setMemberId(memberDao.registerUser(sitter));
            return sitter;
        }
    }

    public boolean isEmailRegistered(String email){
        MemberDAO memberDao = new MemberDAO();
        return memberDao.isEmailRegistered(email);
    }

    public Member alterProfile(int memberId, String firstName, String lastName, String phoneNumber, String address,
                               Integer noOfChildren, String spouseName, Double experience, Double expectedPay, String type) {
        Member member = null;
        MemberDAO memberDao = new MemberDAO();
        if(type.equalsIgnoreCase("seeker")) {
            SeekerService seekerService = new SeekerService();
            Seeker seeker = seekerService.fetchMember(memberId);

            seeker.setFirstName(firstName);
            seeker.setLastName(lastName);
            seeker.setPhoneNumber(phoneNumber);
            seeker.setAddress(address);
            seeker.setNoOfChildren(noOfChildren);
            seeker.setSpouseName(spouseName);

            member = memberDao.alterUser(seeker);
        }
        else {
            SitterService sitterService = new SitterService();
            Sitter sitter = sitterService.fetchMember(memberId);

            sitter.setFirstName(firstName);
            sitter.setLastName(lastName);
            sitter.setPhoneNumber(phoneNumber);
            sitter.setAddress(address);
            sitter.setExperience(experience);
            sitter.setExpectedPay(expectedPay);

            member = memberDao.alterUser(sitter);
        }
        return member;
    }

    public int getConversationId(int seekerId, int sitterId) {
        SeekerService seekerService = new SeekerService();
        SitterService sitterService = new SitterService();
        Conversation conversation = new Conversation();
        ConversationDAO conversationDao = new ConversationDAO();

        Seeker seeker = seekerService.fetchMember(seekerId);

        Sitter sitter = sitterService.fetchMember(sitterId);

        conversation.setSeeker(seeker);
        conversation.setSitter(sitter);
        return conversationDao.getConversationId(conversation);
    }

    public Conversation getConversationById(int conversationId) {
        ConversationDAO conversationDao = new ConversationDAO();
        return conversationDao.getConversationById(conversationId);
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

    public List<Message> getMessages(int conversationId) {
        ConversationDAO conversationDao = new ConversationDAO();
        return conversationDao.getMessages(conversationId);
    }

    public List<Conversation> getUserNames(int memberId, String type) {
        ConversationDAO conversationDao = new ConversationDAO();
        return conversationDao.getUserNames(memberId,type);
    }

    public List<Member> getNewConversation(int memberId, String type) {
        ConversationDAO conversationDao = new ConversationDAO();
        return conversationDao.getNewConversation(memberId,type);
    }

}
