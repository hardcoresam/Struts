package com.MinicareStruts.form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class ApplyJobForm extends ActionForm{
    private String expectedPay;
    private String jobId;
    //In the previous project i wrote this, But now i am removing it. Check if this produces any issues. I also removed the code
    //inside contructor and also getters and setters for this particular field i.e sitterId.
    //private int sitterId;

    public ApplyJobForm() {}

    //Delete This after Changing Code
    public ApplyJobForm(String expectedPay, String jobId) {
        this.expectedPay = expectedPay;
        this.jobId = jobId;
    }

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