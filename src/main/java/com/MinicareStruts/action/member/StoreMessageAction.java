package com.MinicareStruts.action.member;

import com.MinicareStruts.model.Conversation;
import com.MinicareStruts.model.Member;
import com.MinicareStruts.service.MemberService;
import com.MinicareStruts.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StoreMessageAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = (Member) request.getSession().getAttribute(Constants.MEMBER);
        String content = request.getParameter("message");
        int conversationId = Integer.parseInt(request.getParameter(Constants.CONVERSATION_ID));
        MemberService memberService = new MemberService();

        if(memberService.checkForValidConversationId(conversationId, member.getMemberId(), member.getType().name())) {
            Conversation conversation = memberService.getConversationById(conversationId);
            memberService.storeMessage(content,conversation,member);

            String path = mapping.findForward("getMessages").getPath();
            path = StringUtils.replace(path,"*",String.valueOf(conversationId));
            return new ActionForward(path,true);
        }
        return mapping.findForward(Constants.FAILURE);
    }
}