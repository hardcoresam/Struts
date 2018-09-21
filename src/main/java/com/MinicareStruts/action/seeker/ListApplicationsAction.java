package com.MinicareStruts.action.seeker;

import com.MinicareStruts.model.JobApplication;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.SeekerService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ListApplicationsAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        SeekerService seekerService = new SeekerService();
        Member member = (Member)request.getSession().getAttribute("member");

        if(seekerService.checkForValidJobId(jobId,member.getMemberId())) {
            List<JobApplication> list = seekerService.listApplications(jobId);
            if(list.isEmpty()) {
                request.setAttribute("successMsg","There are no applications for this job yet.");
                return mapping.findForward("noApplications");
            }
            else {
                request.setAttribute("listOfApplications",list);
                return mapping.findForward("listOfApplications");
            }
        }
        return mapping.findForward("wrongJobId");
    }
}