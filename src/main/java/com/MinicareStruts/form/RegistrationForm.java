package com.MinicareStruts.form;

import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.MemberService;
import com.MinicareStruts.util.Constants;
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
            errors.add(Constants.FIRST_NAME,new ActionMessage("member.required","First Name"));
        else if(!firstName.matches("^[a-zA-Z]+$"))
            errors.add(Constants.FIRST_NAME,new ActionMessage("member.firstName.notValid"));
    }

    public void validateLastName() {
        if(StringUtils.isBlank(lastName))
            errors.add(Constants.LAST_NAME,new ActionMessage("member.required","Last Name"));
        else if(!lastName.matches("^[a-zA-Z]+$"))
            errors.add(Constants.LAST_NAME,new ActionMessage("member.lastName.notValid"));
    }

    public void validatePhoneNumber() {
        if (StringUtils.isBlank(phoneNumber))
            errors.add(Constants.PHONE_NUMBER, new ActionMessage("member.required", "Phone Number"));
        else if (!phoneNumber.matches("^[0-9]{10}$"))
            errors.add(Constants.PHONE_NUMBER, new ActionMessage("member.phoneNumber.notValid"));
    }

    public void validateAddress() {
        if(StringUtils.isBlank(address))
            errors.add(Constants.ADDRESS,new ActionMessage("member.required","Address"));
    }

    public void validateType() {
        if(!(StringUtils.equalsIgnoreCase(type,Constants.SEEKER) || StringUtils.equalsIgnoreCase(type,Constants.SITTER)))
            errors.add(Constants.TYPE,new ActionMessage("member.type.notValid"));
    }

    public void validateMember() {
        if(StringUtils.equalsIgnoreCase(type,Constants.SEEKER))
        {
            if(StringUtils.isBlank(noOfChildren))
                errors.add(Constants.NO_OF_CHILDREN,new ActionMessage("member.required","No Of Children"));
            else if(!noOfChildren.matches("^0$|^[1-9][0-9]{0,2}$"))
                errors.add(Constants.NO_OF_CHILDREN,new ActionMessage("member.noOfChildren.notValid"));

            if(StringUtils.isBlank(spouseName))
                errors.add(Constants.SPOUSE_NAME,new ActionMessage("member.required","Spouse Name"));
            else if(!spouseName.matches("^[a-zA-Z]+([a-zA-Z ]*[a-zA-Z]+)*$"))
                errors.add(Constants.SPOUSE_NAME,new ActionMessage("member.spouseName.notValid"));
        }
        else if(StringUtils.equalsIgnoreCase(type,Constants.SITTER))
        {
            if(StringUtils.isBlank(experience))
                errors.add(Constants.EXPERIENCE,new ActionMessage("member.required","Experience"));
            else if(!experience.matches("^0$|^[1-9]+([\\.]?[0-9]+)?$"))
                errors.add(Constants.EXPERIENCE,new ActionMessage("member.experience.notValid"));

            if(StringUtils.isBlank(expectedPay))
                errors.add(Constants.EXPECTED_PAY,new ActionMessage("member.required","Expected Pay"));
            else if(!expectedPay.matches("^0$|^[1-9]+([\\.]?[0-9]+)?$"))
                errors.add(Constants.EXPECTED_PAY,new ActionMessage("member.expectedPay.notValid"));
        }
    }

    public void validateEmail() {
        if (StringUtils.isBlank(email))
            errors.add(Constants.EMAIL, new ActionMessage("member.required", "Email"));
        else if (!email.matches("^[a-zA-Z0-9]{1}([a-zA-Z0-9.-_*]*[a-zA-Z0-9]+)*@[a-zA-Z0-9]{1}([a-zA-Z0-9.-_*]*[a-zA-Z0-9]+)*$"))
            errors.add(Constants.EMAIL, new ActionMessage("member.email.notValid"));
        else {
            MemberService registrationService = new MemberService();
            if (registrationService.isEmailRegistered(email))
                errors.add(Constants.EMAIL, new ActionMessage("member.email.alreadyExists"));
        }
    }

    public void validatePassword() {
        if (StringUtils.isBlank(password))
            errors.add(Constants.PASSWORD, new ActionMessage("member.required", "Password"));
        else if (!password.matches("^[a-zA-Z0-9\\W]{6,20}$"))
            errors.add(Constants.PASSWORD, new ActionMessage("member.password.notValid"));
    }

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        //Ask we should do like this or the below way.
//        Member member = (Member)request.getSession().getAttribute(Constants.MEMBER);
//        if(member == null) {
//            validateEmail();
//            validateType();
//            validatePassword();
//        }


        validateFirstName();
        validateLastName();
        if(email!=null)
            validateEmail();
        if(password!=null)
            validatePassword();
        if(type!=null)
            validateType();
        validatePhoneNumber();
        validateAddress();
        validateMember();
        return errors;
    }
}