package com.MinicareStruts.action.member;

import com.MinicareStruts.form.RegistrationForm;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.model.Seeker;
import com.MinicareStruts.model.Sitter;
import com.MinicareStruts.service.SeekerService;
import com.MinicareStruts.service.SitterService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProfileInfoAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RegistrationForm registrationForm = (RegistrationForm) form;

        Member member = (Member) request.getSession().getAttribute("member");

        if(member.getType().name().equalsIgnoreCase("seeker")) {
            SeekerService seekerService = new SeekerService();
            Seeker seeker = seekerService.fetchMember(member.getMemberId());

            registrationForm.setMemberId(seeker.getMemberId());
            registrationForm.setFirstName(seeker.getFirstName());
            registrationForm.setLastName(seeker.getLastName());
            registrationForm.setPhoneNumber(seeker.getPhoneNumber());
            registrationForm.setAddress(seeker.getAddress());
            registrationForm.setNoOfChildren(String.valueOf(seeker.getNoOfChildren()));
            registrationForm.setSpouseName(seeker.getSpouseName());
            registrationForm.setType(member.getType().name().toLowerCase());
        }
        else {
            SitterService sitterService = new SitterService();
            Sitter sitter = sitterService.fetchMember(member.getMemberId());

            registrationForm.setMemberId(sitter.getMemberId());
            registrationForm.setFirstName(sitter.getFirstName());
            registrationForm.setLastName(sitter.getLastName());
            registrationForm.setPhoneNumber(sitter.getPhoneNumber());
            registrationForm.setAddress(sitter.getAddress());
            registrationForm.setExperience(String.valueOf(sitter.getExperience()));
            registrationForm.setExpectedPay(String.valueOf(sitter.getExpectedPay()));
            registrationForm.setType(member.getType().name().toLowerCase());
        }

        return mapping.findForward("success");
    }
}