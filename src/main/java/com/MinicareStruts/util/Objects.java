package com.MinicareStruts.util;

import com.MinicareStruts.dao.*;
import com.MinicareStruts.service.*;

import java.util.HashMap;

public class Objects {
    public static HashMap<Class,Object> map = new HashMap<>();
    static {
        map.put(MemberService.class,new MemberService());
        map.put(SeekerService.class,new SeekerService());
        map.put(SitterService.class,new SitterService());
        map.put(MemberDAO.class,new MemberDAO());
        map.put(SeekerDAO.class,new SeekerDAO());
        map.put(SitterDAO.class,new SitterDAO());
        map.put(JobDAO.class,new JobDAO());
        map.put(JobApplicationDAO.class,new JobApplicationDAO());
        map.put(ConversationDAO.class,new ConversationDAO());
    }
}
