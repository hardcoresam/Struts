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

        //Here we are getting an exception bcoz if user registers as a seeker and then this
        //Double.parseDouble(registrationForm.getExperience())  would throw an exception right. So use an
        //if and else and solve this problem.

        MemberService memberService = new MemberService();
        Member member = memberService.registerUser(registrationForm.getFirstName(), registrationForm.getLastName(),
                registrationForm.getEmail(), registrationForm.getPhoneNumber(), registrationForm.getAddress(),
                Integer.parseInt(registrationForm.getNoOfChildren()), registrationForm.getSpouseName(),
                Double.parseDouble(registrationForm.getExperience()), Double.parseDouble(registrationForm.getExpectedPay()),
                registrationForm.getPassword(), registrationForm.getType());

        HttpSession session = request.getSession();
        session.setAttribute("member",member);

        if(registrationForm.getType().equalsIgnoreCase("seeker"))
            return mapping.findForward("seeker");
        else
            return mapping.findForward("sitter");
    }
}