package ua.edu.npu.filter;

import ua.edu.npu.util.ConnectionUtils;
import ua.edu.npu.util.DBUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;

@WebFilter(urlPatterns = { "/*" })
public class JdbcFilter implements Filter {

    public JdbcFilter() {
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String servletPath = req.getServletPath();
        if (servletPath.contains("/specialPath1") || servletPath.contains("/specialPath2")) {
            Connection connection = null;
            try {
                connection = ConnectionUtils.getConnection();
                connection.setAutoCommit(false);
                DBUtil.storeConnection(servletRequest, connection);
                filterChain.doFilter(servletRequest, servletResponse);
                connection.commit();
            } catch (Exception e) {
                ConnectionUtils.rollbackQuietly(connection);
                throw new ServletException();
            } finally {
                ConnectionUtils.closeQuietly(connection);
            }
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
