package main.java.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.java.helpers.Stock_Information;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Julian on 8/11/2017.
 */
public class Stock_API {

    static final String api_base_url = "http://project2-burgerx-database-api.herokuapp.com/ingredients/";
    static final int STANDARD_RESTOCK_AMOUNT= 5;

    Stock_API() {
    }

    //Get all stock
    public static ArrayList<Stock> getAllStockDetails() {

        String api_url = api_base_url + "all";

        ArrayList<Stock> allStock = new ArrayList<>();

        //Request the json resource at the specified url
        try {

            URL url = new URL(api_url);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            //Convert JSON object to access data
            JsonParser jp = new JsonParser(); //json parser from gson library
            JsonArray stock_array = jp.parse(new InputStreamReader((InputStream) request.getContent())).getAsJsonArray();

            for (int i = 0; i < stock_array.size(); i++) {
                JsonObject stock_object = stock_array.get(i).getAsJsonObject();
                int category_id = Integer.parseInt(stock_object.get("Category_ID").getAsString());
                //Currently includes nulls
                String img_file_path = stock_object.get("Img_File_Name").getAsString();
                String ingredient_name = stock_object.get("Ingredient_Name").getAsString();
                double price = stock_object.get("Price").getAsDouble();
                int stock_id = Integer.parseInt(stock_object.get("Stock_ID").getAsString());
                String ingredient_category = Stock_Information.getItemCategory(stock_id);
                int stock_level = Integer.parseInt(stock_object.get("Stock_Level").getAsString());

                Stock newStock = new Stock(stock_id, ingredient_name, ingredient_category, stock_level, price, img_file_path);

                allStock.add(newStock);

            }

            return allStock;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Get all available stock
    public static ArrayList<Stock> getAvailableStockDetails() {

        String api_url = api_base_url + "available";

        ArrayList<Stock> allStock = new ArrayList<>();

        //Request the json resource at the specified url
        try {

            URL url = new URL(api_url);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            //Convert JSON object to access data
            JsonParser jp = new JsonParser(); //json parser from gson library
            JsonArray stock_array = jp.parse(new InputStreamReader((InputStream) request.getContent())).getAsJsonArray();

            for (int i = 0; i < stock_array.size(); i++) {
                JsonObject stock_object = stock_array.get(i).getAsJsonObject();
                int category_id = Integer.parseInt(stock_object.get("Category_ID").getAsString());
                //Currently includes nulls
                String img_file_path = stock_object.get("Img_File_Name").getAsString();
                String ingredient_name = stock_object.get("Ingredient_Name").getAsString();
                double price = stock_object.get("Price").getAsDouble();
                int stock_id = Integer.parseInt(stock_object.get("Stock_ID").getAsString());
                String ingredient_category = Stock_Information.getItemCategory(stock_id);
                int stock_level = Integer.parseInt(stock_object.get("Stock_Level").getAsString());

                Stock newStock = new Stock(stock_id, ingredient_name, ingredient_category, stock_level, price, img_file_path);

                allStock.add(newStock);

            }

            return allStock;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Get stock by stock_id
    public static Stock getStockDetailsByID(int stock_id) {

        String api_url = api_base_url + stock_id;

        //Request the json resource at the specified url
        try {

            URL url = new URL(api_url);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            //Convert JSON object to access data
            JsonParser jp = new JsonParser(); //json parser from gson library
            JsonArray stock_array = jp.parse(new InputStreamReader((InputStream) request.getContent())).getAsJsonArray();

                JsonObject stock_object = stock_array.get(0).getAsJsonObject();
                int category_id = Integer.parseInt(stock_object.get("Category_ID").getAsString());
                //Currently includes nulls
                String img_file_path = stock_object.get("Img_File_Name").getAsString();
                String ingredient_name = stock_object.get("Ingredient_Name").getAsString();
                double price = stock_object.get("Price").getAsDouble();
                int stock_id_result = Integer.parseInt(stock_object.get("Stock_ID").getAsString());
                String ingredient_category = Stock_Information.getItemCategory(stock_id_result);
                int stock_level = Integer.parseInt(stock_object.get("Stock_Level").getAsString());

                Stock newStock = new Stock(stock_id_result, ingredient_name, ingredient_category, stock_level, price, img_file_path);

            return newStock;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    //Get stock by stock_name
    public static Stock getStockDetailsByName(String stock_name) {

        String api_url = api_base_url + stock_name;

        //Request the json resource at the specified url
        try {

            URL url = new URL(api_url);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            //Convert JSON object to access data
            JsonParser jp = new JsonParser(); //json parser from gson library
            JsonArray stock_array = jp.parse(new InputStreamReader((InputStream) request.getContent())).getAsJsonArray();

            JsonObject stock_object = stock_array.get(0).getAsJsonObject();
            int category_id = Integer.parseInt(stock_object.get("Category_ID").getAsString());
            //Currently includes nulls
                String img_file_path = stock_object.get("Img_File_Name").getAsString();
            String ingredient_name = stock_object.get("Ingredient_Name").getAsString();
            double price = stock_object.get("Price").getAsDouble();
            int stock_id_result = Integer.parseInt(stock_object.get("Stock_ID").getAsString());
            String ingredient_category = Stock_Information.getItemCategory(stock_id_result);
            int stock_level = Integer.parseInt(stock_object.get("Stock_Level").getAsString());

            Stock newStock = new Stock(stock_id_result, ingredient_name, ingredient_category, stock_level, price, img_file_path);

            return newStock;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    //Method for restocking ingredients (standard amount) - tested and functional
    public static boolean standardRestock(int stock_id) {

        JsonObject ingredient_json_object = new JsonObject();
        ingredient_json_object.addProperty("stock_ID", stock_id);
        ingredient_json_object.addProperty("amount", STANDARD_RESTOCK_AMOUNT);

        String url_string = api_base_url + "restock";
        System.out.println(url_string);

        try {

            URL url = new URL(url_string);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();

            request.setDoOutput(true);
            request.setDoInput(true);

            request.setRequestProperty("Content-Type", "application/json");
            request.setRequestProperty("Accept", "application/json");
            request.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
            out.write(ingredient_json_object.toString());
            out.flush();

            StringBuilder sb = new StringBuilder();
            int HttpResult = request.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                System.out.println("" + sb.toString());
                return true;
            } else {
                System.out.println(request.getResponseMessage());
                return false;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    //Method for custom restocking ingredients - tested and functional
    public static boolean customRestock(int stock_id, int quantity) {

        JsonObject ingredient_json_object = new JsonObject();
        ingredient_json_object.addProperty("stock_ID", stock_id);
        ingredient_json_object.addProperty("amount", quantity);

        String url_string = api_base_url + "restock";
        System.out.println(url_string);

        try {

            URL url = new URL(url_string);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();

            request.setDoOutput(true);
            request.setDoInput(true);

            request.setRequestProperty("Content-Type", "application/json");
            request.setRequestProperty("Accept", "application/json");
            request.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
            out.write(ingredient_json_object.toString());
            out.flush();

            StringBuilder sb = new StringBuilder();
            int HttpResult = request.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                System.out.println("" + sb.toString());
                return true;
            } else {
                System.out.println(request.getResponseMessage());
                return false;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<Stock> getStockPendingRestock() {
        String api_url = api_base_url + "pending_restock";

        ArrayList<Stock> restockIngredients = new ArrayList<>();

        //Request the json resource at the specified url
        try {

            URL url = new URL(api_url);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            //Convert JSON object to access data
            JsonParser jp = new JsonParser(); //json parser from gson library

            JsonArray stock_array = jp.parse(new InputStreamReader((InputStream) request.getContent())).getAsJsonArray();

            for (int i = 0; i < stock_array.size(); i++) {
                JsonObject stock_object = stock_array.get(i).getAsJsonObject();
                int category_id = Integer.parseInt(stock_object.get("Category_ID").getAsString());
                //Currently includes nulls
                String img_file_path = stock_object.get("Img_File_Name").getAsString();
                String ingredient_name = stock_object.get("Ingredient_Name").getAsString();
                double price = stock_object.get("Price").getAsDouble();
                int stock_id = Integer.parseInt(stock_object.get("Stock_ID").getAsString());
                String ingredient_category = Stock_Information.getItemCategory(stock_id);
                int stock_level = Integer.parseInt(stock_object.get("Stock_Level").getAsString());
                String stock_img_path = stock_object.get("Img_File_Name").getAsString();

                Stock newStock = new Stock(stock_id, ingredient_name, ingredient_category, stock_level, price, stock_img_path);

                restockIngredients.add(newStock);

            }

            return restockIngredients;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            //Not a json array, catch and return empty arraylist
        } catch (IllegalStateException e) {
            return null;
        }
        return null;
    }


    //Manual method tests JUL
    public static void main(String[] args) {
//        ArrayList<Stock> ingredients = Stock_API.getAllStockDetails();
//

//        ArrayList<Stock> ingredients = Stock_API.getAvailableStockDetails();
//
//        for (int i = 0; i < ingredients.size(); i++) {
//            System.out.println(ingredients.get(i).getIngredient_name() + " " + ingredients.get(i).getPrice() + " " + ingredients.get(i).getStock_level());
//        }

//        Stock stock = Stock_API.getStockDetailsByID(1);
//        Stock stock = Stock_API.getStockDetailsByName("White");
//        System.out.println(stock.getIngredient_name());

//        Stock_API.standardRestock(1);
//        Stock_API.customRestock(1, -6);

//        ArrayList<Stock> restock_ingredient = Stock_API.getStockPendingRestock();
//
//        for (int i = 0; i < restock_ingredient.size(); i++) {
//            System.out.println(restock_ingredient.get(i).getIngredient_name() + " needs restocking, only " + restock_ingredient.get(i).getStock_level() + " stock remaining");
//        }



    }

}
