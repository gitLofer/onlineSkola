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
    private String autorID;
	private String naziv;
	private String id;

    Attachment(String autorID, String naziv, String id) {
        this.autorID = autorID;
        this.naziv = naziv;
        this.id = id;
    }

    Attachment(String id) {
        Attachment a = loadFromJSON(id);
        this.autorID = a.autorID;
        this.naziv = a.naziv;
        this.id = id;
    }

    @Override
    public Attachment loadFromJSON(String id) {
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(jsonLoc + attachJSONLoc)) {

            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            // DEBUG: System.out.println(jsonArray);

            // Loop for finding JSONObject with given id
            for (JSONObject jsonObject : (Iterable<JSONObject>) jsonArray) {
                String fetchedID = jsonObject.get("id").toString();
                if (fetchedID.equals(id)) {
                    return new Attachment(
                            (String) jsonObject.get("autorID"),
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
                if (jsonObject.get("id").toString().equals(this.id)) {
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
        Osoba o = new Osoba(autorID);
        return "\n\tAutor: " + o.getIme() + " " + o.getPrezime() + ", naziv: " + naziv + "\n";
    }

    // Getteri i Setteri
    public String getNaziv() {
        return naziv;
    }
    public String getAutorID() {
        return autorID;
    }
    public String getId() { 
    	return id;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    public void setAutorID(String autorID) {
        this.autorID = autorID;
    }
    public void setId(String id) {
    	this.id = id; 
    }
}
