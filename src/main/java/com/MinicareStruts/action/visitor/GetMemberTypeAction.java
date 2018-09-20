package com.MinicareStruts.action.visitor;

import com.MinicareStruts.form.RegistrationForm;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetMemberTypeAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RegistrationForm registrationForm = (RegistrationForm) form;

        String type = request.getParameter("type");

        if(StringUtils.equalsIgnoreCase(type,"seeker")) {
            registrationForm.setType("seeker");
        }
        else if(StringUtils.equalsIgnoreCase(type,"sitter")) {
            registrationForm.setType("sitter");
        }
        else {
            request.setAttribute("errorType","Please Choose a Valid type before Registration");
            return mapping.findForward("failure");
        }
        return mapping.findForward("success");
    }
}
