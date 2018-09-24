package com.MinicareStruts.filter;

import com.MinicareStruts.model.Member;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute("member");
        if(member != null) {
            String type = member.getType().name().toLowerCase();
            //Redirect this.
            filterConfig.getServletContext().getRequestDispatcher("/jsp/"+ type +"/HomePage"+ type +".jsp").forward(request,response);
        }
        else {
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {}
}
