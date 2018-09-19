package com.MinicareStruts.action.member;

import com.MinicareStruts.model.Conversation;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.MemberService;
import com.MinicareStruts.service.SeekerService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StoreMessageAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");

        String content = request.getParameter("message");

        int conversationId = Integer.parseInt(request.getParameter("conversationId"));

        MemberService memberService = new MemberService();
        Conversation conversation = memberService.getConversationById(conversationId);

        SeekerService seekerService = new SeekerService();
        seekerService.storeMessage(content,conversation,member);

        request.setAttribute("conversationId", conversationId);
        return mapping.findForward("getMessages");
    }
}