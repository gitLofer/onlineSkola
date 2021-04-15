import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class Attachment implements JSON_Interface<Attachment> {
    private final String attachJSONLoc = "attachment.json";
    private int autorID;
	private String naziv;
	private int id;

    Attachment(int autorID, String naziv, int id) {
        this.autorID = autorID;
        this.naziv = naziv;
        this.id = id;
    }

    Attachment(int id) {
        Attachment a = loadFromJSON(id);
        this.autorID = a.autorID;
        this.naziv = a.naziv;
        this.id = id;
    }

    @Override
    public Attachment loadFromJSON(int id) {
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(jsonLoc + attachJSONLoc)) {

            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            // DEBUG: System.out.println(jsonArray);

            // Loop for finding JSONObject with given id
            for (JSONObject jsonObject : (Iterable<JSONObject>) jsonArray) {
                int fetchedID = ((Long) jsonObject.get("id")).intValue();
                if (fetchedID == id) {
                    return new Attachment(
                            ((Long) jsonObject.get("autorID")).intValue(),
                            (String) jsonObject.get("naziv"),
                            id
                    );
                }
            }
            //user defined exception
            IdLookupException ex = new IdLookupException("Invalid ID lookup");
            throw ex;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IdLookupException e) {
        	e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveToJSON() {
        // DEBUG: System.out.println("Saving!");
        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(jsonLoc + attachJSONLoc);
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            JSONObject obj = new JSONObject();
            obj.put("autorID", this.autorID);
            obj.put("naziv", this.naziv);
            obj.put("id", this.id);

            for (int i = 0; i < jsonArray.size(); ++i) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                if (((Long) jsonObject.get("id")).intValue() == this.id) {
                    jsonArray.set(i, obj);
                    try (FileWriter file = new FileWriter(jsonLoc + attachJSONLoc)) {
                        file.write(jsonArray.toJSONString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
            jsonArray.add(obj);

            // Pisanje u JSON fajl - Ubije formatiranje jer je sve u jednoj liniji, rip
            try (FileWriter file = new FileWriter(jsonLoc + attachJSONLoc)) {
                file.write(jsonArray.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Attachment{ \n" +
                "\tautorID='" + autorID + "',\n" +
                "\tnaziv='" + naziv + "'\n" +
                "}";
    }

    // Getteri i Setteri
    public String getNaziv() {
        return naziv;
    }
    public int getAutorID() {
        return autorID;
    }
    public int getId() { 
    	return id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    public void setAutorID(int autorID) {
        this.autorID = autorID;
    }
    public void setId(int id) {
    	this.id = id; 
    }
}
