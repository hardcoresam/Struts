package com.MinicareStruts.form;

import com.MinicareStruts.service.MemberService;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class RegistrationForm extends ActionForm {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private String address;
    private String noOfChildren;
    private String spouseName;
    private String experience;
    private String expectedPay;
    private String type;

    public RegistrationForm() {}

    //Delete this after changing the entire code
    public RegistrationForm(String firstName, String lastName, String phoneNumber, String email, String password,
                            String address, String noOfChildren, String spouseName, String experience,
                            String expectedPay, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.noOfChildren = noOfChildren;
        this.spouseName = spouseName;
        this.experience = experience;
        this.expectedPay = expectedPay;
        this.type = type;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        type = request.getParameter("type");

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

        //EMAIL
        if(email.equals(""))
            ae.add("email",new ActionMessage("member.required","Email"));
        else if(!email.matches("^[a-zA-Z0-9]{1}([a-zA-Z0-9.-_*]*[a-zA-Z0-9]+)*@[a-zA-Z0-9]{1}([a-zA-Z0-9.-_*]*[a-zA-Z0-9]+)*$"))
            ae.add("email",new ActionMessage("member.email.notValid"));
        else {
            //Check this.
            MemberService registrationService = new MemberService();
            if(registrationService.isEmailRegistered(email))
                ae.add("email", new ActionMessage("member.email.alreadyExists"));
        }

        //PHONE NUMBER
        if(phoneNumber.equals(""))
            ae.add("phoneNumber",new ActionMessage("member.required","Phone Number"));
        else if(!phoneNumber.matches("^[0-9]{10}$"))
            ae.add("phoneNumber",new ActionMessage("member.phoneNumber.notValid"));

        //ADDRESS
        if(address.equals(""))
            ae.add("address",new ActionMessage("member.required","Address"));

        //PASSWORD
        if(password.equals(""))
            ae.add("password",new ActionMessage("member.required","Password"));
        else if(!password.matches("^[a-zA-Z0-9\\W]{6,20}$"))
            ae.add("password",new ActionMessage("member.password.notValid"));

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
