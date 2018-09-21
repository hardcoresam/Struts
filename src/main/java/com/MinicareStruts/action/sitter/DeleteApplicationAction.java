package com.MinicareStruts.action.sitter;

import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.SitterService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteApplicationAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int applicationId = Integer.parseInt(request.getParameter("applicationId"));
        Member member = (Member)request.getSession().getAttribute("member");

        SitterService sitterService = new SitterService();
        if(sitterService.deleteApplication(applicationId,member.getMemberId())) {
            return mapping.findForward("success");
        }
        return mapping.findForward("failure");
    }
}
