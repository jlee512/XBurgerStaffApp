package main.java.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import main.java.entity.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ProcessStats extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Stock> allStock = Stock_API.getAllStockDetails();
        ArrayList<Integer> item_counts = new ArrayList<>();
        ArrayList<String> item_names = new ArrayList<>();
        item_names.add("Burger");
        item_names.add("Side");
        item_names.add("Drink");
        item_names.add("Special");
        item_counts.add(0);
        item_counts.add(0);
        item_counts.add(0);
        item_counts.add(0);

        for (int i = 0; i < allStock.size(); i++) {
            allStock.get(i).setupUsed_this_month();
        }

        ArrayList<Order> monthOrder = Order_API.getLastMonthOfOrders();

        for (int i = 0; i < monthOrder.size(); i++) {

            Order order = monthOrder.get(i);
            ArrayList<Item> items = order.getItems();

            for (int j = 0; j < items.size(); j++) {

                Item item = items.get(j);

                if (item.getItem_type().equals("burger")) {
                    item_counts.set(0, item_counts.get(0) + 1);
                } else if (item.getItem_type().equals("side")) {
                    item_counts.set(1, item_counts.get(1) + 1);
                } else if (item.getItem_type().equals("drink")) {
                    item_counts.set(2, item_counts.get(2) + 1);
                } else if (item.getItem_type().equals("special")) {
                    item_counts.set(3, item_counts.get(3) + 1);
                }

                ArrayList<Stock> stocks = item.getIngredients();

                for (int k = 0; k < stocks.size(); k++) {

                    Stock ingredient = stocks.get(k);

                    for (int l = 0; l < allStock.size(); l++) {

                        if(allStock.get(l).getIngredient_id() == ingredient.getIngredient_id()) {
                            allStock.get(l).incrementUsed_this_month();
                        }
                    }

                }

            }

        }

        req.setAttribute("all_stock", allStock);
        req.setAttribute("monthOrder", monthOrder);
        req.setAttribute("item_names", item_names);
        req.setAttribute("item_counts", item_counts);
        req.getRequestDispatcher("/statistics_page").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }


}
