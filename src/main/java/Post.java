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
    private int autorID;
    private int datumObjave; // Videti https://docs.oracle.com/javase/8/docs/api/java/sql/Date.html
    private String objavaTekst;
    private ArrayList<Komentar> komentari;
    private ArrayList<Integer> attachments;
    private String id;

    Post(int autorID, int datumObjave, String objavaTekst, ArrayList<Komentar> komentari, ArrayList<Integer> attachments, String id) {
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

                // otprilike loadFromJSONObject
                if (fetchedID.equals(id)) {
                    // DEBUG: System.out.println("Matching Post ID found");
                    int autorID = ((Long) jsonObject.get("autor")).intValue();
                    int datumObjave = ((Long) jsonObject.get("datumObjave")).intValue();
                    String tekst = jsonObject.get("objavaTekst").toString();

                    JSONArray att = (JSONArray) jsonObject.get("attachments");
                    ArrayList<Integer> attachment = new ArrayList<>();
                    for (int i = 0; i < att.size(); ++i) {
                        attachment.add( Integer.valueOf( ( (Long) att.get(i)).intValue() ) );
                    }

                    
                    ArrayList<Komentar> comments = new ArrayList<>();

                    JSONArray commentArray = (JSONArray) jsonObject.get("komentari");
                    for (int i = 0; i < commentArray.size(); ++i) {
                        JSONObject currObj = (JSONObject) commentArray.get(i);
                        int commentAutorID = ((Long) currObj.get("autor")).intValue();
                        int commentDatumObjave = ((Long) currObj.get("datumObjave")).intValue();
                        String commentTekst = currObj.get("objavaTekst").toString();

                        Komentar k = new Komentar (commentAutorID,commentDatumObjave,commentTekst);
                        comments.add(k);

//                        comments.add(new Komentar(commentID, commentDatumObjave, commentTekst, new ArrayList<>()));
//                      // Pre bi radio implementaciju SQL-a u asembleru  nego ovo
//                        comments.add(new Komentar(commentID, commentDatumObjave, new ArrayList<>() ,commentTekst, comments.get(i)));

                    }
                    // DEBUG:
                    // System.out.println("Successfuly ran, results for 0 are:");
                    // System.out.println(comments.get(0));
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
            // int autorID, int datumObjave, String objavaTekst, List<Komentar> komentari, int id
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

    // Nesto trebamo da uradimo za razlikovanje Material i Assignment. Neku formu predaje da ima As dok Mat ima neki "preview"?
    // Ili da napravimo da Assignment nasledjuje Material; tu bi Material zapravo bila ova klasa tj. Post?

    // DEBUG
    @Override
    public String toString() {
        return "Post{ \n" +
                "\tpostsJSONLoc='" + postsJSONLoc + "',\n" +
                "\tautorID='" + autorID + "',\n" +
                "\tdatumObjave='" + datumObjave + "',\n" +
                "\tobjavaTekst='" + objavaTekst + "',\n" +
                "\tkomentari='" + komentari + "',\n" +
                "\tattachments='" + attachments + "',\n" +
                "\tid='" + id + "'\n" +
                '}';
    }

    // Getteri i Setteri
    public int getAutorID() {
        return autorID;
    }
    public int getDatumObjave() {
        return datumObjave;
    }
    public String getObjavaTekst() {
        return objavaTekst;
    }

    public void setAutorID(int autorID) { 
    	this.autorID = autorID; 
    }
    public void setDatumObjave(int datumObjave) {
        this.datumObjave = datumObjave;
    }
    public void setObjavaTekst(String objavaTekst) {
        this.objavaTekst = objavaTekst;
    }
}
