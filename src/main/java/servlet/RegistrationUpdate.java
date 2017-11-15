package main.java.servlet;

import main.java.entity.Customer;
import main.java.entity.CustomerAPI;
import main.java.entity.Staff;
import main.java.entity.Staff_API;
import main.java.password.Passwords;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Julian on 5/11/2017.
 */

public class RegistrationUpdate extends HttpServlet {

    // If this servlet is accessed via HTTP GET: return the user to the login page (.jsp)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loginStatusRedirection(request, response);
    }

    // This servlet should only be accessed via post. If posted, access the customers details from the API and validate credentials and return response depending on validation process
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Customer customer_login_submission = null;

        //Get request parameters from form submission
        String username_entry = request.getParameter("username");
        String password_entry = request.getParameter("pass_us");
        //String password_confirm = request.getParameter("password");

        byte[] saltArray = Passwords.getNextSalt(16);
        int iterArray = Passwords.getNextNumIterations();

        byte[] passwordArray = Passwords.hash(password_entry.toCharArray(),saltArray, iterArray);

        String salt = Passwords.base64Encode(saltArray);
        String passwordHash = Passwords.base64Encode(passwordArray);

        Staff newStaff = new Staff("Floor Staff", username_entry, iterArray, salt, passwordHash);

        Staff_API.addStafftoDBAPI(newStaff);

        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(60 * 60 * 12);
        session.setAttribute("loginStatus", "active");
        session.setAttribute("staff", newStaff);

        response.sendRedirect("/home");
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
