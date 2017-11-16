package main.java.entity;

import main.java.helpers.Stock_Information;

import java.io.Serializable;

/**
 * Created by Julian on 7/11/2017.
 */
public class Stock implements Serializable, Comparable<Stock> {

    int ingredient_id;
    String ingredient_name;
    String category;
    int stock_level;
    double price;
    String img_file_name;
    int used_this_month;

    public Stock(int ingredient_id, String ingredient_name, String category, int stock_level, double price, String img_file_name) {
        this.ingredient_id = ingredient_id;
        this.ingredient_name = ingredient_name;
        this.category = category;
        this.stock_level = stock_level;
        this.price = price;
        this.img_file_name = img_file_name;
    }

    public Stock(int ingredient_id) {
        this.ingredient_id = ingredient_id;
        this.ingredient_name = Stock_Information.getIngredientName(ingredient_id);
        this.category = Stock_Information.getItemCategory(ingredient_id);
        this.stock_level = -1;
        this.price = Stock_Information.getIngredientPrice(ingredient_id);
        this.img_file_name = "";
    }

    public int getIngredient_id() {
        return ingredient_id;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public String getCategory() {
        return category;
    }

    public int getStock_level() {
        return stock_level;
    }

    public double getPrice() {
        return price;
    }

    public int getUsed_this_month() {
        return used_this_month;
    }

    public String getImg_file_name() {
        return img_file_name;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStock_level(int stock_level) {
        this.stock_level = stock_level;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImg_file_name(String img_file_name) {
        this.img_file_name = img_file_name;
    }

    public void setUsed_this_month(int used_this_month) {
        this.used_this_month = used_this_month;
    }

    @Override
    public int compareTo(Stock o) {
        return Integer.compare(this.stock_level, o.stock_level);
    }

    public void incrementUsed_this_month(){
        this.used_this_month++;
    }

    public void setupUsed_this_month() {
        this.used_this_month = 0;
    }
}
