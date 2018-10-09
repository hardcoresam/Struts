package com.MinicareStruts.action.sitter;

import com.MinicareStruts.form.ApplyJobForm;
import com.MinicareStruts.model.Job;
import com.MinicareStruts.model.JobApplication;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.model.Sitter;
import com.MinicareStruts.service.SitterService;
import com.MinicareStruts.util.Constants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class ApplyJobAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = (Member)request.getSession().getAttribute(Constants.MEMBER);
        ApplyJobForm applyJobForm = (ApplyJobForm) form;
        SitterService sitterService = new SitterService();
        Sitter sitter = sitterService.fetchMember(member.getMemberId());
        Job job = sitterService.getJobById(applyJobForm.getJobId());

        if(job!=null && job.getStatus() == Job.Status.ACTIVE) {


            //Check if there is any better Way and Confirm This
            Set<JobApplication> jobApplicationSet = sitter.getSetOfApplications();
            for(JobApplication jobApplication : jobApplicationSet) {
                if(jobApplication.getJob().getJobId() == applyJobForm.getJobId())
                    return mapping.findForward(Constants.FAILURE);
            }


            sitterService.applyJob(applyJobForm.getJobId(), Double.parseDouble(applyJobForm.getExpectedPay()), member.getMemberId());
            return mapping.findForward(Constants.SUCCESS);
        }
        return mapping.findForward(Constants.FAILURE);
    }
}