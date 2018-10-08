package com.MinicareStruts.action.visitor;

import com.MinicareStruts.form.RegistrationForm;
import com.MinicareStruts.util.CommonUtil;
import com.MinicareStruts.util.Constants;
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

        String type = request.getParameter(Constants.TYPE);

        if(CommonUtil.isSeeker(type)) {
            registrationForm.setType(Constants.SEEKER);
        }
        else if(CommonUtil.isSitter(type)) {
            registrationForm.setType(Constants.SITTER);
        }
        else {
            request.setAttribute("errorType","Please Choose a Valid type before Registration");
            return mapping.findForward(Constants.FAILURE);
        }
        return mapping.findForward(Constants.SUCCESS);
    }
}