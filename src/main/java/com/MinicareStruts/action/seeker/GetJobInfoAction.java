package com.MinicareStruts.action.seeker;

import com.MinicareStruts.form.PostJobForm;
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

public class GetJobInfoAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PostJobForm postJobForm = (PostJobForm) form;
        Member member = (Member)request.getSession().getAttribute(Constants.MEMBER);

        //Check for String Here.
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        SeekerService seekerService = new SeekerService();
        Job job = seekerService.getJobById(jobId);

        if(job!=null && job.getSeeker().getMemberId() == member.getMemberId()) {
            postJobForm.setJobId(job.getJobId());
            postJobForm.setTitle(job.getTitle());
            postJobForm.setPayPerHour(String.valueOf(job.getPayPerHour()));
            postJobForm.setStartTime(job.getStartTime().toString());
            postJobForm.setEndTime(job.getEndTime().toString());
            postJobForm.setStartDate(job.getStartDate().toString());
            postJobForm.setEndDate(job.getEndDate().toString());
            return mapping.findForward(Constants.SUCCESS);
        }
        return mapping.findForward("wrongJobId");
    }
}
