package com.MinicareStruts.form;

import com.MinicareStruts.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class RegistrationForm extends ActionForm {
    private ActionErrors errors = new ActionErrors();

    private int memberId;
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

    public int getMemberId() { return memberId; }

    public void setMemberId(int memberId) { this.memberId = memberId; }

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

    public void validateFirstName() {
        if(StringUtils.isBlank(firstName))
            errors.add("firstName",new ActionMessage("member.required","First Name"));
        else if(!firstName.matches("^[a-zA-Z]+$"))
            errors.add("firstName",new ActionMessage("member.firstName.notValid"));
    }

    public void validateLastName() {
        if(StringUtils.isBlank(lastName))
            errors.add("lastName",new ActionMessage("member.required","Last Name"));
        else if(!lastName.matches("^[a-zA-Z]+$"))
            errors.add("lastName",new ActionMessage("member.lastName.notValid"));
    }

    public void validatePhoneNumber() {
        if (StringUtils.isBlank(phoneNumber))
            errors.add("phoneNumber", new ActionMessage("member.required", "Phone Number"));
        else if (!phoneNumber.matches("^[0-9]{10}$"))
            errors.add("phoneNumber", new ActionMessage("member.phoneNumber.notValid"));
    }

    public void validateAddress() {
        if(StringUtils.isBlank(address))
            errors.add("address",new ActionMessage("member.required","Address"));
    }

    public void validateType() {
        if(!(StringUtils.equalsIgnoreCase(type,"seeker") || StringUtils.equalsIgnoreCase(type,"sitter")))
            errors.add("type",new ActionMessage("member.type.notValid"));
    }

    public void validateMember() {
        if(StringUtils.equalsIgnoreCase(type,"seeker"))
        {
            if(StringUtils.isBlank(noOfChildren))
                errors.add("noOfChildren",new ActionMessage("member.required","No Of Children"));
            else if(!noOfChildren.matches("^0$|^[1-9][0-9]{0,2}$"))
                errors.add("noOfChildren",new ActionMessage("member.noOfChildren.notValid"));

            if(StringUtils.isBlank(spouseName))
                errors.add("spouseName",new ActionMessage("member.required","Spouse Name"));
            else if(!spouseName.matches("^[a-zA-Z]+([a-zA-Z ]*[a-zA-Z]+)*$"))
                errors.add("spouseName",new ActionMessage("member.spouseName.notValid"));
        }
        else if(StringUtils.equalsIgnoreCase(type,"sitter"))
        {
            if(StringUtils.isBlank(experience))
                errors.add("experience",new ActionMessage("member.required","Experience"));
            else if(!experience.matches("^0$|^[1-9]+([\\.]?[0-9]+)?$"))
                errors.add("experience",new ActionMessage("member.experience.notValid"));

            if(StringUtils.isBlank(expectedPay))
                errors.add("expectedPay",new ActionMessage("member.required","Expected Pay"));
            else if(!expectedPay.matches("^0$|^[1-9]+([\\.]?[0-9]+)?$"))
                errors.add("expectedPay",new ActionMessage("member.expectedPay.notValid"));
        }
    }

    public void validateEmail() {
        if (StringUtils.isBlank(email))
            errors.add("email", new ActionMessage("member.required", "Email"));
        else if (!email.matches("^[a-zA-Z0-9]{1}([a-zA-Z0-9.-_*]*[a-zA-Z0-9]+)*@[a-zA-Z0-9]{1}([a-zA-Z0-9.-_*]*[a-zA-Z0-9]+)*$"))
            errors.add("email", new ActionMessage("member.email.notValid"));
        else {
            MemberService registrationService = new MemberService();
            if (registrationService.isEmailRegistered(email))
                errors.add("email", new ActionMessage("member.email.alreadyExists"));
        }
    }

    public void validatePassword() {
        if (StringUtils.isBlank(password))
            errors.add("password", new ActionMessage("member.required", "Password"));
        else if (!password.matches("^[a-zA-Z0-9\\W]{6,20}$"))
            errors.add("password", new ActionMessage("member.password.notValid"));
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        validateFirstName();
        validateLastName();
        if(email!=null)
            validateEmail();
        if(password!=null)
            validatePassword();
        validatePhoneNumber();
        validateAddress();
        validateType();
        validateMember();
        return errors;
    }
}