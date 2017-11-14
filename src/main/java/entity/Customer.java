package main.java.entity;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Julian on 5/11/2017.
 */

//Javabean to represent the Customer object - serializeable and therefore can be stored within a session
public class Customer implements Serializable {

    @SerializedName("Customer_ID")
    @Expose
    int customer_id;
    @SerializedName("Username")
    @Expose
    String username;
    @SerializedName("Email")
    @Expose
    String email;
    @SerializedName("Phone_Number")
    @Expose
    String phone_number;
    @SerializedName("Iterations")
    @Expose
    int iterations;
    @SerializedName("Salt")
    @Expose
    String salt;
    @SerializedName("PassHash")
    @Expose
    String passHash;
    @SerializedName("PassPin")
    @Expose
    String passPin;
    @SerializedName("Card_Token")
    @Expose
    String cardToken;

    // Constructor for Customer object with full suite of details as pulled from the database
    public Customer(int customer_id, String username, String email, String phone_number, int iterations, String salt, String passHash, String passPin, String cardToken) {
        this.customer_id = customer_id;
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.iterations = iterations;
        this.salt = salt;
        this.passHash = passHash;
        this.passPin = passPin;
        this.cardToken = cardToken;
    }

    // Constructor for "prospective" customer with full suite of details (minus customer_id which will be assigned on registration
    public Customer(String username, String email, String phone_number, int iterations, String salt, String passHash, String passPin, String cardToken) {
        this.username = username;
        this.email = email;
        this.phone_number = phone_number;
        this.iterations = iterations;
        this.salt = salt;
        this.passHash = passHash;
        this.passPin = passPin;
        this.cardToken = cardToken;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public int getIterations() {
        return iterations;
    }

    public String getSalt() {
        return salt;
    }

    public String getPassHash() {
        return passHash;
    }

    public String getPassPin() {
        return passPin;
    }

    public String getCardToken() {
        return cardToken;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public void setPassPin(String passPin) {
        this.passPin = passPin;
    }

    public void setCardToken(String cardToken) {
        this.cardToken = cardToken;
    }

    public JsonObject createCustomerJson() {
        JsonObject customer_json_object = new JsonObject();
        customer_json_object.addProperty("Username", this.username);
        customer_json_object.addProperty("Email", this.email);
        customer_json_object.addProperty("Phone_Number", this.phone_number);
        customer_json_object.addProperty("Iterations", this.iterations);
        customer_json_object.addProperty("Salt", this.salt);
        customer_json_object.addProperty("PassHash", this.passHash);
        customer_json_object.addProperty("PassPin", this.passPin);
        customer_json_object.addProperty("Card_Token", this.cardToken);

        return customer_json_object;

    }

}
