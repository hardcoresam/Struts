package com.MinicareStruts.action.member;

import com.MinicareStruts.model.Member;
import com.MinicareStruts.model.Seeker;
import com.MinicareStruts.model.Sitter;
import com.MinicareStruts.service.MemberService;
import com.MinicareStruts.service.SeekerService;
import com.MinicareStruts.service.SitterService;
import com.MinicareStruts.util.Constants;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendMessageAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = (Member) request.getSession().getAttribute(Constants.MEMBER);

        int seekerId,sitterId;
        if(member.isSeeker()) {
            seekerId = member.getMemberId();
            sitterId = Integer.parseInt(request.getParameter(Constants.MEMBER_ID));

            //Check this and ask About this.
            SitterService sitterService = new SitterService();
            Sitter sitter = sitterService.fetchMember(sitterId);
            //Ask if i should Check for Null here like sitter != null or not. Otherwise we would get an NullPointerException.
//            if(sitter == null)
//                throw new Exception();
//            else if(sitter.getStatus() == Member.Status.INACTIVE)
//                throw new Exception();
            //Ask and do the same thing below also.
            if(sitter.getStatus() == Member.Status.INACTIVE)
                throw new Exception();


        }
        else {
            sitterId = member.getMemberId();
            seekerId = Integer.parseInt(request.getParameter(Constants.MEMBER_ID));

            //Check this and ask About this.
            SeekerService seekerService = new SeekerService();
            Seeker seeker = seekerService.fetchMember(seekerId);
            if(seeker.getStatus() == Member.Status.INACTIVE)
                throw new Exception();

        }

        MemberService memberService = new MemberService();
        int conversationId = memberService.getConversationId(seekerId, sitterId);

        request.setAttribute(Constants.CONVERSATION_ID,conversationId);
        return mapping.findForward("getMessages");
    }
}