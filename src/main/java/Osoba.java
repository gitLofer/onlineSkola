import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

enum Pol
{
    Musko, Zensko
}

public class Osoba implements JSON_Interface<Osoba> {
    private final String osobeJSONLoc = "osobe.json";
    private String ime;
    private String prezime;
    private Pol pol;
    private String email;
    private String id;
    private String sifra;

    Osoba(String ime, String prezime, Pol pol, String email, String id, String sifra){
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.email = email;
        this.id = id;
        this.sifra = sifra;
    }

    Osoba(String id) {
        Osoba o = loadFromJSON(id);
        if(o == null) {
        	this.ime = null;
            this.prezime = null;
            this.pol = Pol.Musko;
            this.email = null;
            this.id = null;
            this.sifra = null;
            return;
        }
        this.ime = o.ime;
        this.prezime = o.prezime;
        this.pol = o.pol;
        this.email = o.email;
        this.id = id;
        this.sifra = o.sifra;
    }
    
    Osoba(String email, String sifra){
    	Osoba o = logIn(email, sifra);
    	if(o == null) {
        	this.ime = null;
            this.prezime = null;
            this.pol = Pol.Musko;
            this.email = null;
            this.id = null;
            this.sifra = null;
            return;
        }
        this.ime = o.ime;
        this.prezime = o.prezime;
        this.pol = o.pol;
        this.email = o.email;
        this.id = o.id;
        this.sifra = o.sifra;
    }

    @Override
    public Osoba loadFromJSON(String id) {
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(jsonLoc + osobeJSONLoc)) {

            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            // DEBUG: System.out.println(jsonArray);

            // Loop for finding JSONObject with given id
            for (JSONObject jsonObject : (Iterable<JSONObject>) jsonArray) {
                // DEBUG: System.out.println("Object loaded: " + jsonObject);

                // Fun fact: 'Number' u JSON je 'Long'. Ne 'long', nego 'Long'
                // Template za casting JSON "Number" u "int": ((Long) returnVal).intValue()
                String fetchedID = jsonObject.get("id").toString();
                // DEBUG: System.out.println(fetchedID + " =? " + id);

                if (fetchedID.equals(id)) {
                    return new Osoba(
                            (String) jsonObject.get("ime"),
                            (String) jsonObject.get("prezime"),
                            ((Boolean) jsonObject.get("muskoJe")) ? Pol.Musko : Pol.Zensko,
                            (String) jsonObject.get("email"),
                            id,
                            (String) jsonObject.get("sifra")
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
    
    public Osoba logIn(String email, String sifra){
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(jsonLoc + osobeJSONLoc)) {

            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            
            for (JSONObject jsonObject : (Iterable<JSONObject>) jsonArray) {
                String fetchedEmail = jsonObject.get("email").toString();
                String fetchedSifra = jsonObject.get("sifra").toString();

                if (email.equals(fetchedEmail) && sifra.equals(fetchedSifra)) {
                    return new Osoba(
                            (String) jsonObject.get("ime"),
                            (String) jsonObject.get("prezime"),
                            ((Boolean) jsonObject.get("muskoJe")) ? Pol.Musko : Pol.Zensko,
                            (String) jsonObject.get("email"),
                            (String) jsonObject.get("id"),
                            (String) jsonObject.get("sifra")
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveToJSON() {
        // DEBUG: System.out.println("Saving!");
        try {
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(jsonLoc + osobeJSONLoc);
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            JSONObject obj = new JSONObject();
            obj.put("ime", this.ime);
            obj.put("prezime", this.prezime);
            obj.put("muskoJe", this.pol == Pol.Musko);
            obj.put("email", this.email);
            obj.put("id", this.id);
            obj.put("sifra", this.sifra);

            for (int i = 0; i < jsonArray.size(); ++i) {
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                if (jsonObject.get("id").toString().equals(this.id)) {
                    jsonArray.set(i, obj);
                    try (FileWriter file = new FileWriter(jsonLoc + osobeJSONLoc)) {
                        file.write(jsonArray.toJSONString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
            jsonArray.add(obj);

            // Pisanje u JSON fajl - Ubije formatiranje jer je sve u jednoj liniji, rip
            try (FileWriter file = new FileWriter(jsonLoc + osobeJSONLoc)) {
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


    @Override
    public String toString() {
        return "Osoba{ \n" +
                "\time='" + ime + "',\n" +
                "\tprezime='" + prezime + "',\n" +
                "\tpol='" + pol + "',\n" +
                "\temail='" + email + "',\n" +
                "\tid='" + id + "',\n" +
                "\tsifra='" + sifra + "'\n" +
                '}';
    }

    // Getteri i Setteri - ima ih puno
    public void setEmail(String email) {
        this.email = email;
    }
    public void setIme(String ime) {
        this.ime = ime;
    }
    public void setPol(Pol pol) {
        this.pol = pol;
    }
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getEmail() {
        return email;
    }
    public String getId() {
        return id;
    }
    public String getSifra() {
        return sifra;
    }
    public String getIme() {
        return ime;
    }
    public Pol getPol() {
        return pol;
    }
    public String getPrezime() {
        return prezime;
    }
}