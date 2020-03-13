package Service;

import java.util.HashMap;

public class BrandType {
    private static HashMap<Integer, String> brandTypeMap = new HashMap<Integer, String>(){{
        put(1, "OE");
        put(2, "IAM");
    }};

    public static String getBrandType(int id){
        try {
            return brandTypeMap.get(id);
        }
        catch (Exception ex){
            return null;
        }

    }
}
