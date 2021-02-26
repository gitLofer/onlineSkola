enum Pol
{
    Musko, Zensko
}

public class Osoba {
    String ime;
    String prezime;
    int godiste;
    Pol pol;
    String email;

    Osoba(String ime, String prezime, int godiste, Pol pol){
        this.ime = ime;
        this.prezime = prezime;
        this.godiste = godiste;
        this.pol = pol;
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

    public String getEmail() {
        return email;
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