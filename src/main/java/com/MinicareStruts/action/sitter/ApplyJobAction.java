package com.MinicareStruts.action.sitter;

import com.MinicareStruts.dao.JobDAO;
import com.MinicareStruts.form.ApplyJobForm;
import com.MinicareStruts.model.Job;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.SitterService;
import com.MinicareStruts.util.Constants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplyJobAction extends Action {

    private Logger logger = Logger.getLogger(ApplyJobAction.class.getName());

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = (Member)request.getSession().getAttribute(Constants.MEMBER);
        ApplyJobForm applyJobForm = (ApplyJobForm) form;

        //For Strings, inside the form the jobId would be stored as 0
        if(applyJobForm.getJobId()==0) {
            return mapping.findForward(Constants.FAILURE);
        }
        JobDAO jobDao = new JobDAO();
        Job job = jobDao.getJobById(applyJobForm.getJobId());

        SitterService sitterService = new SitterService();
        if(job.getStatus() == Job.Status.ACTIVE) {
            sitterService.applyJob(applyJobForm.getJobId(), Double.parseDouble(applyJobForm.getExpectedPay()), member.getMemberId());
            return mapping.findForward(Constants.SUCCESS);
        }
        return mapping.findForward(Constants.FAILURE);
    }
}