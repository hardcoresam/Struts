package com.MinicareStruts.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyExceptionHandler extends ExceptionHandler {
    private static final Logger logger = Logger.getLogger(MyExceptionHandler.class.getName());
    @Override
    public ActionForward execute(Exception ex, ExceptionConfig ae, ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServletException {
        logger.log(Level.SEVERE, "Exception Occurred In Action ", ex);
        return super.execute(ex, ae, mapping, form, request, response);
    }
}
