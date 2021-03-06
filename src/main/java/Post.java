import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class Post implements JSON_Interface<Post>{
    private final String postsJSONLoc = "posts.json";
    private String autorID;
    private String datumObjave; // Videti https://docs.oracle.com/javase/8/docs/api/java/sql/Date.html
    private String objavaTekst;
    private ArrayList<Komentar> komentari;
    private ArrayList<String> attachments;
    private String id;

    Post(String autorID, String datumObjave, String objavaTekst, ArrayList<Komentar> komentari, ArrayList<String> attachments, String id) {
        this.autorID = autorID;
        this.datumObjave = datumObjave;
        this.objavaTekst = objavaTekst;
        this.komentari = komentari;
        this.attachments = attachments;
        this.id = id;
    }

    Post (String id) {
        Post p = loadFromJSON(id);
        this.autorID = p.autorID;
        this.datumObjave = p.datumObjave;
        this.objavaTekst = p.objavaTekst;
        this.komentari = p.komentari;
        this.attachments = p.attachments;
        this.id = id;
    }

    @Override
    public Post loadFromJSON(String id) {

        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(jsonLoc + postsJSONLoc)) {

            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            for (JSONObject jsonObject : (Iterable<JSONObject>) jsonArray) {
                // DEBUG: System.out.println("Object loaded: " + jsonObject);

                String fetchedID = jsonObject.get("id").toString();

                if (fetchedID.equals(id)) {
                    // DEBUG: System.out.println("Matching Post ID found");
                    String autorID = jsonObject.get("autor").toString();
                    String datumObjave = jsonObject.get("datumObjave").toString();
                    String tekst = jsonObject.get("objavaTekst").toString();

                    JSONArray att = (JSONArray) jsonObject.get("attachments");
                    ArrayList<String> attachment = new ArrayList<>();
                    for (int i = 0; i < att.size(); ++i) {
                        attachment.add(att.get(i).toString());
                    }

                    
                    ArrayList<Komentar> comments = new ArrayList<>();

                    JSONArray commentArray = (JSONArray) jsonObject.get("komentari");
                    for (int i = 0; i < commentArray.size(); ++i) {
                        JSONObject currObj = (JSONObject) commentArray.get(i);
                        String commentAutorID = currObj.get("autor").toString();
                        String commentDatumObjave = currObj.get("datumObjave").toString();
                        String commentTekst = currObj.get("objavaTekst").toString();

                        Komentar k = new Komentar (commentAutorID,commentDatumObjave,commentTekst);
                        comments.add(k);


                    }
                    return (new Post(autorID, datumObjave, tekst, comments, attachment, id));
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
        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(jsonLoc + postsJSONLoc);
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            // String autorID, int datumObjave, String objavaTekst, List<Komentar> komentari, int id
            JSONObject obj = new JSONObject();
            obj.put("id", this.id);
            obj.put("autor", this.autorID);
            obj.put("datumObjave", this.datumObjave);
            obj.put("objavaTekst", this.objavaTekst);

            JSONArray att = new JSONArray();
            for (int i = 0; i < attachments.size(); ++i) {
                att.add(this.attachments.get(i));
            }
            obj.put("attachments", att);

            JSONArray comments = new JSONArray();
            for (int i = 0; i < komentari.size(); ++i) {
                JSONObject currComment = new JSONObject();
                Komentar k = komentari.get(i);
                currComment.put("autor", k.getAutor());
                currComment.put("datumObjave", k.getDatumObjave());
                currComment.put("objavaTekst", k.getObjavaTekst());
                comments.add(currComment);
            }
            obj.put("komentari", comments);

            for (int i = 0; i < jsonArray.size(); ++i) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                if (jsonObject.get("id").toString().equals(this.id)) {
                    jsonArray.set(i, obj);
                    try (FileWriter file = new FileWriter(jsonLoc + postsJSONLoc)) {
                        file.write(jsonArray.toJSONString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
            jsonArray.add(obj);

            // Pisanje u JSON fajl - Ubije formatiranje jer je sve u jednoj liniji, rip
            try (FileWriter file = new FileWriter(jsonLoc + postsJSONLoc)) {
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

    // DEBUG
    @Override
    public String toString() {
        String comments = "\n";
        for (Komentar k : komentari) {
            comments = comments + k + "\n";
        }
        Osoba o = new Osoba(autorID);
        String out = "\n\tAutor: " + o.getIme() + " " + o.getPrezime()
                + "\n\t" + datumObjave
                + "\n\t" + objavaTekst
                + "\n\t" + comments
                + "\n\t" + attachments
                + "\n\tID objave: " + id;

        return out;
    }

    // Getteri i Setteri
    public String getAutorID() {
        return autorID;
    }
    public String getDatumObjave() {
        return datumObjave;
    }
    public String getObjavaTekst() {
        return objavaTekst;
    }
    public String getId() {
        return id;
    }
    public ArrayList<Komentar> getKomentari() {
        return komentari;
    }

    public void setAutorID(String autorID) {
    	this.autorID = autorID; 
    }
    public void setDatumObjave(String datumObjave) {
        this.datumObjave = datumObjave;
    }
    public void setObjavaTekst(String objavaTekst) {
        this.objavaTekst = objavaTekst;
    }
}
