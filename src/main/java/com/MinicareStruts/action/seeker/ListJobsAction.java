package com.MinicareStruts.action.seeker;

import com.MinicareStruts.model.Job;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.SeekerService;
import com.MinicareStruts.util.Constants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ListJobsAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = (Member)request.getSession().getAttribute(Constants.MEMBER);

        SeekerService seekerService = new SeekerService();
        List<Job> list = seekerService.listJobs(member.getMemberId());
        if(list.isEmpty()) {
            request.setAttribute(Constants.SUCCESS,"There are no jobs which you have posted yet.");
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