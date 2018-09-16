package com.MinicareStruts.action.sitter;

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

        SitterService sitterService = new SitterService();
        sitterService.deleteApplication(applicationId);

        request.setAttribute("success","Application Was Deleted Successfully");
        return mapping.findForward("listApplications");
    }
}
