package com.MinicareStruts.action.sitter;

import com.MinicareStruts.model.JobApplication;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.SitterService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ListApplicationAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");

        SitterService sitterService = new SitterService();
        List<JobApplication> list = sitterService.listApplications(member.getMemberId());

        if (list.isEmpty()) {
            request.setAttribute("success","You have not applied to any Job yet.");
            return mapping.findForward("noJobs");
        } else {
            request.setAttribute("listOfApplications", list);
            return mapping.findForward("listOfApplications");
        }
    }
}
