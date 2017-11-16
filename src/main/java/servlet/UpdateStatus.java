package main.java.servlet;

import com.googlecode.objectify.impl.Session;
import main.java.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class UpdateStatus extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        Staff staff = (Staff)session.getAttribute("staff");
        int staff_id = staff.getStaff_id();

        String order_id = req.getParameter("order_id");
//        System.out.println(order_id);
        int order_id_integer = Integer.parseInt(order_id);
        Order thisOrder = Order_API.getOrderDetailsByOrderIDCustomerAPI(order_id_integer);
        System.out.println("Method called update status");

        String status = thisOrder.getStatus();

        if(status.equals("Pending")) {
            Order_API.assignStaffToOrder(staff_id, order_id_integer);
        }
        else if(status.equals("Making")) {
            Order_API.completeOrder(order_id_integer);
        }
        else if(status.equals("Insufficient Ingredients")) {
            ArrayList<Item> itemList = thisOrder.getItems();
            boolean available = true;

            for (int i = 0; i < itemList.size(); i++) {
                ArrayList<Stock> ingredientsList = itemList.get(i).getIngredients();

                for (int j = 0; j < ingredientsList.size(); j++) {
                    if(ingredientsList.get(i).getStock_level() < 5) {
                        available = false;
                    }
                }
            }

            if (available == true) {
                Order_API.assignStaffToOrder(staff_id, order_id_integer);
            }
        }

        resp.sendRedirect("/order");
    }

}
