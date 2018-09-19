package com.MinicareStruts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class PostJobForm extends ActionForm{
    private int jobId;
    private String title;
    private String payPerHour;
    private String startTime;
    private String endTime;
    private String startDate;
    private String endDate;

    //Used For Validation.
    private Date startDate1;
    private Date endDate1;

    public PostJobForm() {}

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPayPerHour() {
        return payPerHour;
    }

    public void setPayPerHour(String payPerHour) {
        this.payPerHour = payPerHour;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors ae = new ActionErrors();
        startDate1 = null;
        endDate1 = null;

        if(title.equals(""))
            ae.add("title",new ActionMessage("job.required","Title"));
        else if(!title.matches("^[a-zA-Z]+([a-zA-Z ]*[a-zA-Z]+)*$"))
            ae.add("title",new ActionMessage("job.title.notValid"));

        if(payPerHour.equals(""))
            ae.add("payPerHour",new ActionMessage("job.required","Pay Per Hour"));
        else if(!payPerHour.matches("^0$|^[1-9]+([\\.]?[0-9]+)?$"))
            ae.add("payPerHour",new ActionMessage("job.payPerHour.notValid"));


        if(startDate.equals(""))
            ae.add("startDate",new ActionMessage("job.required","Start Date"));
        else {
            try {
                startDate1 = Date.valueOf(startDate);
            } catch (IllegalArgumentException e) {
                ae.add("startDate", new ActionMessage("job.startDate.notValid"));
            }

            //Checking startDate Should be either today or after today's date.
            if(startDate1 != null) {
                java.util.Date date=new java.util.Date();
                if(!startDate1.after(date)) {
                    ae.add("startDate",new ActionMessage("job.startDate.shouldBeGreater"));
                }
            }
        }


        if(endDate.equals(""))
            ae.add("endDate",new ActionMessage("job.required","End Date"));
        else {
            try {
                endDate1 = Date.valueOf(endDate);
            } catch (IllegalArgumentException e) {
                ae.add("endDate", new ActionMessage("job.endDate.notValid"));   //Show the format in the placeholder
            }

            //If start Date is not null and also if end date is not null
            if(startDate1 != null && endDate1 != null) {
                if(!endDate1.after(startDate1)){
                    ae.add("endDate",new ActionMessage("job.endDate.shouldBeGreater"));
                }
            }
        }

        if(startTime.equals(""))
            ae.add("startTime",new ActionMessage("job.required","Start Time"));
        else {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");   //Show the format in the placeholder
                java.util.Date d1 = simpleDateFormat.parse(startTime);
            }
            catch(ParseException p) {
                ae.add("startTime",new ActionMessage("job.startTime.notValid"));
            }
        }

        if(endTime.equals(""))
            ae.add("endTime",new ActionMessage("job.required","End Time"));
        else {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");   //Show the format in the placeholder
                java.util.Date d2 = simpleDateFormat.parse(endTime);
            }
            catch(ParseException p) {
                ae.add("endTime",new ActionMessage("job.endTime.notValid"));
            }
        }
        return ae;
    }
}