package com.MinicareStruts.action.seeker;

import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.SeekerService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteJobAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        SeekerService seekerService = new SeekerService();

        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");

        if(!seekerService.deleteJob(jobId,member.getMemberId())) {
            request.setAttribute("successMsg", "You are not authorized to delete this Job");
        }
        else {
            request.setAttribute("successMsg", "Job Was Deleted Successfully");
        }

        return mapping.findForward("jobDeleted");
    }
}