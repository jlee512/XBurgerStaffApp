package main.java.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Julian on 13/11/2017.
 */
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*If the user is logged-in, log them out by removing all current session attributes and invalidating the session*/
        HttpSession session = request.getSession(true);
        if (session.getAttribute("loginStatus") != null) {
            session.removeAttribute("loginStatus");
            session.removeAttribute("staff");
        /*Invalidate session and redirect to login page*/
            session.invalidate();
            response.sendRedirect("/");
        } else {
        /*Else redirect to login page and print message saying that the user is no longer logged in*/
            session.invalidate();
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*If the user is logged-in, log them out by removing all current session attributes and invalidating the session*/
        HttpSession session = request.getSession(true);
        if (session.getAttribute("loginStatus") != null) {
            session.removeAttribute("loginStatus");
            session.removeAttribute("staff");
        /*Invalidate session and redirect to login page*/
            session.invalidate();
            response.sendRedirect("/");
        } else {
        /*Else redirect to login page and print message saying that the user is no longer logged in*/
            session.invalidate();
            response.sendRedirect("/");
        }
    }
}
