package com.MinicareStruts.action.seeker;

import com.MinicareStruts.model.Job;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.SeekerService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

public class ListJobsAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");

        SeekerService seekerService = new SeekerService();
        Set<Job> list = seekerService.listJobs(member.getMemberId());
        if(list.isEmpty()) {
            request.setAttribute("success","There are no jobs which you have posted yet.");
            return mapping.findForward("noJobs");
        }
        else {
            //This code is for - when we have no applications, we would forward to this page only.So for that, we use this.
            String successMsg = (String)request.getAttribute("successMsg");
            request.setAttribute("successMsg",successMsg);

            request.setAttribute("listOfJobs",list);
            return mapping.findForward("listOfJobs");
        }
    }
}