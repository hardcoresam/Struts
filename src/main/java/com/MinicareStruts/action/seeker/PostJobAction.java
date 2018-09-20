package com.MinicareStruts.action.seeker;

import com.MinicareStruts.form.PostJobForm;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.SeekerService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;

public class PostJobAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PostJobForm postJobForm = (PostJobForm) form;

        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");

        SeekerService seekerService = new SeekerService();
        seekerService.postJob(postJobForm.getTitle(), Double.parseDouble(postJobForm.getPayPerHour()), member.getMemberId(),
                Time.valueOf(postJobForm.getStartTime()+":00"), Time.valueOf(postJobForm.getEndTime()+":00"),
                Date.valueOf(postJobForm.getStartDate()), Date.valueOf(postJobForm.getEndDate()));

        return mapping.findForward("jobPosted");
    }
}