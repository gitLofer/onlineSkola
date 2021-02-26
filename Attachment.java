public class Attachment {
    Osoba autor;
    String naziv;
    String link;

    Attachment(Osoba autor, String naziv, String link) {
        this.autor = autor;
        this.naziv = naziv;
        this.link = link;
    }

    // Getteri i Setteri
    public String getLink() {
        return link;
    }
    public String getNaziv() {
        return naziv;
    }
    public Osoba getAutor() {
        return autor;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    public void setAutor(Osoba autor) {
        this.autor = autor;
    }
}
