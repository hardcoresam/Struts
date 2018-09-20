package com.MinicareStruts.action.member;

import com.MinicareStruts.form.RegistrationForm;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.MemberService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditProfileAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RegistrationForm registrationForm = (RegistrationForm) form;

        HttpSession session = request.getSession(false);
        Member member = (Member) session.getAttribute("member");

        Double experience,expectedPay;
        Integer noOfChildren;
        if(member.getType().name().equalsIgnoreCase("seeker")) {
            experience = null;
            expectedPay = null;
            noOfChildren = Integer.parseInt(registrationForm.getNoOfChildren());
        }
        else {
            experience = Double.parseDouble(registrationForm.getExperience());
            expectedPay = Double.parseDouble(registrationForm.getExpectedPay());
            noOfChildren = null;
        }

        MemberService memberService = new MemberService();
        Member updatedMember = memberService.alterProfile(member.getMemberId(), registrationForm.getFirstName(), registrationForm.getLastName(),
                registrationForm.getPhoneNumber(), registrationForm.getAddress(),
                noOfChildren, registrationForm.getSpouseName(), experience, expectedPay, member.getType().name());


        session.invalidate();
        HttpSession updatedSession = request.getSession();
        updatedSession.setAttribute("member",updatedMember);

        request.setAttribute("success","Your Profile Was Updated Successfully");

        return mapping.findForward(member.getType().name().toLowerCase());
    }
}