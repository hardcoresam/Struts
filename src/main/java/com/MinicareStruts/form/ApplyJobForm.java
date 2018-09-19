package com.MinicareStruts.form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class ApplyJobForm extends ActionForm{
    private String expectedPay;

    //See why this is required.
    private String jobId;

    public ApplyJobForm() {}

    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }

    public String getJobId() { return jobId; }

    public void setJobId(String jobId) { this.jobId = jobId; }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        jobId = request.getParameter("jobId");

        ActionErrors ae = new ActionErrors();

        if(expectedPay.equals(""))
            ae.add("expectedPay",new ActionMessage("job.required","Expected Pay"));
        else if(!expectedPay.matches("^0$|^[1-9]+([\\.]?[0-9]+)?$"))
            ae.add("expectedPay",new ActionMessage("job.expectedPay.notValid"));

        return ae;
    }
}