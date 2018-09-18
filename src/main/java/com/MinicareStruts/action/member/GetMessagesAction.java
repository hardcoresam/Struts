package com.MinicareStruts.action.member;

import com.MinicareStruts.model.Message;
import com.MinicareStruts.service.SeekerService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetMessagesAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int conversationId = (Integer)request.getAttribute("conversationId");

        System.out.println("Conversation Id check ---------------");
        System.out.println(conversationId);

        SeekerService seekerService = new SeekerService();
        List<Message> list = seekerService.getMessages(conversationId);

        if(list.isEmpty()) {
            request.setAttribute("conversationId",conversationId);
        }

        request.setAttribute("listOfMessages",list);
        return mapping.findForward("listOfMessages");
    }
}
