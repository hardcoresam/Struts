package com.MinicareStruts.action.member;

import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.MemberService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendMessageAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = (Member) request.getSession().getAttribute("member");

        int seekerId,sitterId;
        if(member.getType().name().equalsIgnoreCase("seeker")) {
            seekerId = member.getMemberId();
            sitterId = Integer.parseInt(request.getParameter("memberId"));
        }
        else {
            sitterId = member.getMemberId();
            seekerId = Integer.parseInt(request.getParameter("memberId"));
        }

        MemberService memberService = new MemberService();
        int conversationId = memberService.getConversationId(seekerId, sitterId);

        request.setAttribute("conversationId",conversationId);
        return mapping.findForward("getMessages");
    }
}