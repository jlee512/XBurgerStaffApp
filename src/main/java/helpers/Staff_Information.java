package main.java.helpers;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Julian on 8/11/2017.
 */
public class Staff_Information {

    public static final HashMap<Integer, String> staff_type = new HashMap<>();

    static {
        staff_type.put(1, "Manager");
        staff_type.put(11, "Floor Staff");
        staff_type.put(21, "Wizard");
    }

    public static String getStaffType(int staffType_ID){
        return staff_type.get(staffType_ID);
    }

    public static int getStaffTypeID(String staffType) {
        for (Map.Entry entry : staff_type.entrySet()) {
            if (entry.getValue().equals(staffType)) {
                return Integer.parseInt(entry.getKey().toString());
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(getStaffType(1));
    }

}
