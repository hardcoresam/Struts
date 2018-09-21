package com.MinicareStruts.action.seeker;

import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.SeekerService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteJobAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        Member member = (Member)request.getSession().getAttribute("member");

        SeekerService seekerService = new SeekerService();
        if(seekerService.checkForValidJobId(jobId,member.getMemberId())) {
            seekerService.deleteJob(jobId);
            return mapping.findForward("success");
        }
        return mapping.findForward("wrongJobId");
    }
}