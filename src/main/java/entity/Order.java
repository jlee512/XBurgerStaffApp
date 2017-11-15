package main.java.entity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TimeZone;

/**
 * Created by Julian on 7/11/2017.
 */
//Javabean to represent the Order object - serializeable and therefore can be stored within a session
public class Order implements Serializable {

    int order_id;
    Staff staff;
    Customer customer;
    String order_datetime;
    String status;
    ArrayList<Item> items;
    double price;
    String priceString;

    public Order(int order_id, Staff staff, Customer customer, String order_datetime, String status, ArrayList<Item> items) {
        this.order_id = order_id;
        this.staff = staff;
        this.customer = customer;
        this.order_datetime = order_datetime;
        this.status = status;
        this.items = items;
    }

    // Order creation method for John
    public Order(int order_id, int customer_id, ArrayList<Item> items) {
        this.order_id = order_id;
        this.items = items;
        this.staff = null;
        this.customer = new Customer(customer_id, null, null, null, -1, null, null, null, null);
    }

    public Order(int customer_id, ArrayList<Item> items) {
        this.items = items;
        this.staff = null;
        this.customer = new Customer(customer_id, null, null, null, -1, null, null, null, null);
    }

    public int getOrder_id() {
        return order_id;
    }

    public Staff getStaff() {
        return staff;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getOrder_datetime() {
        return order_datetime;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice() {

        double price = 0.0;

        for (Item item : items) {

            for (Stock ingredient : item.getIngredients()) {

                price += ingredient.getPrice();

            }

        }

        return price;
    }

    public String getPriceString(){ return priceString; }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrder_datetime(String order_datetime) {
        this.order_datetime = order_datetime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setPriceString(){
        double newPrice = Math.round(getPrice());
        this.priceString = "$" + newPrice + "0";
    }

    public JsonObject createOrderJson () {

        JsonObject orderObject = new JsonObject();
        JsonArray orderItems = new JsonArray();

        for (int i = 0; i < items.size(); i++) {

            Item item = items.get(i);

            JsonArray item_ingredients = new JsonArray();
            for (int j = 0; j < item.getIngredients().size(); j++) {
                item_ingredients.add(item.getIngredients().get(j).getIngredient_id());
            }

            orderItems.add(item_ingredients);

        }

        orderObject.add("item_details_list", orderItems);

        return orderObject;
    }

    public String convertTime(String order_datetime){

//        System.out.println("1: " + order_datetime);

        LocalDateTime ldt = LocalDateTime.parse(order_datetime, DateTimeFormatter.RFC_1123_DATE_TIME);
        ZoneId nz = ZoneId.of("Pacific/Auckland");
        ZonedDateTime gmt = ldt.atZone(TimeZone.getTimeZone("GMT+0").toZoneId());
        String gmtString = "" + gmt;
//        System.out.println(gmtString);

        ZonedDateTime nzTime = gmt.withZoneSameInstant(TimeZone.getTimeZone("GMT+13:00").toZoneId());
        String nzTimeString = "" + nzTime;
//        System.out.println(nzTimeString);

//        String nice = nzTime.getHour() +":"+ nzTime.getMinute() +", "+ nzTime.getDayOfWeek() +" "+ nzTime.getDayOfMonth() +" "+ nzTime.getMonth() +" "+ nzTime.getYear();
//        System.out.println(nice);

        String nice = DateTimeFormatter.ofPattern("dd MMMM yyyy - hh:mm a").format(nzTime);

        return nice;
    }

    public static void main(String[] args) {
//        Order order = Order_API.getOrderDetailsByOrderIDCustomerAPI(21);

//        System.out.println(order.createOrderJson());

//        System.out.println(order.convertTime(order.getOrder_datetime()));
    }
}