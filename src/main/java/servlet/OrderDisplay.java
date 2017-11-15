package main.java.servlet;

import main.java.entity.Customer;
import main.java.entity.Order;
import main.java.entity.Order_API;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class OrderDisplay extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        //get order details for the customer
        ArrayList<Order> list_of_orders = Order_API.getAllOrders();
        if (list_of_orders != null) {
            ArrayList<Order> fwd_pending_list = Order_API.getOrdersByStatus(0);
            ArrayList<Order> pending_list = new ArrayList<Order>();

            if(fwd_pending_list != null) {
                for (int i = 0; i < fwd_pending_list.size(); i++) {
                    pending_list.add(pending_list.size() - i, fwd_pending_list.get(i));
                }
            }
            
            ArrayList<Order> fwd_making_list = Order_API.getOrdersByStatus(1);
            ArrayList<Order> making_list = new ArrayList<Order>();

            if (fwd_making_list != null) {
                for (int i = 0; i < fwd_making_list.size(); i++) {
                    making_list.add(making_list.size() - i, fwd_making_list.get(i));
                }
            }
            
            ArrayList<Order> fwd_completed_list = Order_API.getOrdersByStatus(2);
            ArrayList<Order> completed_list = new ArrayList<Order>();

            if (fwd_completed_list != null) {
                for (int i = 0; i < fwd_completed_list.size(); i++) {
                    completed_list.add(completed_list.size() - i, fwd_completed_list.get(i));
                }
            }
            
            ArrayList<Order> fwd_insufficient_list = Order_API.getOrdersByStatus(3);
            ArrayList<Order> insufficient_list = new ArrayList<Order>();

            if (fwd_insufficient_list != null) {
                for (int i = 0; i < fwd_insufficient_list.size(); i++) {
                    insufficient_list.add(insufficient_list.size() - i, fwd_insufficient_list.get(i));
                }
            }

            req.setAttribute("pending_list", pending_list);
            req.setAttribute("making_list", making_list);
            req.setAttribute("completed_list", completed_list);
            req.setAttribute("insufficient_list", insufficient_list);

            req.getRequestDispatcher("/process_order").forward(req, resp);
        } else {
            req.setAttribute("no_orders", true);
            req.getRequestDispatcher("/process_order").forward(req, resp);
        }

    }
}