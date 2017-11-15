package main.java.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import main.java.entity.Stock;
import main.java.entity.Stock_API;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class StockTable extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Stock> allStock = Stock_API.getAllStockDetails();


        JsonArray stockCounts = new JsonArray();

        for (int i = 0; i < allStock.size(); i++) {
            Stock stock = allStock.get(i);
            JsonObject stockObject = new JsonObject();

            stockObject.addProperty(stock.getIngredient_name(),"" + stock.getStock_level());
            stockCounts.add(stockObject);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(stockCounts.toString());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }


}
