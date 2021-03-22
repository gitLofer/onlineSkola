import java.util.*;

enum Pol
{
    Musko, Zensko
}

public class Osoba {
    private String ime;
    private String prezime;
    private int godiste;
    private Pol pol;
    private String email;
    private int id;
    private String sifra;

    Osoba(String ime, String prezime, int godiste, Pol pol, String email, int id, String sifra){
        this.ime = ime;
        this.prezime = prezime;
        this.godiste = godiste;
        this.pol = pol;
        this.email = email;
        this.id = id;
        this.sifra = sifra;
    }

    // Getteri i Setteri
    public void setEmail(String email) {
        this.email = email;
    }
    public void setGodiste(int godiste) {
        this.godiste = godiste;
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
    public int getGodiste() {
        return godiste;
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