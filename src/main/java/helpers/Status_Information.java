package main.java.helpers;

import java.util.HashMap;

/**
 * Created by Julian on 8/11/2017.
 */
public class Status_Information {

    public static final HashMap<Integer, String> status = new HashMap<>();

    static {
        status.put(0, "Pending");
        status.put(1, "Making");
        status.put(2, "Completed");
        status.put(-1, "Insufficient Ingredients");
    }

    public static String getStatus(int order_status){
        return status.get(order_status);
    }

}
