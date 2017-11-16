package main.java.entity;

import com.google.gson.*;
import main.java.password.Passwords;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Julian on 8/11/2017.
 */
public class Staff_API {

    static final String api_base_url = "http://project2-burgerx-database-api.herokuapp.com/staff/";

    Staff_API(){
    }

    //Get staff details by username or user_id from the API
    public static Staff getStaffDetailsAPI (String staff_detail, String method) {
        String api_url = api_base_url + method + "/" + staff_detail;

        try {
            //Request the json resource at the specified url
            URL url = new URL(api_url);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            //Convert JSON object to access data
            JsonParser jp = new JsonParser(); //json parser from gson library
            JsonElement root = jp.parse(new InputStreamReader((InputStream)request.getContent()));
            JsonObject staff_object = root.getAsJsonObject();
            Gson gson = new GsonBuilder().serializeNulls().create();


            //Extract customer data from JSON
            if (staff_object.get("Staff_ID") != null) {
                int staff_id = staff_object.get("Staff_ID").getAsInt();
                String staff_type = staff_object.get("Staff_Type").getAsString();
                String username = staff_object.get("Username").getAsString();
                int iterations = staff_object.get("Iterations").getAsInt();
                String salt = staff_object.get("Salt").getAsString();
                String passHash = staff_object.get("PassHash").getAsString();

                return new Staff(staff_id, staff_type, username, iterations, salt, passHash);
            }

        } catch (MalformedURLException e) {
            //Not expected to be realised based on api_base_url setup
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  catch (NumberFormatException e) {
            return null;
        }

        return null;
    }

    //Get staff list by staff type from the API
    public static ArrayList<Staff> getStaffListByRole (String role) {
        String api_url = api_base_url + "staff_type/" + role;

        try {
            //Request the json resource at the specified url
            URL url = new URL(api_url);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            //Convert JSON object to access data
            JsonParser jp = new JsonParser(); //json parser from gson library
            JsonElement root = jp.parse(new InputStreamReader((InputStream)request.getContent()));
            JsonArray jsonArrayStaff = root.getAsJsonArray();

            Gson gson = new Gson();
            for (JsonElement staff_element : jsonArrayStaff) {
                JsonObject staff_object = staff_element.getAsJsonObject();
                String username = staff_object.get("Username").getAsString();
                System.out.println(username);

            }

            System.out.println(root);
        } catch (MalformedURLException e) {
            //Not expected to be realised based on api_base_url setup
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static boolean addStafftoDBAPI (Staff staff) {

        JsonObject staff_json_object = staff.createStaffJson();

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
            out.write(staff_json_object.toString());
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
            } else {
                System.out.println(request.getResponseMessage());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(staff_json_object);


        return true;

    }

    public static boolean validateStaffPassword(String password_entry, String hashDB, String saltDB, int iterationsDB) {

        //Use Passwords class to compare password_entry with hash from the database
        byte[] salt_bytes = Passwords.base64Decode(saltDB);
        byte[] hash_bytes = Passwords.base64Decode(hashDB);

        boolean password_validation = Passwords.isExpectedPassword(password_entry.toCharArray(), salt_bytes, iterationsDB, hash_bytes);

        return password_validation;
    }

    public static void main(String[] args) {
//        Staff staff_test = Staff_API.getStaffDetailsAPI("m4ddi3", "username");
//        System.out.println(staff_test.getUsername());
//        System.out.println(staff_test.getStaff_type());
//
//        Staff staff_test1 = Staff_API.getStaffDetailsAPI("11", "staff_id");
//        System.out.println(staff_test1.getIterations());
//
//        //Test staff addition API method
//        String username = "o06h05ted0o";
//        String staff_type = "Wizard";
//        String password = "test";
//        int iterations = Passwords.getNextNumIterations();
//        byte[] salt = Passwords.getNextSalt(16);
//        String saltString = Passwords.base64Encode(salt);
//        String passHash = Passwords.base64Encode(Passwords.hash(password.toCharArray(), salt, iterations));
//
//        Staff newStaff = new Staff(staff_type, username, iterations, saltString, passHash);
//
//        Staff_API.addStafftoDBAPI(newStaff);

//        Staff_API.getStaffListByRole("wizard");

    }
}
