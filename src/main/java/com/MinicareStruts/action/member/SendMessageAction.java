package com.MinicareStruts.action.member;

import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.MemberService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SendMessageAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");

        int seekerId,sitterId;
        if(member.getType().toString().equalsIgnoreCase("seeker")) {
            seekerId = member.getMemberId();
            sitterId = Integer.parseInt(request.getParameter("sitterId"));
        }
        else {
            sitterId = member.getMemberId();
            seekerId = Integer.parseInt(request.getParameter("seekerId"));
        }

        MemberService memberService = new MemberService();
        int conversationId = memberService.getConversationId(seekerId, sitterId);

        request.setAttribute("conversationId",conversationId);
        return mapping.findForward("getMessages");
    }
}