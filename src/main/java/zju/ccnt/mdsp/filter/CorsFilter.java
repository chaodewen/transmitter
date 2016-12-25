package zju.ccnt.mdsp.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Cc on 2016/12/21.
 */

public class CorsFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("---> CorsFilter.init() : Starting CorsFilter ...");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse
            , FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        System.out.println("<--- CorsFilter.init() : Closing CorsFilter ...");
    }
}