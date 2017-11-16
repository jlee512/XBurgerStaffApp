package main.java.servlet;

import main.java.entity.Customer;
import main.java.entity.Staff;
import main.java.entity.Staff_API;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Julian on 15/11/2017.
 */
public class LoginValidation extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loginStatusRedirection(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Staff staff_login_submission = null;

        //Get request parameters from form submission
        String username_entry = request.getParameter("username");
        String password_entry = request.getParameter("pass_us");

        //Check whether user has input a username or an email and access details from the API
        if (username_entry != null && username_entry.length() > 0) {
            staff_login_submission = Staff_API.getStaffDetailsAPI(username_entry, "username");
        }

        ServletOutputStream out = response.getOutputStream();

        if (staff_login_submission != null) {
            if (Staff_API.validateStaffPassword(password_entry, staff_login_submission.getPassHash(), staff_login_submission.getSalt(), staff_login_submission.getIterations())) {

                //Login username and password verified
                //Create user session and setMaxInactiveInterval to 12 hours. Also store the user details and login status variables in the session
                HttpSession session = request.getSession(true);
                session.setMaxInactiveInterval(60 * 60 * 12);
                session.setAttribute("loginStatus", "active");
                session.setAttribute("staff", staff_login_submission);

                response.sendRedirect("/home");

            } else {
                response.addHeader("loginStatus", "incorrectPassword");
                response.addHeader("username", username_entry);
                response.sendRedirect("/login");
            }
        } else {
                response.addHeader("loginStatus", "invalidUsername");
                response.sendRedirect("/login");
        }

        out.flush();
        out.close();

    }


    /*Method to check a user's login-status and redirect accordingly
   (this method is used across most user-sensitive servlets to confirm their login status)*/
    public static void loginStatusRedirection(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("loginStatus") != "active"){
            response.sendRedirect("/");
        } else {
            Staff staff = (Staff) session.getAttribute("staff");
            response.sendRedirect("/home");
        }
    }
}
