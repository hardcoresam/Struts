package com.MinicareStruts.action.visitor;

import com.MinicareStruts.form.LoginForm;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.MemberService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String type;
        LoginForm loginForm = (LoginForm)form;

        MemberService memberService = new MemberService();
        Member member = memberService.fetchMember(loginForm.getEmail(), loginForm.getPassword());

        //So check if user is already logged in here also.
        //bcoz lets say the user opened a Login.jsp in two tabs and in one tab log in
        //now in the other tab we can still log in which should not be allowed.
        //So ask and check here also.

        if(member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("member",member);

            type=(member.getType().toString().equalsIgnoreCase("seeker"))?"seeker":"sitter";
            return mapping.findForward(type);
        }
        else {
            request.setAttribute("loginError","Invalid Credentials - Please Try Again.");
            return mapping.findForward("error");
        }
    }
}