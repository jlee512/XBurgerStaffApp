package main.java.servlet;

import main.java.entity.Order;
import main.java.entity.Order_API;
import main.java.entity.Stock;
import main.java.entity.Stock_API;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Julian on 15/11/2017.
 */
public class SetupHome extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Order> orders = Order_API.getOrdersByStatus(0);
        int order_count = orders.size();

        ArrayList<Stock> low_stocks = Stock_API.getStockPendingRestock();

        Collections.sort(low_stocks);


        req.setAttribute("order_count", order_count);
        req.setAttribute("low_stocks", low_stocks);
        req.getRequestDispatcher("/home_setup").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
