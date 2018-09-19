package com.MinicareStruts.action.visitor;

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

public class RegistrationAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        RegistrationForm registrationForm = (RegistrationForm)form;

        Double experience,expectedPay;
        Integer noOfChildren;
        if(registrationForm.getType().equalsIgnoreCase("seeker")) {
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
        Member member = memberService.registerUser(registrationForm.getFirstName(), registrationForm.getLastName(),
                registrationForm.getEmail(), registrationForm.getPhoneNumber(), registrationForm.getAddress(),
                noOfChildren, registrationForm.getSpouseName(), experience, expectedPay,
                registrationForm.getPassword(), registrationForm.getType());

        HttpSession session = request.getSession();
        session.setAttribute("member",member);

        if(registrationForm.getType().equalsIgnoreCase("seeker"))
            return mapping.findForward("seeker");
        else
            return mapping.findForward("sitter");
    }
}