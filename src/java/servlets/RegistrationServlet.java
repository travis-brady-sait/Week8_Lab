/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import services.UserService;

/**
 *
 * @author awarsyle
 */
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null && action.equals("checkUsername")) {
            String username = request.getParameter("username");

            UserService us = new UserService();
            User user;
            try {
                user = us.get(username);

                // handle the reseponse by hardcoding values
                // the response is not a whole page, just some text
                if (user == null) {
                    response.getWriter().write("Hey that's cool");
                } else {
                    response.getWriter().write("Hey that's not cool");
                }
            } catch (Exception ex) {
                Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            return; // stop execution of servlet
        }

        getServletContext().getRequestDispatcher("/WEB-INF/registrations.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
