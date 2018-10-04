package com.MinicareStruts.action.member;

import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.SeekerService;
import com.MinicareStruts.service.SitterService;
import com.MinicareStruts.util.Constants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CloseAccountAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute(Constants.MEMBER);

        if(member.getType() == Member.MemberType.SEEKER) {
            SeekerService seekerService = new SeekerService();
            seekerService.closeAccount(member.getMemberId());
        }
        else {
            SitterService sitterService = new SitterService();
            sitterService.closeAccount(member.getMemberId());
        }
        session.invalidate();
        return mapping.findForward("closedAccount");
    }
}