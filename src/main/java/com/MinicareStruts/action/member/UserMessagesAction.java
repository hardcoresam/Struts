package com.MinicareStruts.action.member;

import com.MinicareStruts.model.Conversation;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.MemberService;
import com.MinicareStruts.util.Constants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserMessagesAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = (Member)request.getSession().getAttribute(Constants.MEMBER);
        MemberService memberService = new MemberService();
        List<Conversation> list = memberService.getUserNames(member.getMemberId(),member.getType().name().toLowerCase());
        request.setAttribute(Constants.TYPE,member.getType().name().toLowerCase());
        request.setAttribute("listOfUserNames",list);
        return mapping.findForward(Constants.SUCCESS);
    }
}
