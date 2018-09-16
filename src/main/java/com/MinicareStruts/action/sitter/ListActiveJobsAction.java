package com.MinicareStruts.action.sitter;

import com.MinicareStruts.model.Job;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.SitterService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ListActiveJobsAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");
        SitterService sitterService = new SitterService();

        List<Job> list = sitterService.listActiveJobs(member.getMemberId());
        if(list.isEmpty()) {
            request.setAttribute("success","You have applied to all the jobs.");
            return mapping.findForward("allJobsApplied");
        }
        else {
            request.setAttribute("listOfActiveJobs",list);
            return mapping.findForward("listOfActiveJobs");
        }
    }
}