package com.MinicareStruts.form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class AlterProfileForm extends ActionForm{
    private int memberId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String noOfChildren;
    private String spouseName;
    private String experience;
    private String expectedPay;
    private String type;

    public AlterProfileForm() {}

    //Delete this after changing the entire code
    public AlterProfileForm(int memberId, String firstName, String lastName, String phoneNumber, String address, String noOfChildren,
                            String spouseName, String experience, String expectedPay, String type) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.noOfChildren = noOfChildren;
        this.spouseName = spouseName;
        this.experience = experience;
        this.expectedPay = expectedPay;
        this.type = type;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(String noOfChildren) {
        this.noOfChildren = noOfChildren;
    }

    public String getSpouseName() {
        return spouseName;
    }

    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getExpectedPay() {
        return expectedPay;
    }

    public void setExpectedPay(String expectedPay) {
        this.expectedPay = expectedPay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors ae = new ActionErrors();

        //Ask if i should keep each validation in a seperate method?
        //So keep the validation in seperate methods and in a base class
        //and use the base class everywhere.

        //FIRSTNAME
        if(firstName.equals(""))
            ae.add("firstName",new ActionMessage("member.required","First Name"));
        else if(!firstName.matches("^[a-zA-Z]+$"))
            ae.add("firstName",new ActionMessage("member.firstName.notValid"));

        //LASTNAME
        if(!lastName.matches("^[a-zA-Z]*$")) {
            ae.add("lastName",new ActionMessage("member.required","Last Name"));
        }

        //PHONE NUMBER
        if(phoneNumber.equals(""))
            ae.add("phoneNumber",new ActionMessage("member.required","Phone Number"));
        else if(!phoneNumber.matches("^[0-9]{10}$"))
            ae.add("phoneNumber",new ActionMessage("member.phoneNumber.notValid"));

        //ADDRESS
        if(address.equals(""))
            ae.add("address",new ActionMessage("member.required","Address"));

        if(type.equalsIgnoreCase("Seeker"))
        {
            //No OF CHILDREN
            if(noOfChildren.equals(""))
                ae.add("noOfChildren",new ActionMessage("member.required","No Of Children"));
            else if(!noOfChildren.matches("^0$|^[1-9][0-9]{0,3}$"))
                ae.add("noOfChildren",new ActionMessage("member.noOfChildren.notValid"));

            //SPOUSE NAME
            if(spouseName.equals(""))
                ae.add("spouseName",new ActionMessage("member.required","Spouse Name"));
            else if(!spouseName.matches("^[a-zA-Z]+([a-zA-Z ]*[a-zA-Z]+)*$"))
                ae.add("spouseName",new ActionMessage("member.spouseName.notValid"));
        }
        else
        {
            //EXPERIENCE
            if(experience.equals(""))
                ae.add("experience",new ActionMessage("member.required","Experience"));
            else if(!experience.matches("^0$|^[1-9]+([\\.]?[0-9]+)?$"))
                ae.add("experience",new ActionMessage("member.experience.notValid"));

            //EXPECTED PAY
            if(expectedPay.equals(""))
                ae.add("expectedPay",new ActionMessage("member.required","Expected Pay"));
            else if(!expectedPay.matches("^0$|^[1-9]+([\\.]?[0-9]+)?$"))
                ae.add("expectedPay",new ActionMessage("member.expectedPay.notValid"));
        }
        return ae;
    }
}