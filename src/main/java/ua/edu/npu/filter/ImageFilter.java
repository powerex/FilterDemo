package ua.edu.npu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebFilter(urlPatterns = { "*.png", "*.jpg", "*.gif", "*.svg" },
        initParams = {
        @WebInitParam(name = "notFoundImage", value = "/images/noimage.jpg") })
public class ImageFilter implements Filter {

    private String notFoundImage;

    public ImageFilter() {}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        notFoundImage = filterConfig.getInitParameter("notFoundImage");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String servletPath = req.getServletPath();

        String realRootPath = servletRequest.getServletContext().getRealPath("");
        String imageRealPath = realRootPath + servletPath;
        System.out.println("imageRealPath = " + imageRealPath);
        File file = new File(imageRealPath);
        if (file.exists()) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (!servletPath.equals(this.notFoundImage)) {
            HttpServletResponse resp = (HttpServletResponse) servletResponse;
            resp.sendRedirect(req.getContextPath() + this.notFoundImage);
        }
    }

    @Override
    public void destroy() {

    }
}
