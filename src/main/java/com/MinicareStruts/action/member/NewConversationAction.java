package com.MinicareStruts.action.member;

import com.MinicareStruts.model.Conversation;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.MemberService;
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
        Member member = (Member)request.getSession().getAttribute("member");
        MemberService memberService = new MemberService();
        String type = (member.getType().name().equalsIgnoreCase("seeker"))?"seeker":"sitter";

        List<Member> list = memberService.getNewConversation(member.getMemberId(),type);
        request.setAttribute("newConversation",list);
        request.setAttribute("type",type);

        return mapping.findForward("success");
    }
}
