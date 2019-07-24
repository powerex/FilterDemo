package ua.edu.npu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

public class LogFilter implements Filter {

    public LogFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("LogFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String servletPath = req.getServletPath();
        System.out.println("#INFO " + new Date() + " - ServletPath :" + servletPath //
                + ", URL = " + req.getRequestURL());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("LogFilter destroy");
    }
}
