package com.MinicareStruts.action.member;

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

public class SearchAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = (Member) request.getSession().getAttribute(Constants.MEMBER);
        MemberService memberService = new MemberService();
        List<Member> memberList = memberService.searchMembers(member.getType().name());
        request.setAttribute("listOfMembers",memberList);
        return mapping.findForward(Constants.SUCCESS);
    }
}
