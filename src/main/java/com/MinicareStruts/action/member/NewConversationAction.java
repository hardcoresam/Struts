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

public class NewConversationAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = (Member)request.getSession().getAttribute(Constants.MEMBER);
        MemberService memberService = new MemberService();
        String type = (member.isSeeker())?Constants.SEEKER:Constants.SITTER;

        List<Member> list = memberService.getNewConversation(member.getMemberId(),type);
        request.setAttribute("newConversation",list);
        request.setAttribute(Constants.TYPE,type);

        return mapping.findForward(Constants.SUCCESS);
    }
}
