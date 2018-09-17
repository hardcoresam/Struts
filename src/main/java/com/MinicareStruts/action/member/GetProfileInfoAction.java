package com.MinicareStruts.action.member;

import com.MinicareStruts.form.AlterProfileForm;
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
import javax.servlet.http.HttpSession;

public class GetProfileInfoAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AlterProfileForm alterProfileForm = (AlterProfileForm) form;

        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");

        //CHECK THE CODE HERE -----------------

        if(member.getType().toString().equalsIgnoreCase("seeker")) {
            SeekerService seekerService = new SeekerService();
            Seeker seeker = seekerService.fetchMember(member.getMemberId());

            alterProfileForm.setMemberId(seeker.getMemberId());
            alterProfileForm.setFirstName(seeker.getFirstName());
            alterProfileForm.setLastName(seeker.getLastName());
            alterProfileForm.setPhoneNumber(seeker.getPhoneNumber());
            alterProfileForm.setAddress(seeker.getAddress());
            alterProfileForm.setNoOfChildren(String.valueOf(seeker.getNoOfChildren()));
            alterProfileForm.setSpouseName(seeker.getSpouseName());
            alterProfileForm.setType("seeker");
        }
        else {
            SitterService sitterService = new SitterService();
            Sitter sitter = sitterService.fetchMember(member.getMemberId());

            alterProfileForm.setMemberId(sitter.getMemberId());
            alterProfileForm.setFirstName(sitter.getFirstName());
            alterProfileForm.setLastName(sitter.getLastName());
            alterProfileForm.setPhoneNumber(sitter.getPhoneNumber());
            alterProfileForm.setAddress(sitter.getAddress());
            alterProfileForm.setExperience(String.valueOf(sitter.getExperience()));
            alterProfileForm.setExpectedPay(String.valueOf(sitter.getExpectedPay()));
            alterProfileForm.setType("sitter");
        }
        return mapping.findForward("success");
    }
}