import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

// Sve osnovne/bazne klase trebaju implement ovo. Vidi Osoba kao primer
public interface JSON_Interface<selfObject> {
    // jsonLoc + "exact_file_name.json"
    final String jsonLoc = "src/main/resources/json/";

    // Tutorijal: https://mkyong.com/java/json-simple-example-read-and-write-json/
    selfObject loadFromJSON (String id);
    void saveToJSON();

    static void deleteFromJSON(String id, String jsonLocFIle) {
        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(jsonLoc + jsonLocFIle);
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject temp = (JSONObject) jsonArray.get(i);
                if (temp.get("id").toString().equals(id)) {
                    jsonArray.remove(i);
                    break;
                }
            }
            try (FileWriter file = new FileWriter(jsonLoc + jsonLocFIle)) {
                file.write(jsonArray.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
