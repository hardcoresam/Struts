package com.MinicareStruts.form;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class ApplyJobForm extends ActionForm{
    private String expectedPay;

    //See why this is required.
    private int jobId;

    public ApplyJobForm() {}

    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }

    public int getJobId() { return jobId; }

    public void setJobId(int jobId) { this.jobId = jobId; }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors ae = new ActionErrors();

        if(StringUtils.isBlank(expectedPay))
            ae.add("expectedPay",new ActionMessage("job.required","Expected Pay"));
        else if(!expectedPay.matches("^0$|^[1-9]+([\\.]?[0-9]+)?$"))
            ae.add("expectedPay",new ActionMessage("job.expectedPay.notValid"));

        return ae;
    }
}