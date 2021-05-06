import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class Classroom implements JSON_Interface<Classroom>{
	private final String klasrumiJSONLoc = "classrooms.json";
	private String prof;
	private ArrayList<String> idOsoba;
	private String naziv, odeljenje;
	private ArrayList<String> postovi;
	private String id;
	
	Classroom(String prof, ArrayList<String> osobe, String naziv, String odeljenje, ArrayList<String> postovi, String id){
		this.prof = prof;
		this.idOsoba = osobe;
		this.naziv = naziv;
		this.odeljenje = odeljenje;
		this.postovi = postovi;
		this.id = id;
	}
	
	Classroom(String id){
		Classroom c = loadFromJSON(id);
        if(c == null) {
        	this.prof = null;
    		this.idOsoba = null;
    		this.naziv = null;
    		this.odeljenje = null;
    		this.postovi = null;
    		this.id = null;
            return;
        }
        this.prof = c.prof;
		this.idOsoba = c.idOsoba;
		this.naziv = c.naziv;
		this.odeljenje = c.odeljenje;
		this.postovi = c.postovi;
		this.id = id;
	}
	
	public Classroom loadFromJSON (String id) {
		JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(jsonLoc + klasrumiJSONLoc)) {

            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for (JSONObject jsonObject : (Iterable<JSONObject>) jsonArray) {
            	
                String fetchedID = jsonObject.get("id").toString();
                if (fetchedID.equals(id)) {
                	
                	JSONArray osobaArray = (JSONArray) jsonObject.get("osobe");
                	ArrayList<String> osobe = new ArrayList<>();
                	for (int i = 0; i < osobaArray.size(); ++i) {
                		osobe.add(osobaArray.get(i).toString());
                    }
                	
                	JSONArray postsArray = (JSONArray) jsonObject.get("postovi");
                	ArrayList<String> posts = new ArrayList<>();
                	for (int i = 0; i < postsArray.size(); ++i) {
                		posts.add(postsArray.get(i).toString());
                    }
                    return new Classroom(
	                        (String) jsonObject.get("prof"),
	                        osobe,
	                        (String) jsonObject.get("naziv"),
	                        (String) jsonObject.get("odeljenje"),
	                        posts,
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
	
    public void saveToJSON() {
    	try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(jsonLoc + klasrumiJSONLoc);
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            JSONObject obj = new JSONObject();
            obj.put("prof", this.prof);
            obj.put("osobe", this.idOsoba);
            obj.put("naziv", this.naziv);
            obj.put("odeljenje", this.odeljenje);
            obj.put("postovi", this.postovi);
            obj.put("id", this.id);

            for (int i = 0; i < jsonArray.size(); ++i) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                if (jsonObject.get("id").toString().equals(this.id)) {
                    jsonArray.set(i, obj);
                    try (FileWriter file = new FileWriter(jsonLoc + klasrumiJSONLoc)) {
                        file.write(jsonArray.toJSONString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
            jsonArray.add(obj);

            // Pisanje u JSON fajl - Ubije formatiranje jer je sve u jednoj liniji, rip
            try (FileWriter file = new FileWriter(jsonLoc + klasrumiJSONLoc)) {
                file.write(jsonArray.toJSONString());
                return;
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
	
	// Getteri i Setteri
	public String getProf() {
        return prof;
    }
	public ArrayList<String> getOsobe() {
        return idOsoba;
    }
	public String getNaziv() {
        return naziv;
    }
	public String getOdeljenje() {
        return odeljenje;
    }
	public ArrayList<String> getPostovi() {
        return postovi;
    }
	public String getId() {
        return id;
    }
    
    public void setProf(String prof) {
    	this.prof = prof;
    }
    public void setUcenici(ArrayList<String> osobe) {
    	this.idOsoba = osobe;
    }
    public void setNaziv(String naziv) {
    	this.naziv = naziv;
    }
    public void setOdeljenje(String odeljenje) {
    	this.odeljenje = odeljenje;
    }
    public void setPostovi(ArrayList<String> postovi) {
    	this.postovi = postovi;
    }
    public void setId(String id) {
    	this.id = id;
    }
}
