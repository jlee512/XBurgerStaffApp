package main.java.servlet;

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
public class RestockServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Stock> stock_list = Stock_API.getAllStockDetails();
        Collections.sort(stock_list);

        req.setAttribute("stock_list", stock_list);
        req.getRequestDispatcher("/restock_page").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/restock");
    }
}
