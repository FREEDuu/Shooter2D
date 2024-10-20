import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonParser{
    static JSONParser parser = new JSONParser(); 


    public static JSONObject getJsonObject(String jsonPath){
        try {
            Object obj = parser.parse(new FileReader(jsonPath));
            JSONObject jsonObj = (JSONObject) obj; 
            return jsonObj;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}