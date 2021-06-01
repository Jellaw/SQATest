package vn.compedia.website.jsf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter{

//    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationFilter.class);
//    private FilterConfig config = null;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        this.config = filterConfig;
//        LOGGER.info("Initializing Authorization Filter");
//    }
//
////    @Override
////    public void doFilter(ServletRequest request, ServletResponse response,
////                         FilterChain chain) throws IOException, ServletException {
////        HttpServletRequest req = (HttpServletRequest) request;
////        LOGGER.info("RequestURI: " + req.getRequestURI());
////        HttpSession session = ((HttpServletRequest) request).getSession();
////        AuthorizationController auth = (session != null) ? (AuthorizationController) session
////                .getAttribute("auth") : null;
////        boolean allow = true;
////
//////		if (auth != null && auth.isLoggedIn()) {
//////			allow = true;
//////		} else {
//////			allow = false;
//////		}
////
////        if (allow) {
////            chain.doFilter(request, response);
////        } else {
////            HttpServletResponse res = (HttpServletResponse) response;
////            res.sendRedirect(req.getContextPath() + "/login.xhtml");
////            LOGGER.info("redirect to login page");
////        }
////    }
//
//    @Override
//    public void destroy() {
//        LOGGER.info("Destroy Authorization filter");
//    }
}
