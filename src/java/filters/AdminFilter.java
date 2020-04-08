/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.UserService;

/**
 *
 * @author 798794
 */
public class AdminFilter implements Filter {

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest r = (HttpServletRequest)request;
        HttpSession session = r.getSession();
        UserService us = new UserService();
        User user = null;
        try {
            user = us.get((String) session.getAttribute("username"));
        } catch (Exception ex) {
            Logger.getLogger(AdminFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
        Role role = user.getRole();
        int roleInt = role.getRoleid();
        
        if (roleInt == 1) {
                // if they are authenticated, i.e. have a username in the session,
                // then allow them to continue on to the servlet
                chain.doFilter(request, response);
            } else {
                // they do not have a username in the sesion
                // so, send them to login page
                HttpServletResponse resp = (HttpServletResponse)response;
                resp.sendRedirect("home");
            }
    }

    

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        
    }

}

