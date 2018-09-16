package com.MinicareStruts.action.sitter;

import com.MinicareStruts.form.ApplyJobForm;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.SitterService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplyJobAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Member member = (Member)request.getSession().getAttribute("member");
        ApplyJobForm applyJobForm = (ApplyJobForm) form;

        SitterService sitterService = new SitterService();
        sitterService.applyJob(Integer.parseInt(applyJobForm.getJobId()),
                Float.parseFloat(applyJobForm.getExpectedPay()), member.getMemberId());

        //Think how to display this to user in List Applications page.
        request.setAttribute("success","You have applied for the job Successfully");
        return mapping.findForward("listApplications");
    }
}