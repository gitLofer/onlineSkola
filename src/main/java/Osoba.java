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
    private int id;
    private String sifra;

    Osoba(String ime, String prezime, Pol pol, String email, int id, String sifra){
        this.ime = ime;
        this.prezime = prezime;
        this.pol = pol;
        this.email = email;
        this.id = id;
        this.sifra = sifra;
    }

    Osoba(int id) {
        Osoba o = loadFromJSON(id);
        this.ime = o.ime;
        this.prezime = o.prezime;
        this.pol = o.pol;
        this.email = o.email;
        this.id = id;
        this.sifra = o.sifra;
    }

    @Override
    public Osoba loadFromJSON(int id) {
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(jsonLoc + osobeJSONLoc)) {

            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            // DEBUG: System.out.println(jsonArray);

            // Loop for finding JSONObject with given id
            for (JSONObject jsonObject : (Iterable<JSONObject>) jsonArray) {
                // DEBUG: System.out.println("Object loaded: " + jsonObject);

                // Fun fact: 'Number' u JSON je 'Long'. Ne 'long', nego 'Long'
                // Template za casting JSON "Number" u "int": ((Long) returnVal).intValue()
                int fetchedID = ((Long) jsonObject.get("id")).intValue();
                // DEBUG: System.out.println(fetchedID + " =? " + id);

                if (fetchedID == id) {
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

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // TODO: Pretvoriti ovo u IDLookupException
        System.out.println("Invalid ID lookup");
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
            jsonArray.add(obj);

            // Pisanje u JSON fajl - Ubije formatiranje jer je sve u jednoj liniji, rip
            try (FileWriter file = new FileWriter(jsonLoc + osobeJSONLoc)) {
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

    // Autogenerisano je ali izgleda kao smece sta god uradio. Popravi ako hoces
    @Override
    public String toString() {
        return "Osoba{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", pol=" + pol +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", sifra='" + sifra + '\'' +
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
    public void setId(int id) {
        this.id = id;
    }
    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getEmail() {
        return email;
    }
    public int getId() {
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