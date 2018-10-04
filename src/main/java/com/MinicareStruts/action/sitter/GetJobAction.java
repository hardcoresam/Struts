package com.MinicareStruts.action.sitter;

import com.MinicareStruts.dao.JobDAO;
import com.MinicareStruts.form.ApplyJobForm;
import com.MinicareStruts.model.Job;
import com.MinicareStruts.util.Constants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetJobAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplyJobForm applyJobForm = (ApplyJobForm) form;
        int jobId = Integer.parseInt(request.getParameter("jobId"));

        JobDAO jobDao = new JobDAO();
        Job job = jobDao.getJobById(jobId);
        if(job.getStatus() == Job.Status.ACTIVE) {
            applyJobForm.setJobId(job.getJobId());
            return mapping.findForward(Constants.SUCCESS);
        }
        return mapping.findForward(Constants.FAILURE);
    }
}