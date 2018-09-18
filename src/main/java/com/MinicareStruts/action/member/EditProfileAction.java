package com.MinicareStruts.action.member;

import com.MinicareStruts.form.AlterProfileForm;
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

        AlterProfileForm alterProfileForm = (AlterProfileForm) form;


        Double experience,expectedPay;
        Integer noOfChildren;
        if(alterProfileForm.getType().equalsIgnoreCase("seeker")) {
            experience = null;
            expectedPay = null;
            noOfChildren = Integer.parseInt(alterProfileForm.getNoOfChildren());
        }
        else {
            experience = Double.parseDouble(alterProfileForm.getExperience());
            expectedPay = Double.parseDouble(alterProfileForm.getExpectedPay());
            noOfChildren = null;
        }

        MemberService memberService = new MemberService();
        Member updatedMember = memberService.alterProfile(alterProfileForm.getMemberId(), alterProfileForm.getFirstName(), alterProfileForm.getLastName(),
                alterProfileForm.getPhoneNumber(), alterProfileForm.getAddress(),
                noOfChildren, alterProfileForm.getSpouseName(), experience, expectedPay, alterProfileForm.getType());

        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");
        session.invalidate();
        HttpSession updatedSession = request.getSession();
        updatedSession.setAttribute("member",updatedMember);

        request.setAttribute("success","Your Profile Was Updated Successfully");

        return mapping.findForward(alterProfileForm.getType().toLowerCase());
    }
}