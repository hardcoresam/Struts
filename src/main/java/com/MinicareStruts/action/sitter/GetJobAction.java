package com.MinicareStruts.action.sitter;

import com.MinicareStruts.form.ApplyJobForm;
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

public class GetJobAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = (Member)request.getSession().getAttribute(Constants.MEMBER);
        ApplyJobForm applyJobForm = (ApplyJobForm) form;
        SitterService sitterService = new SitterService();
        Sitter sitter = sitterService.fetchMember(member.getMemberId());

        //Job job = sitterService.getJobById(applyJobForm.getJobId());
        //Remove all the validations here and do in ApplyJobAction
//        if(job!=null && job.getStatus() == Job.Status.ACTIVE) {
//            applyJobForm.setExpectedPay(String.valueOf(sitter.getExpectedPay()));
//            return mapping.findForward(Constants.SUCCESS);
//        }
//        return mapping.findForward(Constants.FAILURE);


        //Prefilling the ExpectedPay of the Sitter when he is applying for a Job.
        applyJobForm.setExpectedPay(String.valueOf(sitter.getExpectedPay()));
        return mapping.findForward(Constants.SUCCESS);
    }
}