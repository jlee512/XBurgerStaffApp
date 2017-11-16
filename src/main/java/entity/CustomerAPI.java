package main.java.entity;

import com.google.gson.*;
import main.java.password.Passwords;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Julian on 7/11/2017.
 */
public class CustomerAPI {

    static final String api_base_url = "http://project2-burgerx-database-api.herokuapp.com/customer/";

    CustomerAPI(){
    }

    // Get customer details by username or email from the API
    public static Customer getCustomerDetailsAPI (String customer_detail, String method) {
        String api_url = api_base_url + method + "/" + customer_detail;

        try {
            //Request the json resource at the specified url
            URL url = new URL(api_url);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            //Convert JSON object to access data
            JsonParser jp = new JsonParser(); //json parser from gson library
            JsonElement root = jp.parse(new InputStreamReader((InputStream)request.getContent()));
            Gson gson = new GsonBuilder().serializeNulls().create();
            JsonObject customer_object = root.getAsJsonObject();


            //Extract customer data from JSON
            if (customer_object.get("Customer_ID") != null) {

                int customer_id = customer_object.get("Customer_ID").getAsInt();
                String username = customer_object.get("Username").getAsString();
                String email = customer_object.get("Email").getAsString();
                String phone_number = customer_object.get("Phone_Number").getAsString();
                int iterations = customer_object.get("Iterations").getAsInt();
                String salt = customer_object.get("Salt").getAsString();
                String passHash = customer_object.get("PassHash").getAsString();
                String passPin = customer_object.get("PassPin").getAsString();
                String cardToken = customer_object.get("Card_Token").getAsString();

                return new Customer(customer_id, username, email, phone_number, iterations, salt, passHash, passPin, cardToken);
            }

        } catch (MalformedURLException e) {
            //Not expected to be realised based on api_base_url setup
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean addCustomertoDBAPI (Customer customer) {

        JsonObject customer_json_object = customer.createCustomerJson();

        String url_string = api_base_url + "add";
        try {

            URL url = new URL(url_string);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();

            request.setDoOutput(true);
            request.setDoInput(true);
            request.setRequestProperty("Content-Type", "application/json");
            request.setRequestProperty("Accept", "application/json");
            request.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
            out.write(customer_json_object.toString());
            out.flush();

            StringBuilder sb = new StringBuilder();
            int HttpResult = request.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(request.getInputStream(), "utf-8"));
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

    public static void saveCustomerPaymentTokenDB(int customer_id, String pin, String card_token) {

        JsonObject paymentDetailsUpdate = new JsonObject();

        Customer customer = CustomerAPI.getCustomerDetailsAPI("" + customer_id, "user_id");
        byte[] pinHash = Passwords.hash(pin.toCharArray(), Passwords.base64Decode(customer.getSalt()), customer.getIterations());
        String pinHashString = Passwords.base64Encode(pinHash);

        paymentDetailsUpdate.addProperty("customer_id", "" + customer_id);
        paymentDetailsUpdate.addProperty("pass_pin", pinHashString);
        paymentDetailsUpdate.addProperty("card_token", card_token);

        String url_string = api_base_url + "save/payment";
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
            System.out.println(paymentDetailsUpdate);
            out.write(paymentDetailsUpdate.toString());
            out.flush();

            StringBuilder sb = new StringBuilder();
            int HttpResult = request.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(request.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                System.out.println("" + sb.toString());
                return;
            } else {
                System.out.println(request.getResponseMessage());
                return;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;

    }

    public static String getPaymentToken(int customer_id) {
        String api_url = api_base_url + "token/" + customer_id;

        try {
            //Request the json resource at the specified url
            URL url = new URL(api_url);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            //Convert JSON object to access data
            JsonParser jp = new JsonParser(); //json parser from gson library
            JsonElement root = jp.parse(new InputStreamReader((InputStream)request.getContent()));
            Gson gson = new GsonBuilder().serializeNulls().create();
            JsonObject customer_object = root.getAsJsonObject();


            //Extract customer data from JSON
            String card_token = customer_object.get("Card_Token").getAsString();

            return card_token;

        } catch (MalformedURLException e) {
            //Not expected to be realised based on api_base_url setup
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static boolean validateCustomerPassword(String password_entry, String hashDB, String saltDB, int iterationsDB) {

        //Use Passwords class to compare password_entry with hash from the database
        byte[] salt_bytes = Passwords.base64Decode(saltDB);
        byte[] hash_bytes = Passwords.base64Decode(hashDB);

        boolean password_validation = Passwords.isExpectedPassword(password_entry.toCharArray(), salt_bytes, iterationsDB, hash_bytes);

        return password_validation;
    }

    public static void main(String[] args) {

//        String username = "TestCustomer1";
//        String email = "blah@gmail.com";
//        String phone_number = "0211234567";
//        String password = "test";
//        int iterations = Passwords.getNextNumIterations();
//        byte[] salt = Passwords.getNextSalt(16);
//        String saltString = Passwords.base64Encode(salt);
//        String passHash = Passwords.base64Encode(Passwords.hash(password.toCharArray(), salt, iterations));
//        String passPin = null;
//        String cardToken = null;
//
//        Customer newCustomer = new Customer(username, email, phone_number, iterations, saltString, passHash, passPin, cardToken);
//
//        CustomerAPI.addCustomertoDBAPI(newCustomer);

//        CustomerAPI.saveCustomerPaymentTokenDB(1, "1234", Token_Generator.generateToken());
//
//        System.out.println(CustomerAPI.getPaymentToken(1));

    }

}
