package com.MinicareStruts.form;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import javax.servlet.http.HttpServletRequest;

public class LoginForm extends ActionForm{
    private String email;
    private String password;

    public LoginForm() {}

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

    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors ae = new ActionErrors();

        if(StringUtils.isBlank(email))
            ae.add("email",new ActionMessage("member.required","Email"));
        else if(!email.matches("^[a-zA-Z0-9]{1}([a-zA-Z0-9.-_*]*[a-zA-Z0-9]+)*@[a-zA-Z0-9]{1}([a-zA-Z0-9.-_*]*[a-zA-Z0-9]+)*$"))
            ae.add("email",new ActionMessage("member.email.notValid"));

        if(StringUtils.isBlank(password))
            ae.add("password",new ActionMessage("member.required","Password"));

        return ae;
    }
}
