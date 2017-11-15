package main.java.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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

public class ProcessStats extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("test");

        ArrayList<Stock> allStock = Stock_API.getAllStockDetails();
        ArrayList<Order> monthOrder = Order_API.getLastMonthOfOrders();

        req.setAttribute("all_stock", allStock);
        req.setAttribute("monthOrder", monthOrder);

        req.getRequestDispatcher("/statistics_page").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }


}
