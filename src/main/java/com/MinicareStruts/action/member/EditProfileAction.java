package com.MinicareStruts.action.member;

import com.MinicareStruts.form.RegistrationForm;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.MemberService;
import com.MinicareStruts.util.Constants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditProfileAction extends Action {
    private Double experience;
    private Double expectedPay;
    private Integer noOfChildren;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RegistrationForm registrationForm = (RegistrationForm) form;

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(Constants.MEMBER);

        if(member.getType().name().equalsIgnoreCase(Constants.SEEKER)) {
            noOfChildren = Integer.parseInt(registrationForm.getNoOfChildren());
        }
        else {
            experience = Double.parseDouble(registrationForm.getExperience());
            expectedPay = Double.parseDouble(registrationForm.getExpectedPay());
        }

        MemberService memberService = new MemberService();
        Member updatedMember = memberService.alterProfile(member.getMemberId(), registrationForm.getFirstName(), registrationForm.getLastName(),
                registrationForm.getPhoneNumber(), registrationForm.getAddress(),
                noOfChildren, registrationForm.getSpouseName(), experience, expectedPay, member.getType().name());

        session.invalidate();
        HttpSession updatedSession = request.getSession();
        updatedSession.setAttribute(Constants.MEMBER,updatedMember);

        return mapping.findForward(member.getType().name().toLowerCase());
    }
}