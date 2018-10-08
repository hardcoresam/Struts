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
        SeekerService seekerService = new SeekerService();

        if(seekerService.checkForValidJobId(postJobForm.getJobId(),member.getMemberId())) {
            Job job = seekerService.getJobById(postJobForm.getJobId());
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
