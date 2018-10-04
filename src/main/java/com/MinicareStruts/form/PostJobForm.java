package com.MinicareStruts.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.MinicareStruts.util.Constants;
import org.apache.commons.lang3.StringUtils;
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
    private Date startTime1;
    private Date endTime1;

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

        if(StringUtils.isBlank(title))
            ae.add(Constants.TITLE,new ActionMessage("job.required","Title"));
        else if(!title.matches("^[a-zA-Z]+([a-zA-Z ]*[a-zA-Z]+)*$"))
            ae.add(Constants.TITLE,new ActionMessage("job.title.notValid"));

        if(StringUtils.isBlank(payPerHour))
            ae.add(Constants.PAY_PER_HOUR,new ActionMessage("job.required","Pay Per Hour"));
        else if(!payPerHour.matches("^0$|^[1-9]+([\\.]?[0-9]+)?$"))
            ae.add(Constants.PAY_PER_HOUR,new ActionMessage("job.payPerHour.notValid"));


        if(StringUtils.isBlank(startTime))
            ae.add(Constants.START_TIME,new ActionMessage("job.required","Start Time"));
        else {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                startTime1 = simpleDateFormat.parse(startTime);
            }
            catch(ParseException p) {
                ae.add(Constants.START_TIME,new ActionMessage("job.time.notValid","Start Time"));
            }
        }

        if(StringUtils.isBlank(endTime))
            ae.add(Constants.END_TIME,new ActionMessage("job.required","End Time"));
        else {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                endTime1 = simpleDateFormat.parse(endTime);
            }
            catch(ParseException p) {
                ae.add(Constants.END_TIME,new ActionMessage("job.time.notValid","End Time"));
            }
        }


        if(StringUtils.isBlank(startDate))
            ae.add(Constants.START_DATE,new ActionMessage("job.required","Start Date"));
        if(StringUtils.isBlank(endDate))
            ae.add(Constants.END_DATE,new ActionMessage("job.required","End Date"));


        if(startTime1!=null && endTime1!=null) {
            //Start Date
            if(StringUtils.isNotBlank(startDate)) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                    startDate1 = simpleDateFormat.parse(startDate + " " + startTime);

                    //Should be greater than Today's Date
                    Date currentDate = new Date();
                    if (startDate1.before(currentDate))
                        ae.add(Constants.START_DATE, new ActionMessage("job.startDate.shouldBeGreater"));
                } catch (ParseException e) {
                    ae.add(Constants.START_DATE, new ActionMessage("job.date.notValid"));
                }
            }

            //End Date
            if(StringUtils.isNotBlank(endDate)) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                    endDate1 = simpleDateFormat.parse(endDate + " " + endTime);

                    //Should be greater than Start Date
                    if (startDate1 != null) {
                        if (endDate1.before(startDate1)) {
                            ae.add(Constants.END_DATE, new ActionMessage("job.endDate.shouldBeGreater"));
                        }
                    }
                } catch (ParseException e) {
                    ae.add(Constants.START_DATE, new ActionMessage("job.date.notValid"));
                }
            }
        }
        return ae;
    }
}